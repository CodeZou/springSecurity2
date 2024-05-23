FROM openjdk:17-jdk-slim-buster
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} applic.jar
ENTRYPOINT ["java","-jar","/applic.jar"]