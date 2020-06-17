package org.dripto.springy.data.config

import org.dripto.springy.data.trigger.DataTrigger
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
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

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val vendorAdapter = HibernateJpaVendorAdapter().apply {
            setGenerateDdl(true)
            /**
             * can be used like this or in config files
             */
            setShowSql(true)
        }
        return LocalContainerEntityManagerFactoryBean().apply {
            dataSource = dataSource()
            jpaVendorAdapter = vendorAdapter
            setPackagesToScan("org.dripto.springy.data")
            /**
             * can be used like this to show formatted sql. also can use config files.
             */
            setJpaPropertyMap(mapOf("hibernate.format_sql" to true))
        }
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager = JpaTransactionManager().apply {
        this.entityManagerFactory = entityManagerFactory
    }

    @Bean
    fun exceptionTranslation() =
            PersistenceExceptionTranslationPostProcessor()
}
