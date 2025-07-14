# Etapa 1: build
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

COPY mvnw ./
COPY .mvn/ .mvn
RUN chmod +x mvnw && ./mvnw dependency:go-offline

COPY pom.xml ./
RUN ./mvnw dependency:resolve

COPY src/ ./src/
RUN ./mvnw clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java","-jar","app.jar"]

