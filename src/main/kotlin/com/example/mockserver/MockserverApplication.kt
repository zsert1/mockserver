package com.example.mockserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
@SpringBootApplication
class MockserverApplication

fun main(args: Array<String>) {
	runApplication<MockserverApplication>(*args)
	// val mockServer= ClientAndServer.startClientAndServer(1080)
	val mockServerManager= MockServerManager()
	mockServerManager.start();

	val healthChecker = HealthChecker(mockServerManager)
    healthChecker.startMonitoring()
	// mockServer.`when`(
    //     HttpRequest.request()
    //         .withMethod("GET")
    //         .withPath("/health")
    // ).respond(
    //     HttpResponse.response()
    //         .withStatusCode(200)
    //         .withBody("Service is running")
    // )

	// println("MockServer is running at http://localhost:1080/health")
}
