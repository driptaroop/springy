package org.dripto.springy.web.config

import org.dripto.springy.web.formatter.CustomConverterFormatters
import org.dripto.springy.web.formatter.DesignationFormatter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class WebConfig(
        private val customConverterFormatters: CustomConverterFormatters,
        private val designationFormatter: DesignationFormatter
): WebFluxConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(customConverterFormatters.stringToDepartment())
        registry.addFormatter(designationFormatter)
    }
}
