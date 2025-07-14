# --------- Stage 1: Build ---------
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copiamos los archivos necesarios para compilar
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Pre-descargamos dependencias para mejorar la caché
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Ahora copiamos el código fuente
COPY src ./src

# Compilamos el proyecto y generamos el JAR
RUN ./mvnw clean package -DskipTests

# --------- Stage 2: Runtime ---------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiamos el JAR generado desde el stage anterior
COPY --from=build /app/target/*.jar app.jar

# Activamos perfil si usas uno (opcional)
ENV SPRING_PROFILES_ACTIVE=prod

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
