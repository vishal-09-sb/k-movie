# Use OpenJDK 11 as the base image
FROM openjdk:11

# Expose the application on port 8082
EXPOSE 9091

# Copy the jar file to the image
ADD target/com-moviebookingauth-docker.jar com-moviebookingauth-docker.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/com-moviebookingauth-docker.jar"]
