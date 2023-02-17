FROM maven as build
COPY /pom.xml /app/pom.xml
COPY /src /app/src
WORKDIR /app
RUN mvn package -Dmaven.test.skip

FROM eclipse-temurin:17
COPY --from=build /app/target /app
WORKDIR /app
CMD ["java","-jar","management-0.0.1-SNAPSHOT.jar"]