plugins {
    kotlin("jvm") version "1.9.22"
    `java-library`
    `maven-publish`
}

group = "com.github.tylerwilbanks"
version = "0.1"

private val kotlinVersion: String by project
private val junitVersion: String by project
private val junitVintageEngineVersion: String by project

repositories {
    mavenCentral()
}

//publishing {
//    publishing {
//        publications {
//            create<MavenPublication>("maven") {
//                groupId = "minutesock.entity-flow"
//                artifactId = "entity-flow"
//                version = "0.1"
//                from(components["java"])
//            }
//        }
//    }
//}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    testImplementation("junit:junit:$junitVersion")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:$junitVintageEngineVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}