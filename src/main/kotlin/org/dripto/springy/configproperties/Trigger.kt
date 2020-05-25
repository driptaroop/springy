package org.dripto.springy.configproperties

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
        value = ["config-properties"],
        prefix = "springy.conditions",
        havingValue = "true"
)
class ConfigPropertiesTrigger
