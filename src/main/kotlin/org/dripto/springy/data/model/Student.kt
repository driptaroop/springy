package org.dripto.springy.data.model

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id


@Entity
data class Student(
        @Id
        val roll: Int,
        val firstName: String,
        val lastName: String,
        val department: Department,
        val address: String,
        val birthDate: LocalDate,
        val school: String
) {
    enum class Department {
        SCIENCE, ARTS, COMMERCE
    }
}
