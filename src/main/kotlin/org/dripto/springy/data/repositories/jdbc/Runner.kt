package org.dripto.springy.data.repositories.jdbc

import com.querydsl.core.types.Predicate
import org.dripto.springy.data.model.QStudent
import org.dripto.springy.data.model.QStudent.student
import org.dripto.springy.data.model.Student
import org.dripto.springy.data.model.Student.Department.COMMERCE
import org.dripto.springy.data.service.StudentService
import org.dripto.springy.extension.pblu
import org.dripto.springy.extension.pg
import org.dripto.springy.extension.pm
import org.dripto.springy.extension.py
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Order.desc
import org.springframework.stereotype.Component


@Component
class Runner(
        private val studentJDBCRepository: StudentJDBCRepository,
        private val studentService: StudentService
): ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        //saving
        pg("====saving students=====")
        studentJDBCRepository.saveAll(studentService.students)
        //getting
        pg("====getting students=====")
        pblu(studentJDBCRepository.findAll(PageRequest.of(0, 5)).content)

        //paging and sorting
        pm(studentJDBCRepository.findByDepartment(Student.Department.SCIENCE,
                PageRequest.of(1,5)).content)
        pm(studentJDBCRepository.findByDepartment(Student.Department.ARTS,
                PageRequest.of(0,10, Sort.by(desc("roll")))).content)

        //querydsl
        val predicate: Predicate = student.department.eq(COMMERCE)
        py(studentJDBCRepository.findAll(predicate,
                PageRequest.of(0, 5, Sort.by(desc("roll")))).content)
    }
}
