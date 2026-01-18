# Stage 1: Download the dependencies
FROM eclipse-temurin:25-jdk-alpine AS dependencies
RUN apk add --no-cache maven
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline


# Stage 2: Build the application
FROM dependencies AS builder
COPY src ./src
RUN mvn clean package -DSkipTests

# Stage 3: Create a LightWeight runtime image
FROM eclipse-temurin:25-jre-alpine AS runtime
WORKDIR /app
ARG JAR_FILE=*.jar
COPY --from=builder /build/target/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]