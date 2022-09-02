FROM eclipse-temurin:11.0.16.1_1-jre

WORKDIR /app

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
