package org.dripto.springy.web.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.dripto.springy.core.model.Department
import org.springframework.boot.jackson.JsonComponent

/**
 * If you use Jackson to serialize and deserialize JSON data,
 * you might want to write your own JsonSerializer and JsonDeserializer
 * classes. Custom serializers are usually registered with Jackson through
 * a module, but Spring Boot provides an alternative @JsonComponent
 * annotation that makes it easier to directly register Spring Beans.
 *
 * You can use the @JsonComponent annotation directly on JsonSerializer,
 * JsonDeserializer or KeyDeserializer implementations. You can also use
 * it on classes that contain serializers/deserializers as inner classes
 */
@JsonComponent
class CustomDepartmentSerializer : StdSerializer<Department>(Department::class.java) {
    override fun serialize(
            value: Department, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.pretty)
    }

    private val Department.pretty: String
        get() = when (this) {
            Department.IT -> "Tech"
            Department.SALES -> "Sales"
        }
}

class CustomDepartmentDeserializer : StdDeserializer<Department>(Department::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext) = when (p.text) {
        "Tech" -> Department.IT
        "Sales" -> Department.SALES
        else -> throw IllegalArgumentException("illegal department")
    }
}
