package org.dripto.springy.web.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.dripto.springy.core.model.Page
import org.springframework.boot.jackson.JsonComponent

@JsonComponent
class PageableSerialization<T>: StdSerializer<Page<T>>(Page::class.java, true) {
    override fun serialize(page: Page<T>, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartObject()
        gen.writeObjectField("content", page.content)
        gen.writeObjectField("page", page.paged)
        gen.writeNumberField("total", page.totalElements)
        gen.writeEndObject()
    }

    private val <T> Page<T>.paged
        get() = object {
            val limit = pageable.pageSize
            val offset = pageable.pageNumber
        }
}
