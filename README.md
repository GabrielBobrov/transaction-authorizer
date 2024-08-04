# Transaction Authorizer

## Descrição

O projeto **Transaction Authorizer** é um serviço responsável pela autorização de transações financeiras. Ele realiza a validação dos detalhes da transação, verifica o saldo disponível na conta do usuário e atualiza o status da transação conforme necessário.

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Maven**
- **H2 Database**
- **JUnit 5**
- **Mockito**

## Configuração

### Banco de Dados

O projeto emprega o banco de dados **H2** em memória, facilitando tanto o desenvolvimento quanto a execução de testes. As configurações pertinentes ao banco de dados encontram-se no arquivo `src/main/resources/application.properties`
Após subir a aplicacao, podemos visualizar o banco de dados pela url http://localhost:8080/h2-console com esses dados de acesso:

<img width="536" alt="image" src="https://github.com/user-attachments/assets/7f63fcb2-1365-4118-b09c-82b1a26ffa06">



## Executando o Projeto

Para executar o projeto localmente, siga os passos a seguir:

1. Clone o repositório: `git clone https://github.com/GabrielBobrov/transaction-authorizer.git`


2. Compile o projeto utilizando o Maven. Execute os comandos `mvn clean` seguido por `mvn install` para gerar os artefatos necessários e iniciar a aplicação.

## Documentação da API
A documentação da API é gerada automaticamente pelo Swagger. Após iniciar a aplicação, você pode acessar a documentação interativa através da URL http://localhost:8080/swagger-ui.html. 

## L4
Sincronização: Ao anotar o método processTransaction com synchronized, garantimos que apenas uma thread pode executar este método por vez. Isso impede que duas transações sejam processadas simultaneamente para a mesma conta.
Desempenho: A sincronização pode introduzir um pequeno overhead, mas é essencial para garantir a consistência dos dados. Dado que as transações devem ser processadas rapidamente (menos de 100 ms).
