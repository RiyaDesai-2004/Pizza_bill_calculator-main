

# Use a valid OpenJDK image (Java 18 compatible)
FROM eclipse-temurin:18-jdk 

# Set working directory inside container
WORKDIR /app

# Copy all project files into container
COPY . /app

# Compile the Java files
RUN javac Driver.java

# Run the program
CMD ["java", "Driver"]

