# InventoryAppApi

API do projeto de inventário Web, que possui: 
 - Cadastro e login de usuário, 
 - Cadastro de clientes
 - Cadastro de fornecedores 
 - Cadastro de produtos
 - Gerenciamento de vendas
 - Transações financeiras.
 - Documentação com Swagger para desenvolvedores, acessada em http://localhost:8080/q/swagger-ui/

## Tecnologias usadas: 
- Java 21 com Quarkus
- Hibernate com Panache
- SQLITE
- Autenticação com token JWT
- Criptografia de senhas com java.security
- Arquitetura: Arquitura limpa, API REST
- Testes: JUnit e Mockito

## Todas bibliotecas:
- quarkus-resteasy-jsonb
- quarkus-smallrye-openapi
- quarkus-smallrye-jwt
- quarkus-smallrye-jwt-build
- quarkus-smallrye-openapi
- quarkus-container-image-jib
- quarkus-jdbc-sqlite
- quarkus-hibernate-orm-panache
- quarkus-junit5
- quarkus-junit5-mockito
