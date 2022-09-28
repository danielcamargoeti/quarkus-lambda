package com.acme

import com.google.api.client.auth.oauth2.*
import com.google.api.client.auth.openidconnect.IdToken
import com.google.api.client.googleapis.auth.oauth2.*
import com.google.api.client.json.webtoken.JsonWebToken
import io.quarkus.runtime.annotations.RegisterForReflection

@Suppress("unused")
@RegisterForReflection(
    targets = [
        // Google OAuth client
        AuthorizationCodeRequestUrl::class,
        AuthorizationCodeResponseUrl::class,
        AuthorizationCodeTokenRequest::class,
        AuthorizationRequestUrl::class,
        PasswordTokenRequest::class,
        RefreshTokenRequest::class,
        TokenErrorResponse::class,
        TokenRequest::class,
        TokenResponse::class,
        // Google api client
        GoogleAuthorizationCodeRequestUrl::class,
        GoogleBrowserClientRequestUrl::class,
        GoogleClientSecrets::class,
        GoogleClientSecrets.Details::class,
        GoogleIdToken.Payload::class,
        GoogleTokenResponse::class,
        IdToken.Payload::class,
        JsonWebToken.Payload::class
    ]
)
class ReflectionConfig
