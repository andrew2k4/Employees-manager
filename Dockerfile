# Step 1: Use a base image with JDK 17 or another JDK version compatible with your app
FROM openjdk:17-jdk-alpine

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the JAR file from the local machine to the container
COPY out/artifacts/service_booking_jar /app

# Step 4: Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

# Step 5: Command to run the JAR file
ENTRYPOINT ["java", "-jar", "service-booking.jar"]
