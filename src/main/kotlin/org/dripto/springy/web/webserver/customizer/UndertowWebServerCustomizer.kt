package org.dripto.springy.web.webserver.customizer

import org.springframework.boot.web.embedded.undertow.UndertowReactiveWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.stereotype.Component


@Component
class UndertowWebServerCustomizer : WebServerFactoryCustomizer<UndertowReactiveWebServerFactory> {
    override fun customize(factory: UndertowReactiveWebServerFactory) {
        println("http/2 is enabled: ${factory.http2.isEnabled}")
    }
}
