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
  - Incluir creditos para um cliente
  - Consultar saldo de um cliente
  - Alterar limite de um cliente
  - Alterar plano de um cliente
  - Ver dados de um cliente
- Mais telas no frontend.

### Tecnologias Futuras
- Liquibase para controle e gerenciamento do banco.
- Eureka Server para atuar junto com o FeignClient quando houver mais serviços.
- Kubernetes para gerenciar melhor os containers.

### Executando os Projetos Manualmente

Para executar os projetos manualmente, siga estas etapas:

1. **Crie os containers necessários:**

   - Para o RabbitMQ:
     ```sh
     docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management
     ```

   - Para o PostgreSQL:
     ```sh
     docker run --name dbpostgres -e POSTGRES_PASSWORD=123 -d -p 5432:5432 postgres
     ```

2. **Suba os projetos:**

   - Execute as classes principais de cada projeto Java (`smsender`, `smconsumer`, `smbackoffice`).

   - Para iniciar o projeto React, navegue até a raiz do projeto web e execute:
     ```sh
     npm start
     ```
     
### Postman Collection
O projeto também inclui um arquivo de coleção do Postman que pode ser utilizado para testar os endpoints. O arquivo está localizado em `messageproject/SMSender.postman_collection.json`.
