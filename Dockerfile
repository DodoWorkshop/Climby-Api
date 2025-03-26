FROM eclipse-temurin:17

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