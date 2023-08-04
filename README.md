# :dart: <center> CRUD API Completo </center>

Criei esse projeto com a intenção de aprender a utilizar as tecnologias do jQuery + Boostrap, além de colocar em prática Java e Spring boot.

# 🛠 Ferramentas e Tecnologias utilizadas

 - JDK 11
 - Spring boot
 - Maven
 - H2 Database / PostgreSQL (default)
 - Visual Studio Code
 - Docker
 - Nginx
 - Swagger
 
# :exclamation: Pré-requisitos

Para poder iniciar o projeto verifique se sua máquina tem os seguintes programas instalados:

- [X] JDK 11
- [X] Git
- [X] Editor de Texto VSCode ou Eclipse IDE
- [X] PostgreSQL (pode ser utilizado o H2)
- [ ] Docker (Opcional)

##  :zap: Executando o Projeto Com Docker

```bash
# 1º Clone o repositório
$ git clone https://github.com/AlissonFerreiraEvangelista/api_rest_sample.git

# 2º Acesse a pasta do projeto
$ cd \apringboot-rest-api
$ docker compose build
$ docker compose up

Aplicação esta rodando na porta 80, acesse localhost/springboot-bootstrap
```
##  :zap: Executando o Projeto Sem Docker

```bash
# 1º Clone o repositório
$ git clone https://github.com/AlissonFerreiraEvangelista/api_rest_sample.git

# 2º Acesse a pasta do projeto
$ cd \apringboot-rest-api
# Acesse o package default e inicie o arquivo Application.java

Aplicação esta rodando na porta padrão 8080, acesse localhost:8080/springboot-bootstrap
```
## :no_entry_sign: Lembrete
Por padrão está configurado o PostgreSQL no application.properties, caso não tenha o mesmo instalado remover as linhas 23 a 26.
Depois de remover as linhas, remover os comentários das linhas 15 a 20

# Endpoints
### Swagger
http://localhost:8080/springboot-bootstrap/swagger-ui.html




# Imagens

![API](https://user-images.githubusercontent.com/82222646/207737422-6dc2f429-9305-4116-bb71-7bd78ca993c6.PNG)

![open](https://user-images.githubusercontent.com/82222646/208738589-94d90231-ef7a-4a7c-bf13-16e157845364.PNG)
