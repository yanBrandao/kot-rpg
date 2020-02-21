import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	jacoco
	"java-library"
	id("org.springframework.boot") version "2.2.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("net.saliman.cobertura") version "3.0.0"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	id ("com.github.kt3k.coveralls") version "2.9.0"
	id ("org.sonarqube") version "2.8"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.hsqldb:hsqldb")

	implementation("org.postgresql:postgresql:9.4.1208")
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:2.2.1.RELEASE")
	implementation("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	implementation ("com.graphql-java:graphql-spring-boot-starter:5.0.2")
	implementation ("com.graphql-java:graphiql-spring-boot-starter:5.0.2")
	implementation ("com.graphql-java:graphql-java-tools:5.2.4")
	implementation("io.opentracing.contrib:opentracing-spring-cloud-starter:0.4.0")
	implementation("io.opentracing.contrib:opentracing-spring-jaeger-cloud-starter:2.0.3")


}

tasks.withType<Test> {
	useJUnitPlatform()
}

jacoco {
	toolVersion = "0.8.5"
	reportsDir = file("$buildDir/jacocoReport")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.jacocoTestReport {
	reports {
        xml.isEnabled = true
		html.isEnabled = true
		html.destination = file("${buildDir}/jacocoReport")
		xml.destination = file("${buildDir}/jacocoReport/jacocoReport.xml")
    }
}

coveralls {
	sourceDirs.add("src/main/kotlin")
	jacocoReportPath = "${buildDir}/jacocoReport/jacocoReport.xml"
}

sonarqube {
  properties {
	  property("sonar.projectKey", "yanBrandao_kot-rpg")
	  property("sonar.version", "${version} - ${Runtime.getRuntime()}")
  }
}
