# case_tecnico-meetime

## Dependencias
- Spring Boot DevTools
-- Está dependencia foi adicionada com o fim de facilitar o processo de desenvolvimento.

- Spring Web
-- Serve como ponto de partida na criação de projetos facilita a criação de APIs REST e é flexível no caso de necessidade de integrações posteriores com outras tecnologias do ecossistema Spring.

## Estrutura

case-tecnico/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── meetime/
│   │   │           └── case_tecnico/
│   │   │               ├── config/               <-- Configurações gerais, OAuth2, beans, etc
│   │   │               ├── controller/           <-- Endpoints REST (OAuth, integração, webhook)
│   │   │               ├── dto/                  <-- Objetos de transferência de dados (payloads)
│   │   │               ├── model/                <-- Modelos (se tiver persistência local, ex: tokens)
│   │   │               ├── repository/           <-- Acesso a dados (se necessário)
│   │   │               ├── service/              <-- Lógica de negócio (integrações, OAuth, etc)
│   │   │               └── CaseTecnicoApplication.java
│   │   └── resources/
│   │       ├── application.yml or application.properties
│   │       └── static/ or templates/ (se necessário)
│   └── test/
│       └── java/
│           └── com/
│               └── meetime/
│                   └── case_tecnico/
│                       └── (testes unitários e de integração)
├── pom.xml

