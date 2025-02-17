# Transaction API

![Java](https://img.shields.io/badge/Java-23-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-green)
![Swagger](https://img.shields.io/badge/Swagger-2.8.4-yellow)
![Docker](https://img.shields.io/badge/Docker-✓-blue)

A **Transaction API** é uma aplicação Spring Boot e foi feita como resolução do desafio para vaga de Desenvolvedor Júnior do Itaú.
É uma API RESTful que permite enviar e salvar na memória uma transação baseada em um valor e uma data/hora, deletar as transações armazenadas e visualizar estatísticas a partir delas.

---

## Funcionalidades

- **Criação de transações**: Registre novas transações com valor, descrição e data.
- **Validação de dados**: Garante que os campos obrigatórios estejam preenchidos e que os valores sejam válidos.
- **Consulta de estatísticas de transações**: Veja informações como contagem de transações, soma, média, valor mínimo e máximo.
- **Documentação com Swagger**: A API é documentada automaticamente com Swagger UI para facilitar o uso e testes.
- **Respostas HTTP padronizadas**: Retorna códigos de status HTTP adequados para cada operação.
- **Docker**: Suporte para containerização da aplicação.

---

## Ferramentas e Tecnologias Utilizadas

- **Java 23**
- **Spring Boot**
- **Lombok**
- **Maven**
- **Swagger**
- **Docker**

---

## Como Executar a Aplicação

Você pode executar a aplicação localmente ou a partir do Docker.

### 1. **Executando Localmente**

#### Clone o repositório

```
git clone https://github.com/marcelocb200/transaction-api.git
cd transaction-api
```

#### Compile o projeto

Use o Maven para compilar o projeto:

```
mvn clean install
```

#### Execute a aplicação

Após a compilação, execute a aplicação com o seguinte comando:

```
mvn spring-boot:run
```

A aplicação estará disponível em:  
[http://localhost:8080](http://localhost:8080)

---

### 2. **Executando com Docker**

Para executar a aplicação em um contêiner Docker, siga os passos abaixo.

#### Gere o arquivo .jar da aplicação

```
mvn clean package 
```

#### Construa a imagem Docker

```
docker build -t transaction-api .
```

#### Execute o contêiner

```
docker run -p 8080:8080 transaction-api
```

A aplicação estará disponível em:  
[http://localhost:8080](http://localhost:8080)

---

## Endpoints da API

A API possui os seguintes endpoints:

### 1. **Adicionar transação**
- `POST /transacao`

### 2. **Limpa as transações armazenadas**
- `DELETE /transacao`

### 3. **Retorna as estatísticas de transação**
- `GET /estatistica`
  
## Documentação da API

A API está documentada usando **Swagger UI**, onde você pode acessar mais informações sobre RequestBody ou RequestParams das endpoints. Após iniciar a aplicação, acesse a documentação em:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

A documentação inclui:
- Descrição de todos os endpoints.
- Exemplos de requisições e respostas.
- Possibilidade de testar os endpoints diretamente na interface.
