FROM maven:3.9.8-eclipse-temurin-21 AS build
RUN mkdir /opt/app
COPY Faculdade /opt/app/Faculdade
WORKDIR /opt/app/Faculdade
RUN mvn clean package
FROM eclipse-temurin:21-jre-alpine
RUN mkdir /opt/app
COPY --from=build /opt/app/Faculdade/target/app.jar /opt/app/app.jar
WORKDIR /opt/app
ENV PROFILE=prd
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]
