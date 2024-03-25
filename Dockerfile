FROM openjdk:17-jdk-slim

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/user-service-1.0-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

