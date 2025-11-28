# API de Gerenciamento de Produtos (CRUD)

![Badge de Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Badge de Licença](https://img.shields.io/badge/license-MIT-blue)


Uma API RESTful desenvolvida em Java e Spring Boot para realizar as quatro operações básicas (CRUD - Create, Read, Update, Delete) em uma entidade `Produto`.

Este projeto demonstra o uso de boas práticas de desenvolvimento de APIs, incluindo:
* **DTOs (Data Transfer Objects)** com `records` do Java.
* Separação clara de responsabilidades (Camadas de Controller, Service e Repository).
* **Validações** de entrada de dados com `Bean Validation`.
* Tratamento de exceções e retornos HTTP semânticos.
* Atualizações parciais seguras com o padrão `PATCH` (implementado com `@PatchMapping`).

## Status do Projeto
🚧 Em Desenvolvimento 🚧

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3** (ou superior)
* **Spring Data JPA**: Para persistência de dados.
* **H2 Database**: Banco de dados em memória para desenvolvimento/testes.
* **Maven**: Gerenciador de dependências.
* **Lombok**: Para reduzir boilerplate (ex: `@RequiredArgsConstructor`).
* **Bean Validation**: Para validações dos DTOs.

## 🚀 Como Rodar o Projeto

É necessário ter o Java 17 (ou superior) e o Maven instalados.

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/Joaorooliveira/crud-produtos-spring.git
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd crud-produtos-spring
    ```

3.  **Execute o projeto:**
    * A forma mais simples é abrir o projeto em sua IDE (IntelliJ, Eclipse, VSCode) e executar a classe principal `CrudProdutosApplication.java`.
    * Ou, você pode rodar via Maven:
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Acesse a API:**
    A aplicação estará rodando em `http://localhost:8080`.

## 📖 Endpoints da API

A rota base para todos os endpoints é `/api/produtos`.

| Método | Rota | Descrição | Corpo (Body) | Retorno de Sucesso |
| :--- | :--- | :--- | :--- | :--- |
| `POST` | `/create` | Cria um novo produto. | `ProdutoRequestDTO` | `200 OK` (com `Produto` entity) |
| `GET` | `/list` | Lista todos os produtos cadastrados. | N/A | `200 OK` (com `List<ProdutoResponseDTO>`) |
| `GET` | `/list/{id}` | Busca um produto específico pelo seu ID. | N/A | `200 OK` (com `ProdutoResponseDTO`) |
| `PATCH` | `/edit/{id}` | Atualiza um produto (parcial ou total). | `ProdutoAtualizarRequestDto` | `200 OK` (com `ProdutoResponseDTO`) |
| `DELETE` | `/delete/{id}` | Deleta um produto pelo seu ID. | N/A | `204 No Content` |

---

### DTOs (Corpos das Requisições)

**`ProdutoRequestDTO` (para Criar)**
```json
{
  "nome": "Nome do Produto",
  "preco": 199.99,
  "quantidade": 10
}
```

**`ProdutoAtualizarRequestDto` (para Atualizar) Envie apenas os campos que deseja alterar.**
```json

{
  "preco": 249.50,
  "quantidade": 15

}
```

📁 Estrutura do Projeto (Simplificada)
```
.
└── src
    └── main
        └── java
            └── com/product/api/crud_produtos
                ├── controller  # Camada de API (Endpoints)
                ├── dto         # Data Transfer Objects (Records)
                ├── entities    # Entidades do Banco (Produto)
                ├── repository  # Interface com o Banco (JPA)
                └── service     # Camada de Regras de Negócio
```
