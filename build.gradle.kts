import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

task("runPuzzle1_1", JavaExec::class) {
    main = "day1.Puzzle1"
    classpath = sourceSets["main"].runtimeClasspath
    group = "execute"
}

task("runPuzzle1_2", JavaExec::class) {
    main = "day1.Puzzle2"
    classpath = sourceSets["main"].runtimeClasspath
    group = "execute"
}