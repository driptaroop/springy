package org.dripto.springy.core.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.javafaker.Faker
import org.dripto.springy.web.controller.EmployeeRestController
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.web.server.WebExceptionHandler
import org.zalando.problem.ProblemModule
import org.zalando.problem.spring.webflux.advice.ProblemExceptionHandler
import org.zalando.problem.spring.webflux.advice.ProblemHandling
import org.zalando.problem.violations.ConstraintViolationProblemModule


@Configuration
class CoreConfig {
    @Bean
    fun faker() = Faker()

    @Bean
    @Order(-2) // The handler must have precedence over WebFluxResponseStatusExceptionHandler and Spring Boot's
    // ErrorWebExceptionHandler
    fun problemExceptionHandler(mapper: ObjectMapper, problemHandling: ProblemHandling): WebExceptionHandler =
            ProblemExceptionHandler(mapper, problemHandling)
}
