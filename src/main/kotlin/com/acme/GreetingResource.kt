package com.acme

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Path("/")
class GreetingResource {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String {
        logger.info("Hello")
        return "Hello from RESTEasy Reactive"
    }
}
