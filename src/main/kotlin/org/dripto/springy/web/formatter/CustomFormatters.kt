package org.dripto.springy.web.formatter

import org.springframework.core.convert.converter.Converter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CustomFormatters {
    //implement converter class for different converters
    fun localDateToStringConverter(): Converter<LocalDate, String> = Converter<LocalDate, String> {
        it.format(DateTimeFormatter.BASIC_ISO_DATE)
    }
}
