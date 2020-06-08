package org.dripto.springy.web.formatter

import org.dripto.springy.core.model.Department
import org.dripto.springy.core.model.Department.IT
import org.dripto.springy.core.model.Department.SALES
import org.dripto.springy.core.model.Designation
import org.dripto.springy.core.model.Designation.*
import org.springframework.core.convert.converter.Converter
import org.springframework.format.Formatter
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomConverterFormatters {
    //lambda implements converter class for different converters
    fun stringToDepartment() = Converter<String, Department> {
        when(it) {
            "Tech" -> IT
            "sales department" -> SALES
            else -> throw IllegalArgumentException("unsupported type")
        }
    }

    //another implementation of formatter. works as same as `DesignationFormatter` if declared as a bean
    @Suppress("unused")
    fun designationFormatter() = object: Formatter<Designation> {
        override fun print(source: Designation, locale: Locale) = when(source) {
            JUN -> "JUNIOR"
            MID -> "MID-LEVEL"
            SEN -> "SENIOR"
        }

        override fun parse(text: String, locale: Locale) = when(text) {
            "JUNIOR" -> JUN
            "MID-LEVEL" -> MID
            "SENIOR" -> SEN
            else -> throw IllegalArgumentException("unsupported")
        }
    }
}

@Component
class DesignationFormatter: Formatter<Designation> {
    override fun print(source: Designation, locale: Locale) = when(source) {
        JUN -> "JUNIOR"
        MID -> "MID-LEVEL"
        SEN -> "SENIOR"
    }

    override fun parse(text: String, locale: Locale) = when(text) {
        "JUNIOR" -> JUN
        "MID-LEVEL" -> MID
        "SENIOR" -> SEN
        else -> throw IllegalArgumentException("unsupported")
    }
}
