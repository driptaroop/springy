package org.dripto.springy.data.repositories.jdbc

import org.dripto.springy.data.model.Student
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.PagingAndSortingRepository

interface StudentJDBCRepository:
        PagingAndSortingRepository<Student, Int>,
        QuerydslPredicateExecutor<Student> {
    fun findByDepartment(department: Student.Department, pageable: Pageable): Page<Student>
}
