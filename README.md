# 🎓 Universidad

API REST para administrar la información de una universidad, desarrollada con Spring Boot.

---

## 📦 Tecnologías y herramientas

- Java 11
- Spring Boot 2.5.6
- PostgreSQL (vía Docker)
- Maven
- JUnit 5
- Mockito + H2 (pruebas de integración)
- Swagger (documentación de API REST)
- MapStruct (DTOs)
- Lombok (requerido en el IDE)
- Docker Compose
- SonarQube + Jacoco (análisis de calidad de código)
- GitHub Actions + Multipass (CI/CD)

---

## 🚀 Requisitos previos

Antes de ejecutar este proyecto, asegúrate de tener instalado:

- [Java 11](https://adoptium.net)
- [Maven](https://maven.apache.org/)
- [Docker & Docker Compose](https://docs.docker.com/get-docker/)
- [Multipass](https://multipass.run/) (para CI/CD opcional)
- Plugin de Lombok en tu IDE ([Descargar aquí](https://projectlombok.org/download))

---

## 🐘 Base de datos (PostgreSQL con Docker)

Para levantar el contenedor de PostgreSQL inicializado:

```bash
git clone https://github.com/Alelizzt/universidad.git
cd universidad
docker compose up -d db
```

Para iniciar el contenedor nuevamente en el futuro:
```bash
docker compose up -d db
```
---
## ▶️ Ejecución del proyecto

### Especificar perfil manualmente

#### Ejecutar proyecto:
Puede arrancar el proyecto con el perfil desarrollador para facilitar el debugging.
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
O por el contrario arrancar el proyecto en modo produccion y ocultar informacion del proyecto.
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```
Si solo quiere ejecutar el proyecto sin compilarlo, utilice el fichero .jar
```bash
java -jar target/universidad-backend.jar --spring.profiles.active=prod
```
---
## 🧪 Ejecutar pruebas
### Unitarias e integración
```bash
./mvnw clean verify
```
## 🏗️ Compilar proyecto
Para generar el fichero JAR
```bash
./mvnw clean package
```
El archivo .jar se generará en el directorio target/.

### Ejecutar el .jar:
```bash
java -jar target/universidad-backend-0.0.2-SNAPSHOT.jar
```
---
## 📌 Endpoints principales del API

| Método | Endpoint                                      | Descripción                                 |
|--------|-----------------------------------------------|---------------------------------------------|
| GET    | `/api/v2/universidad/carreras`                | Listar todas las carreras                   |
| GET    | `/api/v2/universidad/carreras/{id}`           | Obtener una carrera por ID                  |
| POST   | `/api/v2/universidad/carreras`                | Crear una nueva carrera                     |
| PUT    | `/api/v2/universidad/carreras/{id}`           | Actualizar una carrera                      |
| DELETE | `/api/v2/universidad/carreras/{id}`           | Eliminar una carrera                        |
| GET    | `/api/v2/universidad/alumnos`                 | Listar todos los alumnos                    |
| GET    | `/api/v2/universidad/alumnos/{id}`            | Obtener un alumno por ID                    |
| POST   | `/api/v2/universidad/alumnos`                 | Crear un nuevo alumno                       |
| PUT    | `/api/v2/universidad/alumnos/{id}`            | Actualizar un alumno                        |
| DELETE | `/api/v2/universidad/alumnos/{id}`            | Eliminar un alumno                          |
| GET    | `/api/v2/universidad/profesores`              | Listar todos los profesores                 |
| GET    | `/api/v2/universidad/profesores/{id}`         | Obtener un profesor por ID                  |
| POST   | `/api/v2/universidad/profesores`              | Crear un nuevo profesor                     |
| PUT    | `/api/v2/universidad/profesores/{id}`         | Actualizar un profesor                      |
| DELETE | `/api/v2/universidad/profesores/{id}`         | Eliminar un profesor                        |
| GET    | `/api/v2/universidad/aulas`                   | Listar todas las aulas                      |
| GET    | `/api/v2/universidad/aulas/{id}`              | Obtener un aula por ID                      |
| POST   | `/api/v2/universidad/aulas`                   | Crear una nueva aula                        |
| PUT    | `/api/v2/universidad/aulas/{id}`              | Actualizar un aula                          |
| DELETE | `/api/v2/universidad/aulas/{id}`              | Eliminar un aula                            |
| GET    | `/api/v2/universidad/pabellones`              | Listar todos los pabellones                 |
| GET    | `/api/v2/universidad/pabellones/{id}`         | Obtener un pabellón por ID                  |
| POST   | `/api/v2/universidad/pabellones`              | Crear un nuevo pabellón                     |
| PUT    | `/api/v2/universidad/pabellones/{id}`         | Actualizar un pabellón                      |
| DELETE | `/api/v2/universidad/pabellones/{id}`         | Eliminar un pabellón                        |
---
## 📑 Documentación del API

Una vez iniciado el proyecto, accede a la documentación de Swagger en:

http://localhost:9081/api/v2/universidad/swagger-ui/index.html

---
## 🗂️ Estructura del proyecto

```
universidad/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── gitlab/
│   │   │           └── alelizzt/
│   │   │               └── universidad/
│   │   │                   ├── universidadbackend/
│   │   │                   │   ├── controladores/
│   │   │                   │   ├── modelos/
│   │   │                   │   ├── repositorios/
│   │   │                   │   ├── servicios/
│   │   │                   │   └── UniversidadBackendApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       └── java/
│           └── com/
│               └── gitlab/
│                   └── alelizzt/
│                       └── universidad/
│                           └── universidadbackend/
│                               └── (tests)
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

- **controladores/**: Controladores REST (endpoints del API)
- **modelos/**: Entidades JPA y DTOs
- **repositorios/**: Interfaces de acceso a datos (Spring Data JPA)
- **servicios/**: Lógica de negocio y servicios
- **resources/**: Configuración y recursos estáticos
- **test/**: Pruebas unitarias y de integración

---
## 🛠️ CI/CD con Multipass

Crear una VM para el runner:
```bash
multipass launch -n ci-runner --cpus 2 --mem 4G --disk 20G --cloud-init ci-runner-init.yaml
```
Acceder a la VM:
```bash
multipass shell ci-runner
```
Configurar GitHub Actions Runner:
```bash
cd actions-runner
./config.sh --url https://github.com/alexlizzt/universidad --token <TOKEN>
sudo ./svc.sh install
sudo ./svc.sh start
```
Verificar herramientas dentro de la VM:
```bash
java -version
mvn -version
docker --version
```
Comandos útiles:

- Ejecutar comandos desde fuera:
```bash
multipass exec ci-runner -- lsb_release -a
```
- Detener la VM:
```bash
multipass stop ci-runner
```
- Eliminar la VM:
```bash
multipass stop ci-runner
multipass purge
```
---
## 📊 Análisis de código con SonarQube
Con SonarQube corriendo en local:
```bash
./mvnw clean verify sonar:sonar \
  -Dsonar.projectKey=universidad \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=<tu-token>
```
Si desea integrar la compatibilidad con Jacoco, agrege la siguiente linea al final del comando anterior:
```bash
-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
```
---
## 🗺️ Roadmap
- [x] Integrar SonarQube para evaluar el código fuente.
- [ ] Refactorización del código.
- [ ] Integración con JasperReports.
---
## 🤝 Contribuciones

¡Las contribuciones son bienvenidas!

Para contribuir:
1. Haz un fork del repositorio
2. Crea una nueva rama: `git checkout -b feature/nueva-funcionalidad`
3. Realiza tus cambios
4. Abre un Pull Request explicando el cambio

Por favor, asegúrate de seguir la estructura del proyecto y escribir pruebas si es necesario.

---
## 👨‍🏫 Créditos

Este proyecto parte de una base realizada en el curso
[Spring boot en simples pasos](https://www.udemy.com/course/spring-boot-en-simples-pasos/) de Matias Macrino. Ha sido modificado para profundizar y ampliar su alcance.

---
## 🧾 Licencia

Este proyecto está licenciado bajo la Licencia MIT.