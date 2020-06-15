package org.dripto.springy.data.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
class DataConfig {

    @Bean
    fun dataSource(): DataSource = DataSourceBuilder.create().apply {
        driverClassName("org.h2.Driver")
        url("jdbc:h2:mem:test")
        username("SA")
        password("")
    }.build()
}
