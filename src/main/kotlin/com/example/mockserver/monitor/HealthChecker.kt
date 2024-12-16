
package com.example.mockserver;

import com.example.mockserver.MockServerManager 
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
class HealthChecker(private val mockServerManager: MockServerManager) {

    fun startMonitoring(interval: Long = 5000) {
        thread {
            while (true) {
                Thread.sleep(interval)
                checkHealth()
            }
        }
    }

    private fun checkHealth() {
        try {
            val url = URL("http://localhost:1080/health")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            if (connection.responseCode != 200) {
                println("⚠️  Service Unhealthy! Restarting MockServer...")
                restartMockServer()
            } else {
                println("✅  Service is healthy!")
            }
            connection.disconnect()
        } catch (e: Exception) {
            println("🚨  Unable to connect to MockServer! Restarting...")
            restartMockServer()
        }
    }

    // MockServer 재시작
    private fun restartMockServer() {
        mockServerManager.stop()
        mockServerManager.start()
    }

}
