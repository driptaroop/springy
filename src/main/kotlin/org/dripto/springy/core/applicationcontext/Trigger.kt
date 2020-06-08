package org.dripto.springy.core.applicationcontext

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
        value = ["application-context"],
        prefix = "springy.conditions",
        havingValue = "true"
)
class ApplicationContextTrigger
