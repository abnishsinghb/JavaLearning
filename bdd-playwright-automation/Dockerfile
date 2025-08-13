# Multi-stage Dockerfile for MongoDB Integration Tests CI/CD
# Stage 1: Build environment
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy Maven configuration files first (for better caching)
COPY pom.xml .

# Download dependencies with retry and better error handling
RUN mvn dependency:resolve dependency:resolve-sources -B || \
    mvn dependency:resolve dependency:resolve-sources -B -U || \
    mvn dependency:resolve -B

# Copy source code
COPY src ./src

# Compile the application
RUN mvn clean compile test-compile -B

# Stage 2: Test execution environment
FROM maven:3.9.4-eclipse-temurin-17 AS test-runner

# Install additional tools for CI/CD
RUN apt-get update && apt-get install -y \
    curl \
    wget \
    python3 \
    python3-pip \
    docker.io \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy from builder stage
COPY --from=builder /app .
COPY --from=builder /root/.m2 /root/.m2

    # Copy specific scripts if they exist
    COPY run-tests.sh run-demoqa-tests.sh ./
    RUN chmod +x *.sh || true
    
    # Create directories for reports
RUN mkdir -p target/surefire-reports target/site/allure-maven-plugin allure-results

# Set environment variables
# Set Maven options for Java 17 (removed MaxPermSize as it's not valid in Java 17)
ENV MAVEN_OPTS="-Xmx1024m -XX:MetaspaceSize=256m"
ENV DOCKER_HOST=tcp://docker:2376
ENV DOCKER_TLS_CERTDIR=""

# Default command
CMD ["./run-tests.sh"]

# Stage 3: Production/Report server
FROM nginx:alpine AS report-server

# Install Python for serving Allure reports
RUN apk add --no-cache python3 py3-pip curl

# Copy Allure reports
COPY --from=test-runner /app/target/site/allure-maven-plugin /usr/share/nginx/html/allure
COPY --from=test-runner /app/target/surefire-reports /usr/share/nginx/html/surefire

# Copy custom nginx configuration
COPY docker/nginx.conf /etc/nginx/nginx.conf

# Expose port
EXPOSE 80

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD curl -f http://localhost/ || exit 1

CMD ["nginx", "-g", "daemon off;"]
