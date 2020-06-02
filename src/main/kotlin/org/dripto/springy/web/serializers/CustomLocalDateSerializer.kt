package org.dripto.springy.web.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CustomLocalDateSerializer: StdSerializer<LocalDate>(LocalDate::class.java) {
    override fun serialize(
            value: LocalDate, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.format(formatter))
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy")
    }
}
