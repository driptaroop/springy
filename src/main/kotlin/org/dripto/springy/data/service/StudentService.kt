package org.dripto.springy.data.service

import com.github.javafaker.Faker
import org.dripto.springy.data.model.Student
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId

@Service
class StudentService(private val faker: Faker) {
    val students by lazy {
        var roll = 1
        with(faker) {
            List(1000) {
                Student(
                        firstName = name().firstName(),
                        lastName = name().lastName(),
                        roll = roll++,
                        birthDate = LocalDate.ofInstant(date().birthday().toInstant(), ZoneId.systemDefault()),
                        address = address().fullAddress(),
                        department = Student.Department.values().random(),
                        school = educator().secondarySchool(),
                        age = (20..80).random()
                )
            }
        }
    }
}
