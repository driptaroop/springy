package org.dripto.springy.applicationcontext

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
        value = ["application-context-and-aware-interfaces"],
        prefix = "springy.conditions",
        havingValue = "true"
)
class ApplicationContextTrigger
