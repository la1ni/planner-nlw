# ✈️🗺️ Travel Planner

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)[![OpenAPI](https://img.shields.io/badge/OpenAPI-6BA539?style=for-the-badge&logo=openapi-initiative&logoColor=white)](https://www.openapis.org/)[![Flyway](https://img.shields.io/badge/Flyway-C71A36?style=for-the-badge&logo=flyway&logoColor=white)](https://flywaydb.org/)[![JPA](https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=jpa&logoColor=white)](https://spring.io/projects/spring-data-jpa)

## Descrição

Este projeto foi desenvolvido durante a NLW Journey da Rocketseat, trilha Java Backend, e consiste em uma API REST para um planejador de viagens. O objetivo é permitir que os usuários criem viagens, convidem participantes e registrem atividades e links importantes relacionados a essas viagens.

## Funcionalidades

- **Criação, Atualização e exibição de Viagens**: Permite aos usuários manipular suas viagens.
- **Convite de Participantes**: Os usuários podem convidar outros participantes para suas viagens.
- **Registro e exibição de Atividades**: Adiciona atividades a cada viagem.
- **Registro e exibição de Links**: Salva links úteis relacionados à viagem.

## Principais tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada no desenvolvimento.
- **Spring Boot**: Framework principal para construção da API REST.
- **OpenAPI**: Utilizado para documentar a API.
- **Flyway**: Ferramenta de migração de banco de dados.
- **JPA Repository**: Utilizado para a persistência dos dados.

## Como Executar

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/la1ni/planner-nlw.git
    ```

2. **Navegue até o diretório do projeto:**

    ```bash
    cd planner-nlw
    ```

3. **Configure o banco de dados:**

    Certifique-se de que você tem um banco de dados configurado e atualize as informações de conexão no `application.properties`. Sugerido o H2 devido à facilidade de confirguração

4. **Execute as migrações do Flyway:**

    As migrações do Flyway serão executadas automaticamente ao iniciar a aplicação.

5. **Inicie a aplicação:**

    ```bash
    ./mvnw spring-boot:run
    ```

## Documentação da API

A documentação da API está disponível via OpenAPI. Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui.html
```

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.

## Licença

Este projeto está licenciado sob a licença MIT.

