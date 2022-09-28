package com.acme

import com.google.api.services.oauth2.model.Userinfo
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class AuthUser(
    val refreshToken: String,
    val userInfo: Userinfo
)
