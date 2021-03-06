package org.dripto.springy

import org.dripto.springy.core.configproperties.ConfigProperties
import org.fusesource.jansi.AnsiConsole
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.Banner.Mode.LOG
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.availability.ApplicationAvailability
import org.springframework.boot.context.event.ApplicationStartingEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import javax.annotation.PreDestroy

@SpringBootApplication
@EnableConfigurationProperties(value = [ConfigProperties::class])
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

    @PreDestroy
    fun onExit() {
        println("exiting")
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
/*fun main() {
    val secret = "secret"
    val text = "text"

    val hashcode: HashCode = Hashing.hmacSha256(secret.toByteArray()).hashString(text, UTF_8)
    val str = Base64Utils.encodeToString(hashcode.asBytes())
    println(str)
}
*/
