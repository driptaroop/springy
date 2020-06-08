package org.dripto.springy.web.validator

import org.dripto.springy.core.model.Employee
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

class EmployeeValidator: Validator {
    override fun validate(target: Any, errors: Errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "name.empty");
    }

    override fun supports(clazz: Class<*>): Boolean = Employee::class.java == clazz
}
