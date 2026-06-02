plugins {
    kotlin("jvm") version "2.3.21"
    application
    id("com.google.cloud.tools.jib") version "3.4.4"
}

application {
    mainClass = "docket.MainKt"
}

repositories {
    mavenCentral()
}

val http4kVersion = "6.50.0.0"

dependencies {
    implementation(platform("org.http4k:http4k-bom:$http4kVersion"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-undertow")
    implementation("org.http4k:http4k-format-jackson")

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

jib {
    from {
        image = "eclipse-temurin:21-jre-alpine"
    }
    to {
        image = "us-central1-docker.pkg.dev/${System.getenv("GCP_PROJECT_ID") ?: "PROJECT_ID"}/docket/docket"
        tags = setOf(System.getenv("IMAGE_TAG") ?: "latest")
        credHelper { helper = "gcloud" }
    }
    container {
        ports = listOf("8080")
        jvmFlags = listOf("-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0")
    }
}
