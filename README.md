# Emprestimo API

API REST para gerenciamento de empréstimos, materiais e usuários.

## Tecnologias \- Stack
\- Java 21  
\- Spring Boot 3.5.6  
\- Spring Data JPA  
\- Spring Security  
\- Spring Validation  
\- Spring Web  
\- OpenAPI / Swagger (springdoc)  
\- JWT (jjwt)  
\- PostgreSQL (driver runtime)  
\- Lombok (opcional)  
\- Maven

## Endpoints (resumo)
\- `/api/auth` — autenticação / geração de token JWT  
\- `/api/usuarios` — CRUD usuários  
\- `/api/materiais` — CRUD materiais  
\- `/api/emprestimos` — gerenciamento de empréstimos  
\- `/swagger-ui/index.html` — UI do Swagger (springdoc)  
\- `/v3/api-docs` — especificação OpenAPI

## Estrutura do projeto
\- `src/main/java` — código-fonte (controllers, services, repositories, models, dto, config, etc.)  
\- `src/main/resources` — `application.properties`, templates, estáticos  
\- `pom.xml` — dependências e plugins

## Dependências principais
\- org.springframework.boot:spring-boot-starter-data-jpa  
\- org.springframework.boot:spring-boot-starter-security  
\- org.springframework.boot:spring-boot-starter-validation  
\- org.springframework.boot:spring-boot-starter-web  
\- org.springdoc:springdoc-openapi-starter-webmvc-ui: 2.8.14  
\- io.jsonwebtoken:jjwt-api: 0.11.5  
\- io.jsonwebtoken:jjwt-impl: 0.11.5 (runtime)  
\- io.jsonwebtoken:jjwt-jackson: 0.11.5 (runtime)  
\- org.postgresql:postgresql (runtime)  
\- org.projectlombok:lombok (opcional)  
\- org.springframework.boot:spring-boot-starter-test (test)  
\- org.springframework.security:spring-security-test (test)

## Pré-requisitos
\- JDK 21 instalado  
\- Maven
\- Git  
\- Banco de dados (ex.: PostgreSQL) configurado conforme `src/main/resources/application.properties`

## Instalação e execução (Windows)
1. Clonar o repositório:
2. Build:
     ```
     mvn clean package
     ```
3. Executar:
   \- Via Spring Boot:
     ```
     mvn spring-boot:run
     ```

## Variáveis de ambiente / configuração
Editar `src/main/resources/application.properties` ou definir variáveis de ambiente para:
\- `spring.datasource.url`  
\- `spring.datasource.username`  
\- `spring.datasource.password`  
\- `spring.jpa.hibernate.ddl-auto` (ex.: update, validate)  
\- `jwt.secret` (ou a propriedade que você usa para JWT)  
\- `server.port` (opcional)


## Contribuição
\- Abrir uma issue descrevendo a melhoria/bug.  

## Agradecimento
Esse foi o final do meu Trabalho de Conclusão de Curso na Universidade Federal Rural do Semi-Árido (Ufersa), gostaria de comentar que foi um prazer imenso concluir esse projeto, a forma como aprendi ainda mais sobre os conceitos de JPA, Security e Arquitetura.
