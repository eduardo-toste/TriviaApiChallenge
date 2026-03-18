# Trivia API

API REST para jogar trivia consumindo dados da [Open Trivia Database](https://opentdb.com/).

## Instalação e Execução

```bash
# Compilar
./mvnw clean compile

# Executar
./mvnw spring-boot:run
```

A API estará em `http://localhost:8080`

## Endpoints

### Obter pergunta
```
GET /trivia/question
```

### Responder pergunta
```
POST /trivia/answer/{questionId}
Content-Type: application/json

{
  "answer": true
}
```

## Tecnologias

- Java 21
- Spring Boot 3.5.11
- Spring Cloud OpenFeign
- Maven
