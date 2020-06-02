package org.dripto.springy.web.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.dripto.springy.core.model.Department
import org.dripto.springy.web.formatter.CustomConverterFormatters
import org.dripto.springy.web.serializers.CustomDepartmentDeserializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.web.codec.CodecCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
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
