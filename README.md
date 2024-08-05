# messageproject

Este projeto foi projetado em arquitetura de microserviço e está dividido em três projetos principais feitos em Java.

## Serviços

### smsender
- Responsável pela criação de cliente e envio de mensagem assíncrona.

### smconsumer
- Responsável por ler a mensagem e enviar a requisição para o terceiro serviço.

### smbackoffice
- Responsável pela parte contábil, removendo créditos ou criando transações entre outras ações.

## Frontend

### app_messageproject
- Desenvolvido em React com TypeScript.

## Banco de Dados e Mensageria

- PostgreSQL
- RabbitMQ

## Tecnologias Utilizadas

### Backend
- Java 21
- Spring Boot 3.3
- Spring JPA
- Spring Cloud

### Frontend
- ReactJS
- TypeScript

### Outros
- PostgreSQL
- RabbitMQ

## Instruções para Execução

O projeto possui arquivos Dockerfile e docker-compose.yml. Dentro do projeto raiz, execute o comando:

```sh
docker-compose up --build
