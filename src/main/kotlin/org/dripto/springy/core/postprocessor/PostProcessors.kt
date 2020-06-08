package org.dripto.springy.core.postprocessor

import com.fasterxml.jackson.databind.ObjectMapper
import org.dripto.springy.web.controller.EmployeeRestController
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.problem.ProblemModule
import org.zalando.problem.violations.ConstraintViolationProblemModule

@Configuration
class PostProcessors {
    @Bean
    fun employeeRestControllerPostProcessor() = object : BeanPostProcessor {
        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
            if (bean is EmployeeRestController) {
                println("EmployeeRestController post processor ===== $bean")
            }
            return bean
        }
    }

    @Bean
    fun objectMapperPostProcessor() = object : BeanPostProcessor {
        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
            if (bean is ObjectMapper) {
                println(bean)
                bean.registerModules(ProblemModule(), ConstraintViolationProblemModule())
            }
            return bean
        }
    }
}
