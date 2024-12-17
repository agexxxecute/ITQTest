FROM openjdk:17-jdk-slim

LABEL authors="Egor Nazarev"

WORKDIR /orders-service

COPY target/orders-service-0.0.1-SNAPSHOT.jar orders-service.jar

ENTRYPOINT ["java", "-jar", "orders-service.jar"]