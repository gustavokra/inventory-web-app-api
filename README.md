# inventory-web-app-api

API do projeto de inventário Web, que possui: 
 - Cadastro e login de usuário
 - Cadastro de clientes
 - Cadastro de fornecedores 
 - Cadastro de produtos
 - Gerenciamento de vendas
 - Transações financeiras
 - Documentação com Swagger para desenvolvedores, acessada em http://localhost:8080/q/swagger-ui/

## Tecnologias usadas: 
- Java 21 com Quarkus
- Hibernate com Panache
- SQLITE
- Autenticação com token JWT
- Criptografia de senhas com java.security
- Arquitetura: Arquitura limpa, API REST
- Testes: JUnit e Mockito

## Subir o projeto:
Caso queira subir em modo desenvolvimento, suba a api com o comando 
```
./mvnw quarkus:dev
```

Caso queira subir com o Docker:

Pré requisitos: 
- openssl instalado em seu computador.

Rode o script que cria as suas keys via openssl (que serão usadas para gerar o token JWT) e que sobe o container docker:
```
./start.sh
```

Após isso, acesse http://localhost:8080/ para confirmar se a API subiu localmente.

Caso queria visualizar os enpoints com o documentação Swagger, acesse:
http://localhost:8080/q/swagger-ui/


## Bibliotecas utilizadas:
- quarkus-resteasy-jsonb
- quarkus-jdbc-sqlite
- quarkus-hibernate-orm-panache
- quarkus-smallrye-openapi
- quarkus-smallrye-jwt
- quarkus-smallrye-jwt-build
- quarkus-container-image-jib
- quarkus-junit5
- quarkus-junit5-mockitos

front end: 
[Repositório React Js](https://github.com/gustavokra/inventarioweb)