# user-service

Um serviço RESTful de gerenciamento de usuários, construído com Java 21 e Spring Boot, seguindo os princípios da Arquitetura Limpa e utilizando o Keycloak para autenticação e autorização.

---

## 🏗️ Arquitetura do Projeto

Este projeto foi desenvolvido com base na **Arquitetura Limpa (Clean Architecture)**, que visa separar as preocupações do código em camadas independentes. A estrutura de pastas reflete essa abordagem:

* **`domain`**: Contém as entidades de negócio puras (POJOs), como `User`, que são o coração da aplicação. Esta camada não tem dependência de frameworks externos.
* **`application`**: Define a lógica de negócio específica da aplicação, com a implementação dos "casos de uso" (`usecase`). Ela se comunica com as outras camadas através de interfaces de "portas" (`port`).
* **`infrastructure`**: Lida com os detalhes de implementação. É aqui que os adaptadores de persistência (repositórios JPA), os controladores REST e as configurações de segurança (Spring Security) são definidos.

Esta separação garante um código mais testável, manutenível e desacoplado de frameworks.

---

## 🚀 Tecnologias Utilizadas

* **Java 21**: A linguagem de programação do projeto.
* **Spring Boot 3.3.x**: Framework para construção da aplicação.
* **Spring Data JPA**: Para persistência de dados.
* **H2 Database**: Banco de dados em memória para desenvolvimento.
* **Spring Security**: Para segurança da aplicação.
* **Spring Security OAuth2 Resource Server**: Módulo para integração com o Keycloak.
* **Lombok**: Para reduzir o código repetitivo (getters, setters, construtores).
* **Docker e Docker Compose**: Utilizados para rodar o servidor Keycloak.

---

## 🛠️ Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

* **Java 21** ou superior
* **Maven** ou **Gradle** (para gerenciar as dependências)
* **Docker** e **Docker Compose**

---

## ⚙️ Configuração e Execução

### 1. Iniciar o Keycloak

O servidor Keycloak é essencial para a autenticação. Use o Docker Compose para iniciá-lo de forma fácil:

Navegue até a pasta `keycloak-setup` (ou onde você salvou seu arquivo `docker-compose.yml`) e execute:

```bash
docker compose up -d
```
## 3. Executar o Projeto
Para rodar a aplicação, use o Maven:
mvn spring-boot:run

🚀 Endpoints da API
Todos os endpoints requerem um token de acesso válido no cabeçalho Authorization: Bearer <token>.

Método	Endpoint	Descrição
```POST	/users	Cria um novo usuário.```
```GET	/users/{id}	Busca um usuário pelo ID.```
```GET	/users	Lista todos os usuários.```
```PUT	/users/{id}	Atualiza um usuário existente.```
```DELETE	/users/{id}	Exclui um usuário pelo ID.```
