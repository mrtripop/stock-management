# Stock Management by Spring Boot

This project aim to learn best practice in Spring Boot and improve my skill. Concept of this project for simulate manage
stock system form optimize workload efficiency.

## Prerequisite

- [Homebrew](https://brew.sh/)
- [Maven 3.9.5](https://formulae.brew.sh/formula/maven#default)
- [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [Java version manager](https://www.jenv.be/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Google Java Format](https://plugins.jetbrains.com/plugin/8527-google-java-format)
- [SonarLint](https://plugins.jetbrains.com/plugin/7973-sonarlint)
- [CommitLint](https://www.notion.so/Commitlint-on-local-ea1ec27b07b444f5b1b19d1b5506cbbd)

## Basic Knowledge

First start

- [Conventional Commit](https://www.conventionalcommits.org/en/v1.0.0/)
- [Java Code Style Guild](https://www.cs.cornell.edu/courses/JavaAndDS/JavaStyle.html)

Development

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Postgresql](https://www.postgresql.org/)
- [Three pillars of Observability](https://www.oreilly.com/library/view/distributed-systems-observability/9781492033431/ch04.html)
- [OpenTelemetry Logging](https://opentelemetry.io/docs/specs/otel/logs/)

## Step Development

- CRUD API for interact with `Postgres` database
- Setting `docker-compose` for simulate monitoring
    - OpenTelemetry
    - Grafana Tempo
    - Grafana
- Implement `Micrometer` for compare with `OpenTelemetry`
    - Detail between both
    - Log format with log standard

## Configuration

Go to `src/main/resourse` file `application.yml`<br/>

- Datasource url
    - `localhost` - This for develop not using docker container
    - `postgres` - This for develop using docker container

## How to run on `localhost`

Before start service, start database container at first.

```shell
docker compose up -d postgres --build
```

Then start service

```shell
mvn spring-boot:run
```

Note: `change config` of our application correctly before start our service.

## How to run on `docker-container`

Note: `change config` of our application correctly before start our service.

Build deployment package such as `.jar` file

```shell
mvn package -Dmaven.test.skip
```

Then run docker compose

```shell
docker compose up --build
```

Check docker image

```shell
docker image ls
```

Check docker container running

```shell
docker container ls
```

Stop and remove all container that run by docker-compose

```shell
docker compose down
```

## How request to endpoint

Get all customer

```shell
curl localhost:8080/api/v1/customer
```

## Appendix

Terminal: https://iterm2.com/ <br/>
Homebrew: https://brew.sh/ <br/>
Jenv: https://www.jenv.be/ <br/>
Postman: https://www.postman.com/downloads/ <br/>