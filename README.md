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

posteriormente si requiere utilizar el contenedor:
```bash
docker <start/stop> my-postgresdb-container
```

### Ejecución del código
El proyecto contempla distintos perfiles de ejecucion
```bash
mvn spring-boot:run
```

### Ejecución de pruebas unitarias
```bash
mvn clean verify
```

### A nivel de desarrollo
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Compilar .jar
```bash
./mvnw clean package
```
Esto creará el .jar en el directorio target/.

### A nivel de produccion
```bash
java -jar universidad-backend.jar --spring.profiles.active=prod
```

Si requiere compilar y ejecutar el proyecto
```bash
maven package
java -jar target/universidad-backend-0.0.2-SNAPSHOT.jar
```

## Documentación del API

Una vez ejecutado el proyecto, revisar: [http://localhost:9081/api/v2/universidad/swagger-ui/index.html](http://localhost:9081/api/v2/universidad/swagger-ui/index.html)

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

- [x] Integrar SonarQube para evaluar el código fuente.
- [ ] Refactorización del código.
- [ ] Integración con JasperReports.

## Autores y reconocimiento
Parte del proyecto pertenece al curso [Spring boot en simples pasos](https://www.udemy.com/course/spring-boot-en-simples-pasos/), agradezco a Matias Macrino por su paciencia y guia en el desarrollo del mismo.

## CICD
Crea vm para automatizacion
```bash
multipass launch -n ci-runner --cpus 2 --mem 4G --disk 20G --cloud-init ci-runner-init.yaml
```
una vez la vm esta lista
```bash
multipass shell ci-runner
cd actions-runner
./config.sh --url https://github.com/alexlizzt/universidad --token <TOKEN>
sudo ./svc.sh install
sudo ./svc.sh start
```
conectarse a la vm y verificar:
```bash
multipass shell ci-runner
java -version
mvn -version
docker --version
```
ejecutar comandos en la vm fuera
```bash
multipass exec ci-runner -- lsb_release -a
```
detener la vm
```bash
multipass stop ci-runner
```
eliminar la vm
```bash
multipass stop ci-runner
multipass purge
```

para ejecutar el analisis, con el servidor de sonar previamente arrancado:
```bash
./mvnw clean verify sonar:sonar \
-Dsonar.projectKey=universidad \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=<tu-token>
```
para integrar jacoco en sonar:
```bash
-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
```