# How to build and publish to JFrog Artifactory

* Generate `settings.xml` maven config on JFrog Artifactory UI
* Store `settings.xml` into `~/.m2/settings.xml`
* Install adopt open jdk 8 and 11
  * For example using sdkman `sdk install java 8.0.252.hs-adpt` and `sdk install java 11.0.8.hs-adpt`
* Create `~/.m2/toolchains.xml` with correct path to installed jdk8 and jdk11
  * For example
```xml
<?xml version="1.0" encoding="UTF8"?>
<toolchains>
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>1.8</version>
    </provides>
    <configuration>
      <jdkHome>${env.HOME}/.sdkman/candidates/java/8.0.252.hs-adpt</jdkHome>
    </configuration>
  </toolchain>
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>11</version>
    </provides>
    <configuration>
      <jdkHome>${env.HOME}/.sdkman/candidates/java/11.0.8.hs-adpt</jdkHome>
    </configuration>
  </toolchain>
</toolchains>
```
* Build whole project `./mvnw install`
  * Build only core `./mvnw install -pl log4j-core -am`
  * Build only core with tests skipped `./mvnw install -pl log4j-core -am -Dmaven.test.skip=true`
* Release project to artifactory
  * Copy `distributionManagement` from Artifactory UI and add it into `pom.xml` and `log4j-bom/pom.xml`
    * Should look similar to
```xml
  <distributionManagement>
    <repository>
      <id>central</id>
      <name>acme-releases</name>
      <url>https://acme.jfrog.io/artifactory/libs-release-local</url>
    </repository>
    <snapshotRepository>
      <id>snapshots</id>
      <name>acme-snapshots</name>
      <url>https://company.acme.io/artifactory/libs-snapshot-local</url>
    </snapshotRepository>
  </distributionManagement>
```
  * Update versions in all `pom.xml` files
  * Run `./mvnw deploy` to publish all
    * Run `./mvnw deploy -pl log4j-core -am` to publish only core
