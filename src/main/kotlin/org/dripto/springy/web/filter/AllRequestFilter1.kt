package org.dripto.springy.web.filter

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
@Order(1)
class AllRequestFilter1: WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        println("filter: 1, request: ${exchange.request}")
        println("filter: 1, response: ${exchange.response}")
        println("filter: 1, log id: ${exchange.logPrefix}")
        exchange.attributes.putIfAbsent("requestAttr","requestAttrVal")
        return chain.filter(exchange)
    }
}
