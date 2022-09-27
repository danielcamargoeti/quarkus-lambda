package com.acme

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class GreetingResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
            .`when`().get("/")
            .then()
            .statusCode(200)
            .body(`is`("https://accounts.google.com/o/oauth2/auth?access_type=offline&approval_prompt=force&client_id=some-id&redirect_uri=http://localhost:3000&response_type=code&scope=openid%20https://www.googleapis.com/auth/userinfo.profile%20https://www.googleapis.com/auth/userinfo.email&state=some-state"))
    }
}
