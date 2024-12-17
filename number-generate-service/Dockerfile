FROM openjdk:17-jdk-slim

LABEL authors="Egor Nazarev"

WORKDIR /number-generate-service

COPY target/number-generate-service-0.0.1-SNAPSHOT.jar number-generate-service.jar

ENTRYPOINT ["java", "-jar", "number-generate-service.jar"]