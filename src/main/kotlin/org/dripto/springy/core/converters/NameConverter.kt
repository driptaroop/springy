package org.dripto.springy.core.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class NameConverter: Converter<NameSeparate, FullName> {
    override fun convert(source: NameSeparate) = with(source){ FullName("$firstName $lastName") }
}
