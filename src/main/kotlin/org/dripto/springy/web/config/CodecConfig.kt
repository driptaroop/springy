package org.dripto.springy.web.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.dripto.springy.core.model.Department
import org.dripto.springy.web.serializers.CustomDepartmentDeserializer
import org.springframework.boot.web.codec.CodecCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder

@Configuration
class CodecConfig {
    //register module
    @Bean
    fun departmentDeserializer() = SimpleModule()
            .apply {
                addDeserializer(Department::class.java, CustomDepartmentDeserializer())
            }

    /**
     * extra config options
     */
    @Bean
    fun myCodecCustomizer(encoder: Jackson2JsonEncoder, decoder: Jackson2JsonDecoder)
            = CodecCustomizer {
        it.customCodecs().registerWithDefaultConfig(encoder)
        it.customCodecs().registerWithDefaultConfig(decoder)
    }

    @Bean
    fun jackson2JsonEncoder(mapper: ObjectMapper): Jackson2JsonEncoder {
        return Jackson2JsonEncoder(mapper)
    }

    @Bean
    fun jackson2JsonDecoder(mapper: ObjectMapper): Jackson2JsonDecoder {
        return Jackson2JsonDecoder(mapper)
    }
}
