package com.acme

import com.google.api.client.auth.oauth2.BearerToken
import com.google.api.client.auth.oauth2.Credential

class AuthCredential : Credential(BearerToken.authorizationHeaderAccessMethod())
