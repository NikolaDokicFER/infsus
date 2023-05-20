FROM adoptopenjdk/openjdk11:alpine-slim
ADD build/libs/book-exchange-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]# Use an official OpenJDK 11 Alpine-based image as the base image
FROM adoptopenjdk/openjdk11:alpine-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle files and the source code to the container
COPY build.gradle .
COPY src ./src

# Build the application with Gradle
RUN ./gradlew build

# Copy the compiled JAR file to the container
COPY build/libs/VGR-0.0.1-SNAPSHOT.jar app.jar

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
