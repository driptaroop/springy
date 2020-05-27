package org.dripto.springy.web.controller

import org.dripto.springy.core.model.Department
import org.dripto.springy.core.service.EmployeeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/employee")
class EmployeeRestController(private val employeeService: EmployeeService) {

    @GetMapping
    fun getEmployeeList() = employeeService.employees

    @GetMapping("/suspend")
    suspend fun getEmployeeListSuspended() = employeeService.employees

    @GetMapping("/department/{department}")
    suspend fun employeeDepartment(@PathVariable department: Department)
            = department
}
