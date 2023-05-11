FROM amazoncorretto:17

MAINTAINER ignaciolorenzo

COPY target/API-REST-0.0.1-SNAPSHOT.jar API-REST-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/API-REST-0.0.1-SNAPSHOT.jar"]