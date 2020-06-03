package org.dripto.springy.web.resolvers

import org.dripto.springy.core.model.DefaultPageableRequest
import org.dripto.springy.core.model.Pageable
import org.springframework.core.MethodParameter
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class PageableArgumentResolver: HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
            parameter.parameterType == Pageable::class.java

    override fun resolveArgument(parameter: MethodParameter, bindingContext: BindingContext, exchange: ServerWebExchange): Mono<Any> {
        val limit = exchange.request.queryParams.getFirst("limit")?.toIntOrNull()
        val offset = exchange.request.queryParams.getFirst("offset")?.toIntOrNull()
        return Mono.just (
                if(limit == null || offset == null)
                    DefaultPageableRequest()
                else
                    DefaultPageableRequest(offset, limit)
        )
    }
}
