package org.dripto.springy.web.controller

import org.dripto.springy.core.model.*
import org.dripto.springy.core.service.EmployeeService
import org.dripto.springy.web.validator.EmployeeValidator
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/employee")
class EmployeeRestController(private val employeeService: EmployeeService) {

    //custom binder
    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.addValidators(EmployeeValidator())
    }

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
    suspend fun postEmployee(@RequestBody @Validated employee: Employee) = employee

    @GetMapping("/department/{department}")
    suspend fun employeeDepartment(@PathVariable department: Department)
            = department

    @GetMapping("/designation/{designation}")
    suspend fun employeeDesignation(@PathVariable designation: Designation)
            = designation
}
