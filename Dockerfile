# Set the base image
FROM adoptopenjdk/openjdk11:alpine-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .

# Copy the Gradle wrapper
COPY gradle/ gradle/

# Copy the source code
COPY src/ src/

# Grant execution permissions to the Gradle wrapper
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "build/libs/VGR-0.0.1-SNAPSHOT.jar"]
