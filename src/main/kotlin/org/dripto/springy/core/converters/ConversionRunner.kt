package org.dripto.springy.core.converters

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.ConversionService

@Configuration
class ConversionRunner {
    @Bean
    fun run(
            conversionService: ConversionService
    ) = ApplicationRunner {
        println(conversionService.convert(NameSeparate("dripto", "das"), FullName::class.java))
    }
}
