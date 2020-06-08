import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "xyz.dripto"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-reactor-netty")
	}
	implementation("org.springframework.boot:spring-boot-starter-undertow")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.google.guava:guava:29.0-jre")
	implementation("com.github.javafaker:javafaker:1.0.2")
	implementation("org.zalando:problem-spring-webflux:0.25.2")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("io.strikt:strikt-core:0.26.1")
	testImplementation("io.mockk:mockk:1.10.0")

	//implementation("org.springframework.boot:spring-boot-starter-data-rest")
	//runtimeOnly("com.h2database:h2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	inputs.files("processResources")
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
