# Stock Management by Spring Boot

This project aim to learn Spring Boot best practice, architecture design, and improve my skill. Concept of this project
for simulate manage stock system feature to know how it's work and what design is.

## Prerequisite

- [Homebrew](https://brew.sh/)
- [Maven 3.9.5](https://formulae.brew.sh/formula/maven#default)
- [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [Java version manager](https://www.jenv.be/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Google Java Format](https://plugins.jetbrains.com/plugin/8527-google-java-format)
- [SonarLint](https://plugins.jetbrains.com/plugin/7973-sonarlint)
- [CommitLint](https://www.notion.so/Commitlint-on-local-ea1ec27b07b444f5b1b19d1b5506cbbd)
- [GPG signing key](https://www.notion.so/Commit-Signature-Verification-5eff1efc706340149c38ef93d3c58a0d)

## Basic Knowledge

General

- [Conventional Commit](https://www.conventionalcommits.org/en/v1.0.0/)
- [Java Code Style Guild](https://www.cs.cornell.edu/courses/JavaAndDS/JavaStyle.html)

Design Pattern

- [Facade](https://refactoring.guru/design-patterns/facade/java/example)
- [Builder]()

Development

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Postgresql](https://www.postgresql.org/)
- [Unit test in Spring Boot]
- [Integration test in Spring Boot]

Deployment

- [Logging Format - Better Stack](https://betterstack.com/community/guides/logging/log-formatting/)
- [Log Level]
- [OpenTelemetry Logging](https://opentelemetry.io/docs/specs/otel/logs/)
- [Three pillars of Observability](https://www.oreilly.com/library/view/distributed-systems-observability/9781492033431/ch04.html)
- [Setup OpenTelemetry in Spring Boot](https://www.notion.so/Setup-OpenTelemetry-in-Spring-Boot-f273e32194af44fda8e46a7fecea9b4e?pvs=4)
- [Spring Boot with Actuator]
- [Kubernetes]

## Configuration

Go to `src/main/resourse` file `application.yml`<br/>

- Datasource url
    - `localhost` - This for develop not using docker container
    - `postgres` - This for develop using docker container

## How to run

Note: change `datasource config` correctly before start application.

1. Start database container at first.

```shell
docker compose up -d postgres --build
```

2. Check docker image

```shell
docker image ls
```

3. Check docker container running

```shell
docker container ls
```

4. Start application from terminal

```shell
mvn spring-boot:run
```

5. Stop docker compose and remove container

```shell
docker compose down
```

## URLs Resource

In this section, we define the API endpoint & URLs for any resource as a Swagger. Please run the service and access API specification via web browser.
```shell
http://localhost:8080/swagger-ui/index.html
```



