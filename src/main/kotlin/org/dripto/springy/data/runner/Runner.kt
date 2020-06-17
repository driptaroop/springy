package org.dripto.springy.data.runner

import com.querydsl.core.types.Predicate
import org.dripto.springy.data.model.QStudent.student
import org.dripto.springy.data.model.Student
import org.dripto.springy.data.model.Student.Department.COMMERCE
import org.dripto.springy.data.model.Student_
import org.dripto.springy.data.repositories.StudentJDBCRepository
import org.dripto.springy.data.service.StudentService
import org.dripto.springy.data.trigger.DataTrigger
import org.dripto.springy.extension.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Profile
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Order.desc
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component


@Component
@ConditionalOnBean(DataTrigger::class)
@Profile("!pg")
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

        /**
         * querydsl - requires querydsl model generation as well.
         * here querydsl-apt annotation processor(using kapt) is used to generate querydsl model
         */
        val predicate: Predicate = student.department.eq(COMMERCE)
        py(studentJDBCRepository.findAll(predicate,
                PageRequest.of(0, 5, Sort.by(desc("roll")))).content)

        /**
         * criteria - requires JPA meta-model generation as well.
         * here hibernate-jpamodelgen annotation processor(using kapt) is used to generate meta model
         */
        val moreThan50YearsOld = Specification<Student> {
            root, query, criteriaBuilder -> criteriaBuilder.greaterThan(root[Student_.age], 50)
        }
        pc(studentJDBCRepository
                .findAll(moreThan50YearsOld,
                        PageRequest.of(0, 5, Sort.by(desc("roll")))
                )
                .content)

        /**
         * query by example. not possible in the java way in kotlin if entity is data class with
         * not not nullable properties
         */
        // val example = Example.of(Student(department = COMMERCE))
        // studentJDBCRepository.findAll(example)
    }
}
