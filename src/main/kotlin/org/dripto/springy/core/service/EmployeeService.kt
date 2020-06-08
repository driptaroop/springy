package org.dripto.springy.core.service

import com.github.javafaker.Faker
import org.dripto.springy.core.model.*
import org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId
import java.util.concurrent.TimeUnit.DAYS

@Service
@Scope(SCOPE_SINGLETON)
class EmployeeService(private val faker: Faker, val employee: Employee) {
    val employees by lazy {
        with(faker) {
            List(1000) {
                Employee(
                        firstName = name().firstName(),
                        lastName = name().lastName(),
                        empId = (20..60).random(),
                        employeeSince = LocalDate.ofInstant(date().past(5 * 365, DAYS).toInstant(),
                                ZoneId.systemDefault()),
                        birthDate = LocalDate.ofInstant(date().birthday().toInstant(), ZoneId.systemDefault()),
                        address = address().fullAddress(),
                        department = Department.values().random(),
                        profession = company().profession(),
                        designation = Designation.values().random()
                )
            }
        }
    }

    suspend fun getPaginatedEmployees(pageable: Pageable): Page<Employee>
            = PageImpl(content = employees.take(pageable.pageSize), pageable = pageable)

    suspend fun getEmployeeBeanFactory() = employee
}
