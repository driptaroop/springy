package org.dripto.springy.core.event

import org.springframework.boot.context.event.ApplicationContextInitializedEvent
import org.springframework.context.ApplicationListener

class ApplicationContextInitializedEventListener
    : ApplicationListener<ApplicationContextInitializedEvent> {
    override fun onApplicationEvent(event: ApplicationContextInitializedEvent)
            = println(with(event) { "triggered: $this : $applicationContext" })
}
