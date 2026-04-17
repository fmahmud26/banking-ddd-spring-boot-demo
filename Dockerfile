# Stage 1: Download dependencies
FROM eclipse-temurin:25-jdk-alpine AS dependencies

RUN apk add --no-cache maven
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

# Stage 2: Build the application
FROM dependencies AS builder
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 3: Runtime image (lightweight)
FROM eclipse-temurin:25-jre-alpine AS runtime
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY --from=builder /build/${JAR_FILE} app.jar

# Create non-root user (UID 10001)
RUN addgroup -g 10001 appgroup && \
    adduser -D -u 10001 -G appgroup appuser && \
    chown -R 10001:10001 /app

USER 10001
ENTRYPOINT ["java", "-jar", "app.jar"]
