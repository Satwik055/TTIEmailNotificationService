plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.jan-tennert.supabase:postgrest-kt:3.1.1")
    implementation("io.github.jan-tennert.supabase:storage-kt:3.1.1")
    implementation("io.github.jan-tennert.supabase:realtime-kt:3.1.1")
    implementation("io.ktor:ktor-utils:3.0.3")
    implementation("io.ktor:ktor-client-cio:3.0.3")
    implementation("io.ktor:ktor-client-core:3.0.3")
    implementation("com.resend:resend-java:3.1.0") // Replace with the correct version


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}