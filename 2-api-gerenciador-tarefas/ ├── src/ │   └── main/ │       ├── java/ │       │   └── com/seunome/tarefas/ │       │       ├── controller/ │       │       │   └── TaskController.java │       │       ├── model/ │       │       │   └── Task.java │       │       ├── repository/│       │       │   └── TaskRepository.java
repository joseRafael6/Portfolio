# API de Gerenciamento de Tarefas

API RESTful desenvolvida com **Java** e **Spring Boot** para gerenciar uma lista de tarefas (To-Do List).

### Funcionalidades
- **Criar** uma nova tarefa.
- **Listar** todas as tarefas.
- **Buscar** uma tarefa por ID.
- **Atualizar** uma tarefa existente.
- **Deletar** uma tarefa.

### Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Web:** Para criação dos endpoints REST.
- **Spring Data JPA:** Para persistência de dados.
- **H2 Database:** Banco de dados em memória para ambiente de desenvolvimento.
- **Maven:** Gerenciador de dependências.

### Endpoints da API

| Método HTTP | Endpoint        | Descrição                  | Corpo da Requisição (Exemplo)                |
|-------------|-----------------|----------------------------|----------------------------------------------|
| `GET`       | `/tasks`        | Lista todas as tarefas     | N/A                                          |
| `POST`      | `/tasks`        | Cria uma nova tarefa       | `{"title": "Nova Tarefa", "description": "Descrição"}` |
| `GET`       | `/tasks/{id}`   | Busca uma tarefa por ID    | N/A                                          |
| `PUT`       | `/tasks/{id}`   | Atualiza uma tarefa        | `{"title": "Tarefa Atualizada", "completed": true}` |
| `DELETE`    | `/tasks/{id}`   | Deleta uma tarefa          | N/A                                          |

### Como Executar
1. Clone o repositório.
2. Certifique-se de ter o Java 17+ e o Maven instalados.
3. Execute o comando na raiz do projeto:
   ```bash
   mvn spring-boot:run
