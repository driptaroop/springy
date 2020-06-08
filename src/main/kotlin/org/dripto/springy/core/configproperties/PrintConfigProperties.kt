package org.dripto.springy.core.configproperties

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.stereotype.Component

@Component
@ConditionalOnBean(ConfigPropertiesTrigger::class)
class PrintConfigProperties(
        val configProperties: ConfigProperties
): ApplicationRunner{
    override fun run(args: ApplicationArguments?) {
        println(configProperties)
    }
}
