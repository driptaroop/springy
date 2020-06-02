package org.dripto.springy.core.model

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.PropertyNamingStrategy.KebabCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.dripto.springy.web.serializers.CustomLocalDateSerializer
import java.time.LocalDate

@JsonNaming(KebabCaseStrategy::class)
data class Employee(
        val firstName: String,
        val lastName: String,
        val empId: Int,
        val department: Department,
        val address: String,
        val profession: String,
        @JsonSerialize(using = CustomLocalDateSerializer::class)
        val birthDate: LocalDate,
        val designation: Designation,
        val employeeSince: LocalDate
)

enum class Designation {
    JUN, MID, SEN;

    @JsonValue
    fun pretty() = when (this) {
        JUN -> "Junior"
        MID -> "Mid-Level"
        SEN -> "Senior"
    }
}

enum class Department {
    IT, SALES
}

