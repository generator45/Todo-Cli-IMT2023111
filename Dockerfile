# Use a Java runtime image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built jar from Jenkins workspace into image
# (Jenkins will run 'mvn package' first so this file exists)
COPY target/todo-cli-java-1.0-SNAPSHOT.jar app.jar

# Default command: run the CLI
CMD ["java", "-jar", "app.jar"]
