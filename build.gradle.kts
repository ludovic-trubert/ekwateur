plugins {
  java
}

group = "ekawateur"

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

repositories {
  mavenLocal()
  mavenCentral()
}

dependencies {

  // Versions
  val lombokVersion = "1.18.30"
  val junitVersion = "5.10.2"
  val assertjVersion = "3.25.2"

  // libs
  compileOnly("org.projectlombok:lombok:$lombokVersion")
  annotationProcessor("org.projectlombok:lombok:$lombokVersion")

  // Tests
  testImplementation("org.junit.jupiter:junit-jupiter:${junitVersion}")
  testImplementation("org.assertj:assertj-core:${assertjVersion}")
  testCompileOnly("org.projectlombok:lombok:${lombokVersion}")
  testAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")
}

tasks.getByName<Test>("test") {
  useJUnitPlatform()
}
