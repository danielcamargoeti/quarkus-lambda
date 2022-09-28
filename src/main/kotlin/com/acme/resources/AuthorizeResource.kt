package com.acme.resources

import com.acme.GoogleOAuthClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Path("/authorize")
class AuthorizeResource(private val googleOAuthClient: GoogleOAuthClient) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun handle(): String {
        logger.info("Build auth url")
        return googleOAuthClient.createAuthUrl("some-state").toString()
    }
}
