FROM openjdk:22
WORKDIR /app
COPY target/bootcamp-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
