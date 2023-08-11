FROM openjdk:19 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:19
WORKDIR hesap-api
COPY --from=build target/*.jar hesap-api.jar
ENTRYPOINT ["java", "-jar", "hesap-api.jar"]