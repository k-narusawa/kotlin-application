plugins {
  id("org.springframework.boot") version "2.7.4"
  id("io.spring.dependency-management") version "1.0.12.RELEASE"
  id("com.avast.gradle.docker-compose") version "0.16.8"
  id("com.google.cloud.tools.jib") version "3.3.0"
  kotlin("jvm") version "1.6.21"
  kotlin("plugin.spring") version "1.6.21"
  kotlin("kapt") version "1.7.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
  implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
  implementation("org.mybatis:mybatis-spring:2.0.7")
  implementation("com.auth0:java-jwt:4.0.0")
  implementation("org.apache.commons:commons-lang3:3.12.0")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.0")
  implementation("org.springframework.boot:spring-boot-starter-data-redis:2.7.2")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.security:spring-security-test")
  runtimeOnly("mysql:mysql-connector-java")
  testImplementation("com.intuit.karate:karate-core:1.3.0")
  testImplementation("com.intuit.karate:karate-junit5:1.3.0")
}

jib {
  to {
    image = "registry.hub.docker.com/19992240/kotlin-application"
    tags = setOf("$version", "$version")
  }
  container {
    args = listOf()
    ports = listOf("8080")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
