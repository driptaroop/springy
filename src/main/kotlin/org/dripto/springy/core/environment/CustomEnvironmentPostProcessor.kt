package org.dripto.springy.core.environment

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertySource
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import java.io.IOException


/**
 * 	While using @PropertySource on your @SpringBootApplication may seem to be a convenient
 * 	way to load a custom resource in the Environment, we do not recommend it.
 * 	Such property sources are not added to the Environment until the application context
 * 	is being refreshed. This is too late to configure certain properties such as
 * 	logging.* and spring.main.* which are read before refresh begins.
 */
class CustomEnvironmentPostProcessor: EnvironmentPostProcessor {
    private val loader = YamlPropertySourceLoader()
    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        val path: Resource = ClassPathResource("config/switch.yml")
        environment.propertySources.addLast(loadYml(path, "switch"))
    }
    private fun loadYml(path: Resource, name: String): PropertySource<*> {
        require(path.exists()) { "Resource $path does not exist" }
        return try {
            loader.load(name, path)[0]
        } catch (ex: IOException) {
            throw IllegalStateException("Failed to load yaml configuration from $path", ex)
        }
    }
}
