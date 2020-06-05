package org.dripto.springy.core.config

import org.dripto.springy.core.service.EmployeeService
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component

@Component
class EmployeeServicePostProcessor: BeanPostProcessor {
    override fun postProcessBeforeInitialization(bean: Any, beanName: String) = bean

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        if(bean is EmployeeService){
            println("EmployeeService post processor ===== $bean")
        }
        return bean
    }
}
