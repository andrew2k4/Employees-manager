# Build stage
FROM openjdk:17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean install -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
