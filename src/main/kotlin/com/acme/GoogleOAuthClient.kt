package com.acme

import com.google.api.client.auth.oauth2.TokenResponse
import com.google.api.client.googleapis.auth.oauth2.*
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.apache.v2.ApacheHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.oauth2.Oauth2
import com.google.api.services.oauth2.Oauth2Scopes
import java.net.URL
import javax.enterprise.context.ApplicationScoped
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ApplicationScoped
class GoogleOAuthClient(
    private val properties: GoogleOAuthProperties
) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    private val scopes = setOf(
        // For basic user information
        Oauth2Scopes.OPENID,
        Oauth2Scopes.USERINFO_PROFILE,
        Oauth2Scopes.USERINFO_EMAIL
    )

    private fun googleAuthorizationCodeFlow(): GoogleAuthorizationCodeFlow {
        val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()
        val details = GoogleClientSecrets.Details()

        details.clientId = properties.clientId()
        details.clientSecret = properties.clientSecret()

        val clientSecrets = GoogleClientSecrets()
        clientSecrets.installed = details

        // Create builder
        val builder = GoogleAuthorizationCodeFlow
            .Builder(ApacheHttpTransport(), jsonFactory, clientSecrets, scopes)

        return builder
            .setAccessType("offline")
            .setApprovalPrompt("force")
            .build()
    }

    fun createAuthUrl(state: String): URL {
        logger.debug("Creating a new authentication URL")
        val flow = googleAuthorizationCodeFlow()

        val codeRequestUrl: GoogleAuthorizationCodeRequestUrl = flow
            .newAuthorizationUrl()
            .setRedirectUri(properties.redirectUri())
            .setState(state)

        logger.info("Entries {}", codeRequestUrl.entries.size)
        logger.info("Empty in native builds: {}", codeRequestUrl.buildRelativeUrl())

        return codeRequestUrl.toURL()
    }

    fun authenticate(authorizationCode: String): AuthUser {
        logger.debug("Fetching a new refresh token")
        val flow = googleAuthorizationCodeFlow()

        val codeTokenRequest: GoogleAuthorizationCodeTokenRequest = flow
            .newTokenRequest(authorizationCode)
            .setRedirectUri(properties.redirectUri())

        logger.info("Entries {}", codeTokenRequest.entries.size)
        val token = codeTokenRequest.execute()

        // Let's save the refresh token in DynamoDB so that every instance will
        // be able to reuse it
        val httpTransport = ApacheHttpTransport()
        val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()
        val credential = AuthCredential()
        credential.accessToken = token.accessToken

        val oauth2 = Oauth2
            .Builder(httpTransport, jsonFactory, credential)
            .setApplicationName(properties.applicationName())
            .build()

        val userInfo = oauth2
            .userinfo()
            .get()
            .execute()

        return AuthUser(
            refreshToken = token.refreshToken,
            userInfo = userInfo
        )
    }

    // A new cache will be created for each instance. In other words, each instance will
    // resolve a new access token every 59 minutes and 55 seconds.
    fun getAuthCredential(refreshToken: String): AuthCredential {
        logger.debug("Fetching a new access token")
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()

        val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()

        val tokenResponse: TokenResponse = GoogleRefreshTokenRequest(
            httpTransport,
            jsonFactory,
            refreshToken,
            properties.clientId(),
            properties.clientSecret()
        )
            .setScopes(scopes)
            .setGrantType("refresh_token")
            .execute()

        // Instantiate a credential
        val credential = AuthCredential()
        credential.accessToken = tokenResponse.accessToken

        return credential
    }

    fun applicationName(): String {
        return properties.applicationName()
    }
}
