# Projeto de Autenticação e Autorização jwt com Spring Security
Este é um projeto básico de autenticação e autorização usando o Spring Security e o Jwt Token.


### Tecnologias
As seguintes tecnologias foram usados neste projeto:

* [Spring Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
* [Spring Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
* [Spring Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
* [PostgreSQL](https://mvnrepository.com/artifact/org.postgresql/postgresql)
* [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
* [jjwt-api](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api)
* [jjwt-impl](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl)
* [jjwt-jackson](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson)

### Funcionalidades
Este projeto contém as seguintes funcionalidades

- Registrar usuário do tipo ADMIN ou USER
- Fazer Login
- Gerar jwt token
- Acessar rota com permissão para todos os tipos de usuário
- Acessar rota com permissão apenas para usuários do tipo USER

> OBS: Os comentários no código foram colocados para melhorar o meu entendimento sobre o mesmo.