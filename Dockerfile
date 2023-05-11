FROM amazoncorretto:17-alpine-jdk
MAINTAINER lorenzoIgnacio
COPY target/API-REST-0.0.1-SNAPSHOT.jar api-app.jar

ENTRYPOINT ["java","-jar","/api-app.jar"]