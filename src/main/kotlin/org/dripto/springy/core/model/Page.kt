package org.dripto.springy.core.model

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy::class)
interface Page<T> {
    val totalPages: Long
    val totalElements: Long
    val content: List<T>
    val pageable: Pageable
}

interface Pageable {
    val pageNumber: Int
    val pageSize: Int
}

class PageImpl<T>(
        override val content: List<T>,
        override val pageable: Pageable
) : Page<T> {
    override val totalPages: Long
        get() = totalElements / pageable.pageSize
    override val totalElements: Long
        get() = content.size.toLong()
}

class DefaultPageableRequest(
        override val pageNumber: Int = 0, override val pageSize: Int = 25
) : Pageable
