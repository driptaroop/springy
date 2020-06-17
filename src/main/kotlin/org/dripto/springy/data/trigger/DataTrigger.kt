package org.dripto.springy.data.trigger

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
        value = ["data"],
        prefix = "springy.conditions",
        havingValue = "true"
)
class DataTrigger
