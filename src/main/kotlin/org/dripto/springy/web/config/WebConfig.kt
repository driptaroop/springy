package org.dripto.springy.web.config

import org.dripto.springy.web.formatter.CustomConverterFormatters
import org.dripto.springy.web.formatter.DesignationFormatter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class WebConfig(
        private val customConverterFormatters: CustomConverterFormatters
): WebFluxConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        /**
         * converters and formatters can be registered here.
         * Also, declaring converters and formatters as beans will be auto registered
         * which is easier and preferable
         * for example, DesignationFormatter is declared as a bean and is auto registered
         */
        registry.addConverter(customConverterFormatters.stringToDepartment())
    }
}
