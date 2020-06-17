package org.dripto.springy.data.config

import org.h2.tools.Server
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.sql.SQLException

@Component
@Profile("!pg")
class H2ConsoleConfig {
    private lateinit var webServer: Server
    private lateinit var server: Server

    @EventListener(ContextRefreshedEvent::class)
    @Throws(SQLException::class)
    fun start() {
        webServer = Server.createWebServer("-webPort", "8082", "-tcpAllowOthers").start()
        server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start()
    }

    @EventListener(ContextClosedEvent::class)
    fun stop() {
        webServer.stop()
        server.stop()
    }
}
