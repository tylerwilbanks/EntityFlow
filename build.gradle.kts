plugins {
    kotlin("jvm") version "1.9.22"
    `java-library`
    `maven-publish`
}

group = "minutesock.entity-flow" // io.github.tylerwilbanks
version = "0.1"

private val kotlinVersion: String by project
private val junitVersion: String by project
private val junitVintageEngineVersion: String by project

repositories {
    mavenCentral()
}

//publishing {
//    publications.create<MavenPublication>("lib").from(components["java"])
//    repositories.maven("ht")
//
//    publishing {
//        publications {
//            create<MavenPublication>("maven") {
//                groupId = "minutesock.entity-flow"
//                artifactId = "library"
//                version = "0.1"
//                from(components["java"])
//            }
//        }
//    }
//
//
//
//    repositories {
//        maven {
//            credentials {
//                username = "$usr"
//                password = "$pwd"
//            }
//
//            url = "https://maven.pkg.jetbrains.space/mycompany/p/projectkey/my-maven-repo"
//        }
//        }
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