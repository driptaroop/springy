package org.dripto.springy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringyApplication

fun main(args: Array<String>) {
    runApplication<SpringyApplication>(*args)
}
