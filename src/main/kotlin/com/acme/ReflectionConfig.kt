package com.acme

import com.google.api.client.auth.oauth2.*
import io.quarkus.runtime.annotations.RegisterForReflection

@Suppress("unused")
@RegisterForReflection(
    targets = [
        AuthorizationCodeRequestUrl::class,
        AuthorizationCodeResponseUrl::class,
        AuthorizationCodeTokenRequest::class,
        AuthorizationRequestUrl::class,
        PasswordTokenRequest::class,
        RefreshTokenRequest::class,
        TokenErrorResponse::class,
        TokenRequest::class,
        TokenResponse::class
    ]
)
class ReflectionConfig
