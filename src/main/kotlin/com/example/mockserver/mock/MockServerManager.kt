package com.example.mockserver;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
public class MockServerManager {

    private var mockServer: ClientAndServer? = null

    fun start(port: Int = 1080) {
        mockServer = ClientAndServer.startClientAndServer(port)
        println("✅ MockServer started at http://localhost:$port")
        setupEndpoints()
    }

    // MockServer 중지
    fun stop() {
        mockServer?.stop()
        println("🛑 MockServer stopped.")
    }
    private fun setupEndpoints() {
        mockServer?.`when`(
            HttpRequest.request()
                .withMethod("GET")
                .withPath("/health")
        )?.respond(
            HttpResponse.response()
                .withStatusCode(200)
                .withBody("Service is running")
        )
        println("📌 /health endpoint is ready.")
    }


}
