package org.dripto.springy.core.service

import com.github.javafaker.Faker
import org.dripto.springy.core.model.Department
import org.dripto.springy.core.model.Department.IT
import org.dripto.springy.core.model.Department.SALES
import org.dripto.springy.core.model.Employee
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId
import kotlin.random.Random

@Service
class EmployeeService(private val faker: Faker) {
    val employees: List<Employee>
        get() = with(faker) {
            List(10) {
                Employee(
                        firstName = name().firstName(),
                        lastName = name().lastName(),
                        empId = (20..60).random(),
                        birthDate = LocalDate.ofInstant(date().birthday().toInstant(), ZoneId.systemDefault()),
                        address = address().fullAddress(),
                        department = if (Random.nextBoolean()) IT else SALES,
                        profession = company().profession()
                )
            }
        }
}
