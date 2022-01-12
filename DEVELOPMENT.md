# Poker Hand Sorter

## Requirements

* Java 11
* Direct internet access / Apache Maven proxy configured

## Building

This project is built using [Maven Wrapper](https://github.com/takari/maven-wrapper).

### Building Java only (no Docker image)

#### Linux/macOS

```bash
./mvnw install
```

#### Windows

```bash
mvnw.cmd install
```

### Building to your local Docker daemon

#### Linux/macOS

```bash
./mvnw install -P docker-local
```

#### Windows

```bash
mvnw.cmd install -P docker-local
```

### Building to a remote Docker registry

First you need to change the `docker.image` property in the `pom.xml` to specify the registry.

```xml
<properties>
  ...
  <docker.image>e.g. gcr.io/my-gcp-project/my-app</docker.image>
  ...
</properties>
```

You'll likely need to authenticate with your Docker registry, follow the
[instructions here](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#authentication-methods).

Once you've configured the authentication for your Docker registry run the following command to
build and push the Docker image (this doesn't require a local Docker daemon):

#### Linux/macOS

```bash
./mvnw install -P docker-registry
```

#### Windows

```bash
mvnw.cmd install -P docker-registry
```

## Security scanning

This project includes the [SpotBugs Maven Plugin](https://spotbugs.github.io/spotbugs-maven-plugin)
with the [Find Security Bugs](https://find-sec-bugs.github.io) plugin for performing static analysis
on your code. The static analysis can be quite time consuming so it's not run by default.

To run the security scan run the following from the project root:

#### Linux/macOS

```bash
./mvnw clean install
./mvnw spotbugs:spotbugs -P find-sec-bugs
```

#### Windows

```bash
mvnw.cmd clean install
mvnw.cmd spotbugs:spotbugs -P find-sec-bugs
```

To view the results run the following from each Maven module directory:

#### Linux/macOS

```bash
./mvnw spotbugs:gui -P find-sec-bugs
```

#### Windows

```bash
mvnw.cmd spotbugs:gui -P find-sec-bugs
```
