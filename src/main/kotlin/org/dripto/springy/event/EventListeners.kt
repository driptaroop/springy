package org.dripto.springy.event

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
@ConditionalOnBean(EventTrigger::class)
class EventListeners {
    /**
     * two way of writing event listener
     */

    @EventListener
    fun onAppStart(event: ApplicationStartedEvent) = println("ApplicationStartedEvent event triggered")

    @EventListener(classes = [ApplicationReadyEvent::class])
    fun onAppReady() = println("ApplicationReadyEvent event triggered")
}
