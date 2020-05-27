package org.dripto.springy.core.model

import java.time.LocalDate

data class Employee(
        val firstName: String,
        val lastName: String,
        val empId: Int,
        val department: Department,
        val address: String,
        val profession: String,
        val birthDate: LocalDate
)

enum class Department {
    IT, SALES
}
