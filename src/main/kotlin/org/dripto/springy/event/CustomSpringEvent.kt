package org.dripto.springy.event

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

class CustomSpringEvent (source: Any, val message: String): ApplicationEvent(source)

@Configuration
@ConditionalOnBean(EventTrigger::class)
class CustomSpringEventPublisherListener {

    @Bean
    fun customSpringEventPublisher(
            applicationEventPublisher: ApplicationEventPublisher,
            applicationContext: ApplicationContext
    ) = ApplicationRunner {
        applicationEventPublisher
                .also { println("Publishing CustomSpringEvent====") }
                .apply {
                    publishEvent(CustomSpringEvent(applicationContext, "CustomSpringEvent"))
                }
    }

    @EventListener
    fun customSpringEventListener(event: CustomSpringEvent) = println("""
        CustomSpringEvent Triggered.
        Event: ${event.message}
    """.trimIndent())
}
