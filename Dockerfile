# stage 1: build
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copiar pom, mvnw y configuración de Maven desde el subdirectorio backend
COPY backend/pom.xml backend/mvnw ./
COPY backend/.mvn .mvn

# Pre-descargar dependencias
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copiar el código fuente de la aplicación
COPY backend/src ./src

# Empaquetar la aplicación
RUN ./mvnw clean package -DskipTests

# stage 2: runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar JAR generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Perfil de producción
ENV SPRING_PROFILES_ACTIVE=prod

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
