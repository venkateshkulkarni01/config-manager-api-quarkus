# Start from OpenJDK 21 base image
FROM eclipse-temurin:21-jre as runtime

# Set the working directory
WORKDIR /work/

# Copy the built jar from the target directory
COPY target/quarkus-app/lib/ /work/lib/
COPY target/quarkus-app/*.jar /work/
COPY target/quarkus-app/app/ /work/app/
COPY target/quarkus-app/quarkus/ /work/quarkus/

# Expose the default Quarkus HTTP port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "quarkus-run.jar"]