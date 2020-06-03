package org.dripto.springy.web.controller

import org.dripto.springy.core.model.*
import org.dripto.springy.core.service.EmployeeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/employee")
class EmployeeRestController(private val employeeService: EmployeeService) {

    @GetMapping
    fun getEmployeeList() = employeeService.employees

    @GetMapping("/suspend")
    suspend fun getEmployeeListSuspended() = employeeService.employees

    @GetMapping("/paginated")
    suspend fun getEmployeesPaginated(
            pageable: Pageable
    )
            = employeeService.getPaginatedEmployees(pageable)

    @PostMapping
    suspend fun postEmployee(@RequestBody employee: Employee) = employee

    @GetMapping("/department/{department}")
    suspend fun employeeDepartment(@PathVariable department: Department)
            = department

    @GetMapping("/designation/{designation}")
    suspend fun employeeDesignation(@PathVariable designation: Designation)
            = designation
}
