# FROM openjdk:11-ea-17-jre-slim
FROM amazoncorretto:17.0.8-alpine
LABEL maintainer="terencema@nmbz.co.zw"
VOLUME /bonvie-app
ADD app/target/bonviebackend-0.0.1-SNAPSHOT.jar bonvie-app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/bonvie-app.jar"]