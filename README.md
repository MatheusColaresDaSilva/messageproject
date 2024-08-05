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
```

Esse comando irá construir as imagens dos projetos e levantar os containers de todos os serviços necessários.

### URLs dos Serviços
- Frontend: [http://localhost:3000/](http://localhost:3000/)
- smsender: [http://localhost:8080/](http://localhost:8080/)
- smconsumer: [http://localhost:8081/](http://localhost:8081/)
- smbackoffice: [http://localhost:8082/](http://localhost:8082/)
- PostgreSQL: [http://localhost:5432/](http://localhost:5432/)
- RabbitMQ: [http://localhost:5672/](http://localhost:5672/)

### Fluxo
1. Existirão no banco dois planos já previamente cadastrados: `POS` e `PRE`.
2. Crie um cliente escolhendo um dos dois planos.
3. Envie uma mensagem. Essa mensagem será enviada para o RabbitMQ e será consumida e processada pelo `smbackoffice`.
4. Se tudo estiver certo, após alguns segundos, essa mensagem que estava com status pendente estará com status `Success`.

### O que falta
- Ações do backoffice.
- Mais telas no frontend.

### Tecnologias Futuras
- Liquibase para controle e gerenciamento do banco.
- Eureka Server para atuar junto com o FeignClient quando houver mais serviços.
- Kubernetes para gerenciar melhor os containers.
