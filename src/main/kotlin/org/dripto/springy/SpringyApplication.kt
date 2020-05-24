package org.dripto.springy

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.Banner.Mode.LOG
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.availability.ApplicationAvailability
import org.springframework.boot.context.event.ApplicationStartingEvent
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringyApplication {

    @Bean
    @ConditionalOnProperty(
            value = ["baseApplicationRunner"],
            prefix = "springy.conditions",
            havingValue = "true"
    )
    fun applicationRunner(applicationAvailability: ApplicationAvailability) = ApplicationRunner {
        println("""
            =========Application State==========
            ${applicationAvailability.livenessState}
            ${applicationAvailability.readinessState}
        """.trimIndent())
    }
}

fun main(args: Array<String>) {
    runApplication<SpringyApplication>(*args) {
        setBannerMode(LOG)
        setLogStartupInfo(true)

        /**
         * Some events are actually triggered before the ApplicationContext is created,
         * so you cannot register a listener on those as a @Bean. You can register them
         * with the SpringApplication.addListeners(…​) method or the
         * SpringApplicationBuilder.listeners(…​) method.
         */
        addListeners(ApplicationListener<ApplicationStartingEvent> {
            println("ApplicationStartingEvent event triggered")
        })
    }
}
