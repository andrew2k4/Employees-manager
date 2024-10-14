
FROM openjdk:22-jdk-alpine


LABEL maintainer="your.email@example.com"


VOLUME /tmp


EXPOSE 8080


ARG JAR_FILE=target/spring-boot-app-1.0.0.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]