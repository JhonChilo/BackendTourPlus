# Usa una imagen base de JDK para compilar y correr la aplicación
FROM openjdk:17-jdk-alpine

# Argumento para el JAR generado
ARG JAR_FILE=target/*.jar

# Copia el JAR dentro de la imagen
COPY ${JAR_FILE} app.jar

# Ejecuta la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]
