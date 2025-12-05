# Breaking Bad Backend – API de Frases Aleatorias

Backend en **Spring Boot 3 + Java 17**, conectado a **Cloud SQL (MySQL)** y desplegado en **Cloud Run**, usando contenedores Docker almacenados en **Artifact Registry**.

Este proyecto sirve como ejemplo de arquitectura cloud-native en Google Cloud Platform y demuestra cómo migrar un backend local a una infraestructura serverless administrada.

---

## Características principales

- API REST construida con **Spring Boot 3**
- Conexión segura a **Cloud SQL (MySQL)** mediante Cloud SQL Socket Factory
- Despliegue serverless en **Cloud Run**
- Imagen Docker almacenada en **Artifact Registry**
- Uso de **variables de entorno** para credenciales
- Documentación OpenAPI/Swagger incluida

---

## Endpoint principal

### GET `/api/frases/aleatoria`

Devuelve una frase aleatoria de la serie *Breaking Bad*.

**Ejemplo de respuesta:**

```json
{
  "id": 41,
  "texto": "Jesse Pinkman - Yo no soy un asesino, Sr. White"
}
```

---

## Documentación OpenAPI

Archivo YAML público (Swagger):  
https://editor.swagger.io/?url=https://gist.githubusercontent.com/jovaslink/095b5821a41abf3f0b1759dd894a680a/raw/53f6e678484ea2c5df6708cf2e4baf44d12acfb7/breakingbad-frases.yaml

---

## Configuración (`application.properties`)

```properties
spring.datasource.url=jdbc:mysql:///breakingbad?cloudSqlInstance=breakingbad-project:us-central1:mysql-breakingbad&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.initializationFailTimeout=0
spring.jpa.defer-datasource-initialization=true
spring.sql.init.continue-on-error=true
```

---

## Dependencias principales (`pom.xml`)

```xml
<dependency>
    <groupId>com.google.cloud.sql</groupId>
    <artifactId>mysql-socket-factory-connector-j-8</artifactId>
    <version>1.13.1</version>
</dependency>

<dependency>
    <groupId>com.google.cloud.sql</groupId>
    <artifactId>mysql-socket-factory</artifactId>
    <version>1.13.1</version>
</dependency>
```

---

## Dockerfile

```dockerfile
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
```

---

## Despliegue en Cloud Run

```bash
gcloud run deploy breakingbad-backend   --image=us-central1-docker.pkg.dev/PROJECT_ID/backend-repo/breakingbad-backend   --region=us-central1   --platform=managed   --allow-unauthenticated   --port=8080   --set-cloudsql-instances=PROJECT_ID:us-central1:mysql-breakingbad   --set-env-vars=DB_USER=root,DB_PASS=1234
```

---

## Publicación en Artifact Registry

```bash
docker build -t breakingbad-backend .
docker tag breakingbad-backend us-central1-docker.pkg.dev/PROJECT_ID/backend-repo/breakingbad-backend
docker push us-central1-docker.pkg.dev/PROJECT_ID/backend-repo/breakingbad-backend
```

---

## Documentación oficial

- Cloud Run: https://cloud.google.com/run/docs  
- Cloud SQL MySQL: https://cloud.google.com/sql/docs/mysql/connect-run  
- Cloud SQL Connector: https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory  
- Artifact Registry: https://cloud.google.com/artifact-registry/docs  
- Docker Desktop: https://www.docker.com/products/docker-desktop/  
- Google Cloud SDK: https://cloud.google.com/sdk/docs/install  

---

## Estado del proyecto

Esta es una versión inicial desarrollada como práctica de despliegue en Google Cloud Platform. Se planean mejoras adicionales como soporte para PostgreSQL, Oracle y automatización con Cloud Build o GitHub Actions.

---

## Desarrollado

jhovanny.martinez@landsoft.mx

