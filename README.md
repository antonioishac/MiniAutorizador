# Mini autorizador

#  Arquitetura do projeto
	- Projeto desenvolvido em Java com Spring Boot e os seus componentes.
    - Projeto baseado em uma aplicação Maven para gerenciamento das bibliotecas utilizadas no projeto.
    - Autenticação desenvolvida com Spring Security utilizando o conceito BasicAuth.
    - Utilização do design patterns Strategy com polimorfismo para utilização da regra de negócio na transaçãp do cartão.
    - Utilização dos conceitos do SOLID, principalmente, 'Single Responsibility Principle', deixando as classes com uma única responsabilidade. 
    - Utilização do framework Lombok para diminuir e abstrair a quantidade de linhas de código.
    - Utilização do framework MapStruct para abstrair a camada DTO e Entity, facilitando a transferência dos atributos entre os objetos.
    - Flaway para versionamento dos scripts do banco de dados MySql.
    - Camada de persistência de dados com JPA e Hibernate utilizando o framework SpringDataJpa
    - Foi desenvolvido os testes unitários utilizando JUnit, Mockito, MockMvc, passando pela camada de controller, service e repository.
    - Na parte de Devops, foi utilizado o Docker e o projeto contem um arquivo docker-compose.yml para execução do própio.

## Sugestão para execução do projeto;
    Baixar o projeto, e executar o seguinte comando para baixar as dependências:
    mvn clean install

    Depois basta executar o comando docker compose para rodar o projeto localmente:
    docker-compose up --build

## Observação:
    As respostas da API, foi desenvolvida conforme a solicitação.

##  Mini autorizador - Serviços da API
##	cadastrar um novo cartão;
	POST - http://localhost:8080/cartoes

	{
        "numeroCartao": "6549873025634508",
        "senha": "1234"
    }
##  curl para criação de um novo cartão;
    curl --location 'http://localhost:8080/cartoes' \
    --header 'accept: */*' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=' \
    --data '{
    "numeroCartao": "6549873025634508",
    "senha": "1234"

##  Obter o saldo do cartão criado;
    GET - http://localhost:8080/cartoes/6549873025634508
##  curl para obter o saldo do cartão criado;
    curl --location 'http://localhost:8080/cartoes/6549873025634508' \
    --header 'accept: */*' \
    --header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ='

##  Realizar uma transação;
	POST - http://localhost:8080/transacoes

	{
        "numeroCartao": "6549873025634508",
        "senhaCartao": "1234",
        "valor": 100.0
    }
##  curl para realizar uma transação
    curl --location 'http://localhost:8080/transacoes' \
    --header 'accept: */*' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=' \
    --data '{
    "numeroCartao": "6549873025634508",
    "senhaCartao": "1234",
    "valor": 100.0
    }'
