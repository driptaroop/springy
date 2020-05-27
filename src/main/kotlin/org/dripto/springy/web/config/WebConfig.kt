package org.dripto.springy.web.config

import org.dripto.springy.core.model.Department
import org.dripto.springy.core.model.Department.IT
import org.dripto.springy.core.model.Department.SALES
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class WebConfig: WebFluxConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(stringToEnum())
    }

    fun stringToEnum() = Converter<String, Department> {
        when(it) {
            "Tech" -> IT
            "sales department" -> SALES
            else -> throw IllegalArgumentException("unsupported type")
        }
    }
}
