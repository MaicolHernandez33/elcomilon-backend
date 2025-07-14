# stage 1: build
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copiar archivos directamente desde la raíz (ya no hay subcarpeta backend)
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Pre-descargar dependencias
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copiar código fuente
COPY src ./src

# Empaquetar aplicación
RUN ./mvnw clean package -DskipTests

# stage 2: runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el JAR
COPY --from=build /app/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]
