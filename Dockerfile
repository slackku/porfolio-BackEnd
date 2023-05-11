FROM amazoncorretto:11-alpine-jdk
MAINTAINER lorenzoIgnacio
COPY target/API-REST-0.0.1-SNAPSHOT.jar api-app.jar

ENTRYPOINT ["java","-jar","/app.jar"]