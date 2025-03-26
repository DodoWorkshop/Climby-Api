# Étape de build pour amd64/arm64
FROM --platform=linux/amd64 openjdk:17 AS amd64
FROM --platform=linux/arm64 openjdk:17 AS arm64

# Étape de build pour armv7 (32 bits)
FROM --platform=linux/arm/v7 arm32v7/eclipse-temurin:17 AS armv7

# [Labels]
LABEL maintainer="Donovan Persent"

# [Environment variables]
ENV SERVER_PORT=8080

# [Arguments]
ARG JAR_FILE=target/ClimbyApi-*.jar

# [Actions]
WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

# [Ports]
EXPOSE ${SERVER_PORT}

# [Entry point]
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","app.jar"]