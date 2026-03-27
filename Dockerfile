# ---- Build stage ----
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw && ./mvnw dependency:go-offline -q

COPY src src
RUN ./mvnw package -DskipTests -q

# ---- Runtime stage ----
FROM busybox:stable

ENV JAVA_HOME=/opt/java/25
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=builder /opt/java/openjdk $JAVA_HOME

RUN addgroup -S spring && adduser -S -G spring spring

WORKDIR /app

COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
RUN chown spring:spring app.jar

USER spring

EXPOSE 8080 8081 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
