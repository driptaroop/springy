package org.dripto.springy.core.factory

import com.github.javafaker.Faker
import org.dripto.springy.core.model.Department
import org.dripto.springy.core.model.Designation
import org.dripto.springy.core.model.Employee
import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneId
import java.util.concurrent.TimeUnit
import javax.annotation.Resource

@Component
class EmployeeBeanFactory: FactoryBean<Employee> {

    @Resource //works like @Autowired
    private lateinit var faker: Faker

    override fun getObject(): Employee = with(faker) {
                Employee(
                        firstName = name().firstName(),
                        lastName = name().lastName(),
                        empId = (20..60).random(),
                        employeeSince = LocalDate.ofInstant(date().past(5 * 365, TimeUnit.DAYS).toInstant(),
                                ZoneId.systemDefault()),
                        birthDate = LocalDate.ofInstant(date().birthday().toInstant(), ZoneId.systemDefault()),
                        address = address().fullAddress(),
                        department = Department.values().random(),
                        profession = company().profession(),
                        designation = Designation.values().random()
                )
            }

    override fun getObjectType(): Class<*> = Employee::class.java
}
