# E-commerce - Microsserviços com Spring Boot

Este projeto é um sistema de **E-commerce**, que consiste na **compra e venda de produtos**.

---

## Arquitetura

O sistema é composto pelos seguintes microsserviços:

- **Service-Authentication**: Gerencia a criação de vendedores e compradores, realiza a autenticação e gera tokens **JWT**.
- **Service-Produto**: Gerencia a criação de produtos e o controle de estoque.
- **Service-Order**: Controla as vendas realizadas.
- **Service-Notification**: Envia notificações aos usuários quando um produto é comprado ou vendido.
- **API-Gateway**: Ponto de entrada único para a API.
- **Service-Discovery**: Permite que os serviços do sistema encontrem uns aos outros dinamicamente.

A comunicação entre os microsserviços é feita via **REST** e mensageria (**RabbitMQ**).

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Cloud**
- **Spring Data JPA / Hibernate**
- **Spring Mail Sender**
- **Spring Security**
- **BCrypt**
- **Token JWT**
- **Maven**
- **PostgreSQL**
- **Lombok**
- **OpenFeign**
- **RabbitMQ**
- **Docker / Docker Compose**

---

## Principais Funcionalidades

1. Criação de usuários dos tipos **VENDEDOR** e **COMPRADOR**.  
2. Criptografia de senhas no banco de dados.  
3. Autenticação de e-mail e senha dos usuários, retornando um token **JWT**.  
4. Usuários do tipo **VENDEDOR** podem criar produtos e adicionar unidades ao estoque.  
5. Usuários do tipo **COMPRADOR** podem buscar todos os produtos ou um produto específico.  
6. Quando uma compra é realizada, é enviado um e-mail tanto para o **COMPRADOR** quanto para o **VENDEDOR**, contendo os detalhes da transação.

---

## O que aprendi desenvolvendo este projeto

1. Utilizei pela primeira vez serviços de mensageria e filas (**RabbitMQ**).  
   Foi um aprendizado muito importante, levando em conta que a maioria dos microsserviços atuais utiliza algum tipo de serviço de mensageria.

2. Desenvolvi um projeto completo em arquitetura de **microsserviços** de forma independente.  
   Foi muito enriquecedor ser **autodidata** nesse processo.

3. Percebi o quanto é vantajoso começar um projeto como **monólito** e, posteriormente, migrar para **microsserviços**.  
   Essa abordagem facilita o entendimento da aplicação e a divisão dos serviços de forma mais eficiente.
