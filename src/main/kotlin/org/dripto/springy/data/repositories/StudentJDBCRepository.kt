package org.dripto.springy.data.repositories

import org.dripto.springy.data.model.Student
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.QueryByExampleExecutor


interface StudentJDBCRepository:
        PagingAndSortingRepository<Student, Int>,
        // for querydsl
        QuerydslPredicateExecutor<Student>,
        // for criteria
        JpaSpecificationExecutor<Student>,
        // Query by example
        QueryByExampleExecutor<Student> {
    fun findByDepartment(department: Student.Department, pageable: Pageable): Page<Student>
}
