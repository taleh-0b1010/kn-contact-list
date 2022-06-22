# kn-contact-list

## How to Run:

### 1. Run docker-compose file
This will instantiate a postgresql database inside a docker container.
- cd /infra
- docker-compose up
### 2. Start application with "local" env to run locally
All database tables will be created and initial data will be loaded to the database during start up.
#### NOTE: In case of modifying db schema, add new change-set or delete all images and volumes and recreate using commands:
- docker container prune
- docker volume ls
- docker volume rm --force <volume_name>
## Stack:
- Java 11
- Spring Boot
- Spring Data JPA
- docker, docker-compose
- Postgresql
- Liquibase Migration
- Mapstruct
- Testing
  - JUnit5
  - Mockito
  - AssertJ
  - Spring Boot Test
- Gradle
- Swagger
