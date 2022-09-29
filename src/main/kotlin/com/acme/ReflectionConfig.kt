package com.acme

import io.quarkus.runtime.annotations.RegisterForReflection

@Suppress("unused")
@RegisterForReflection(
    targets = [
        // google-http-client
        com.google.api.client.http.HttpHeaders::class,
        com.google.api.client.json.rpc2.JsonRpcRequest::class,
        com.google.api.client.json.webtoken.JsonWebSignature::class,
        com.google.api.client.json.webtoken.JsonWebToken.Header::class,
        // google-api-client
        com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl::class,
        com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl::class,
        com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets::class,
        com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details::class,
        com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload::class,
        com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse::class,
        com.google.api.client.googleapis.json.GoogleJsonError.ErrorInfo::class,
        com.google.api.client.googleapis.json.GoogleJsonError.Details::class,
        com.google.api.client.googleapis.json.GoogleJsonError.ParameterViolations::class,
        com.google.api.client.googleapis.json.GoogleJsonError::class,
        com.google.api.client.googleapis.json.GoogleJsonErrorContainer::class,
        com.google.api.client.googleapis.mtls.ContextAwareMetadataJson::class,
        // google-oauth-client
        com.google.api.client.auth.oauth.OAuthAuthorizeTemporaryTokenUrl::class,
        com.google.api.client.auth.oauth.OAuthCallbackUrl::class,
        com.google.api.client.auth.oauth.OAuthCredentialsResponse::class,
        com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl::class,
        com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl::class,
        com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest::class,
        com.google.api.client.auth.oauth2.AuthorizationRequestUrl::class,
        com.google.api.client.auth.oauth2.PasswordTokenRequest::class,
        com.google.api.client.auth.oauth2.RefreshTokenRequest::class,
        com.google.api.client.auth.oauth2.TokenErrorResponse::class,
        com.google.api.client.auth.oauth2.TokenRequest::class,
        com.google.api.client.auth.oauth2.TokenResponse::class,
        com.google.api.client.auth.openidconnect.IdToken.Payload::class,
        com.google.api.client.auth.openidconnect.IdTokenResponse::class,
        // @TODO: This must be fixed first
        // https://github.com/googleapis/google-oauth-java-client/issues/947
        // com.google.api.client.auth.openidconnect.IdTokenVerifier.PublicKeyLoader.JsonWebKeySet::class
        // com.google.api.client.auth.openidconnect.IdTokenVerifier.PublicKeyLoader.JsonWebKey::class
    ]
)
class ReflectionConfig
