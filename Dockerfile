FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado al contenedor
COPY target/universidad-backend-0.0.2-SNAPSHOT.jar app.jar

# Expone el puerto que usa la app
EXPOSE 9081

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]