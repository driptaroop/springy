package org.dripto.springy.applicationcontext

import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanNameAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.AbstractBeanFactory
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnBean(ApplicationContextTrigger::class)
class ApplicationContextAccess{
    @Autowired lateinit var applicationContext: ApplicationContext

    @Bean
    fun printAppContextBeanFactory() = ApplicationRunner {
        println("$applicationContext")
    }
}
