package com.acme

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.apache.v2.ApacheHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.oauth2.Oauth2Scopes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Path("/")
class GreetingResource {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    private val scopes = setOf(
        // For basic user information
        Oauth2Scopes.OPENID,
        Oauth2Scopes.USERINFO_PROFILE,
        Oauth2Scopes.USERINFO_EMAIL
    )

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun get(): String {
        logger.info("Build auth url")
        val flow = googleAuthorizationCodeFlow()

        val codeRequestUrl = flow
            .newAuthorizationUrl()
            .setRedirectUri("http://localhost:3000")
            .setState("some-state")

        logger.debug("Why is this empty in native? {}", codeRequestUrl.entries.size)

        return codeRequestUrl.toURL().toString()
    }

    private fun googleAuthorizationCodeFlow(): GoogleAuthorizationCodeFlow {
        val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()
        val details = GoogleClientSecrets.Details()

        details.clientId = "some-id"
        details.clientSecret = "some-secret"

        val clientSecrets = GoogleClientSecrets()
        clientSecrets.installed = details

        val builder = GoogleAuthorizationCodeFlow
            .Builder(ApacheHttpTransport(), jsonFactory, clientSecrets, scopes)

        return builder
            .setAccessType("offline")
            .setApprovalPrompt("force")
            .build()
    }
}
