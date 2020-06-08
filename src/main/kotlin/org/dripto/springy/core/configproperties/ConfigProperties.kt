package org.dripto.springy.core.configproperties

import org.jetbrains.annotations.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import org.springframework.util.unit.DataSize
import org.springframework.validation.annotation.Validated
import java.time.Duration
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@ConfigurationProperties("springy.config-properties")
@ConstructorBinding
@Validated
data class ConfigProperties (
        @field:NotNull
        val stringparam: String,
        @field:Min(100)
        val intparam: Int,
        @Max(100) //must include @field since this is a field level validation
        val wrongValidationParam: Int,
        val nullParam: String?,
        val booleanparam: Boolean,
        val conversionParam: Int, //Needs CustomPropertyConverter to convert
        val durationParam: Duration,
        val datasizeParam: DataSize,
        val objectparam: NestedConfigProperties
)

data class NestedConfigProperties (
        val stringparam: String,
        val intparam: Int,
        val booleanparam: Boolean
)

@Component
@ConfigurationPropertiesBinding
class CustomPropertyConverter: Converter<String, Int> {
    override fun convert(source: String) = source.length
}
