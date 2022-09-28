package com.acme

import io.quarkus.runtime.annotations.RegisterForReflection
import io.smallrye.config.ConfigMapping

@RegisterForReflection
@ConfigMapping(prefix = GoogleOAuthProperties.PREFIX)
interface GoogleOAuthProperties {
    fun applicationName(): String
    fun clientId(): String
    fun clientSecret(): String
    fun redirectUri(): String

    companion object {
        const val PREFIX = "google"
    }
}
