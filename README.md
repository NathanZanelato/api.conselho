# api.conselho

Requisítos para instalação do ambiente:

    * Maven 3.6:          https://maven.apache.org/download.cgi
    * PostgreSQL 10.8:    https://www.postgresql.org/download/windows/
    * pgAdmin 3           https://www.pgadmin.org/download/pgadmin-3-windows/

Configuração e execução da API:

    1) Acesse o pgAdmin e crie o database 'conselho' com usuário 'postgres' e senha 'postgres'
    2) Através do prompt de comando, na raiz do projeto, execute: 'mvn thorntail:run'
    3) Após subir a API e criar as tabelas no banco, na primeira execução, deverá ser inserido o usuário admin;
       para isso execute o insert no pgAdmin: insert into usuarios (id_usuario, password, username) values (nextval('usuarios_seq'), 'admin', 'admin');

Testes iniciais:

    Através do Postman, Insomnia ou afins.. Execute uma requisição POST para http:localhost:8080/api.conselho/logar
    passando username e senha admin ex.:

    {
        "username" : "admin",
        "password" : "admin"
    }

    Caso tudo ocorra bem retornará o token de acesso para aplicações client, 
    então para fazer outras requisições para recursos da API via Postman deverá ser informado este token
    na aba header adicione a Key 'Authorization' e o Value 'Bearer <token>' sem as aspas.