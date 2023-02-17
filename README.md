# Stock Management by Spring Boot

This project aim to learn best practice in Spring Boot and improve my skill. Concept of this project for simulate manage
stock system form optimize workload efficiency.

## Prerequisite

- Homebrew
- Maven
- Java JDK 17
- Docker

## Basic Knowledge

- Development
    - Postgresql
    - Spring Boot 3
    - Redis Cache

- Monitoring
    - Logging
    - Tracing
    - Metric

- Observability
    - OpenTelemetry
    - Micrometer

## Configuration

Go to `src/main/resourse` file `application.yml`<br/>

- Datasource url
    - `localhost` - This for develop not using docker container
    - `postgres` - This for develop using docker container

## How to run on `localhost`

Before start service, start database container at first.

```aidl
docker compose up -d postgres
```

Then start service

```aidl
mvn spring-boot:run
```

Note: `change config` of our application correctly before start our service.

## How to run on `docker-container`

Note: `change config` of our application correctly before start our service.

Build deployment package such as `.jar` file

```aidl
mvn package -Dmaven.test.skip
```

Then run docker compose

```aidl
docker compose up --build
```

Check docker image

```aidl
docker image ls
```

Check docker container running

```aidl
docker container ls
```

Stop and remove all container that run by docker-compose

```aidl
docker compose down
```

## How request to endpoint

Get all customer

```aidl
curl localhost:8080/api/v1/customer
```

## Appendix

Terminal: https://iterm2.com/ <br/>
Homebrew: https://brew.sh/ <br/>
Jenv: https://www.jenv.be/ <br/>
Postman: https://www.postman.com/downloads/ <br/>