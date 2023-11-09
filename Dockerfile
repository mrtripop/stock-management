FROM maven:3.9.5-amazoncorretto-17 AS build
COPY /pom.xml /app/pom.xml
COPY /src /app/src
WORKDIR /app
RUN mvn package -Dmaven.test.skip

FROM amazoncorretto:17-alpine
COPY --from=build /app/target/*-SNAPSHOT.jar /app/service.jar
COPY --from=build /app/target/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
WORKDIR /app
CMD ["java","-jar", "-javaagent:opentelemetry-javaagent.jar","service.jar"]