FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
COPY ./src/main/resources/application.properties application.properties
ENTRYPOINT ["java","-jar","/app.jar"]

