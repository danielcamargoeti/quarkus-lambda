package com.acme.resources

import com.acme.GoogleOAuthClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Path("/callback")
class CallbackResource(
    private val googleOAuthClient: GoogleOAuthClient
) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun handle(
        @QueryParam("code") code: String,
        @QueryParam("state") state: String
    ): String {
        logger.info("Get refresh token")
        val (refreshToken) = googleOAuthClient.authenticate(code)

        return refreshToken
    }
}
