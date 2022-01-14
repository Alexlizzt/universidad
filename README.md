# Universidad

Api REST para administrar la información de una Universidad con SpringBoot

## Despliegue del proyecto

### Servicio de base de datos
Para lograr ejecutar el proyecto con éxito, se proporciona un contenedor con la base de datos postgres inicializada
```bash
git clone https://github.com/Alelizzt/universidad.git
cd universidad
docker build -t my-postgres-db ./
docker run -d --name my-postgresdb-container -p 5432:5432 my-postgres-db
```

### Ejecución del código
```bash
mvn spring-boot:run
```

Si requiere compilar y ejecutar el proyecto
```bash
maven package
java -jar target/universidad-backend-0.0.1-SNAPSHOT.jar
```

## Documentación del API

Una vez ejecutado el proyecto, revisar: [http://localhost:9081/api/v2/universidad/swagger-ui](http://localhost:9081/api/v2/universidad/swagger-ui)

---
## Especificaciones técnicas
- Spring boot 2.5.6
- Docker (Postgresql database)
- Java 11
- Pruebas unitarias con JUnit5
- Pruebas de integración con Mockito y H2
- Mapstruct para el mapeo de objetos y concepto de DTO
- Se debe tener configurado el IDE con Lombok. Descargar [aqui](https://projectlombok.org/download)
- Documentación de API Rest con Swagger

## Roadmap

- [ ] Integrar SonarQube para evaluar el código fuente.
- [ ] Refactorización del código.
- [ ] Integración con JasperReports.
- [ ] Agregar FrontEnd para consumir el servicio.
- [ ] Pruebas unitarias del FrontEnd.

## Autores y reconocimiento
Parte del proyecto pertenece al curso [Spring boot en simples pasos](https://www.udemy.com/course/spring-boot-en-simples-pasos/), agradezco a Matias Macrino por su paciencia y guia en el desarrollo del mismo.

## Estado del proyecto
![coverage](https://img.shields.io/badge/coverage-40%25-yellowgreen)