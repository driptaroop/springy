package org.dripto.springy.core.event

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
        value = ["events"],
        prefix = "springy.conditions",
        havingValue = "true"
)
class EventTrigger
