# Use an official OpenJDK runtime as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file (built using Maven or Gradle) into the container
COPY target/your-microservice.jar /app/

# Expose the port your microservice listens on (replace 8080 with your actual port)
EXPOSE 8080

# Set the command to run your microservice when the container starts
CMD ["java", "-jar", "your-microservice.jar"]
