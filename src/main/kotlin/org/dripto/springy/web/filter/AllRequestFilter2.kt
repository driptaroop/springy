package org.dripto.springy.web.filter

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
@Order(2)
class AllRequestFilter2: WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        println("filter: 2, request: ${exchange.request.headers}")
        println("filter: 2, response: ${exchange.response.headers}")
        return chain.filter(exchange)
    }
}
