# Sistema Faculdade

Sistema de gerenciamento acadêmico desenvolvido em Java com integração ao MySQL. O projeto permite realizar operações de cadastro, consulta, atualização e exclusão de alunos, cursos e disciplinas.

## Tecnologias utilizadas

- **Java 21** — linguagem de programação.
- **MySQL** — sistema de gerenciamento de banco de dados.
- **JDBC** — conexão entre Java e MySQL.
- **IntelliJ IDEA** — ambiente de desenvolvimento.
- **Programação Orientada a Objetos (POO)** — organização e estruturação do sistema.

## Funcionalidades

O sistema possui um menu principal com três módulos:

### Alunos
- Cadastrar alunos, informando nome, idade e curso.
- Consultar alunos e visualizar seus dados, curso e disciplinas vinculadas.
- Atualizar informações dos alunos.
- Excluir alunos.

### Cursos
- Cadastrar cursos, informando nome e turno.
- Consultar cursos cadastrados.
- Atualizar informações dos cursos.
- Excluir cursos, respeitando os vínculos existentes no banco de dados.

### Disciplinas
- Cadastrar disciplinas, informando nome, carga horária e curso relacionado.
- Consultar disciplinas cadastradas.
- Atualizar informações das disciplinas.
- Excluir disciplinas.

## Estrutura do projeto

O projeto foi organizado em pacotes para separar as responsabilidades de cada parte do sistema:

```text
SistemaFaculdade/
│
├── src/
│   ├── controller/
│   │   ├── AlunoController.java
│   │   ├── CursoController.java
│   │   └── DisciplinaController.java
│   │
│   ├── dao/
│   │   ├── AlunoDao.java
│   │   ├── CursoDao.java
│   │   └── DisciplinaDao.java
│   │
│   ├── database/
│   │   └── DatabaseConnection.java
│   │
│   ├── model/
│   │   ├── Aluno.java
│   │   ├── Curso.java
│   │   └── Disciplina.java
│   │
│   ├── view/
│   │   ├── Menu.java
│   │   ├── SubmenuAluno.java
│   │   ├── SubmenuCurso.java
│   │   └── SubmenuDisciplina.java
│   │
│   └── Main.java
│
├── libs/
│   └── mysql-connector-j-9.1.0.jar
│
├── .gitignore
└── README.md
```

## Banco de dados

O sistema utiliza o banco de dados MySQL, com o nome `faculdade`.

São utilizadas três tabelas:

- **curso:** armazena o código, nome e turno dos cursos.
- **disciplinas:** armazena o código, nome, carga horária e o curso ao qual cada disciplina pertence.
- **alunos:** armazena a matrícula, nome, idade e o curso de cada aluno.

As tabelas possuem relacionamentos por meio de chaves estrangeiras, garantindo a integridade dos dados.

## Como executar o projeto

### 1. Pré-requisitos

Antes de executar, é necessário ter instalado:

- JDK 21 ou superior.
- MySQL Server.
- IntelliJ IDEA ou outra IDE compatível com Java.
- MySQL Connector/J.

### 2. Configuração do banco de dados

No MySQL, crie o banco de dados:

```sql
CREATE DATABASE faculdade;
```

As tabelas são criadas automaticamente pelo sistema durante a inicialização.

### 3. Configuração da conexão

Na raiz do projeto, crie um arquivo chamado `database.properties` com as seguintes informações:

```properties
db.user=SEU_USUARIO
db.password=SUA_SENHA
```

Substitua os valores pelos dados de acesso do seu MySQL.

**Importante:** esse arquivo contém credenciais de acesso e não deve ser enviado ao GitHub.

### 4. Configuração do JDBC

Adicione o arquivo `mysql-connector-j-9.1.0.jar`, localizado na pasta `libs`, às dependências do projeto.

No IntelliJ IDEA, verifique se o arquivo está configurado como biblioteca do projeto.

### 5. Executar o sistema

Abra o projeto na IDE e execute a classe `Main.java`.

O menu principal será exibido no console, permitindo acessar os módulos de alunos, disciplinas e cursos.

## Organização e conceitos de POO

Durante o desenvolvimento, foram aplicados conceitos de Programação Orientada a Objetos:

- **Encapsulamento:** atributos privados e utilização de métodos getters e setters.
- **Construtores:** inicialização dos objetos.
- **Associação entre classes:** alunos e disciplinas relacionados aos cursos.
- **Separação de responsabilidades:** divisão do sistema em Model, View, Controller e DAO.

A organização facilita a manutenção, compreensão e evolução do código.

## Autor

Samuel Chaves. Projeto acadêmico desenvolvido como atividade prática de Programação Orientada a Objetos, com integração a banco de dados MySQL.
