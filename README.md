# Tech Challenger FIAP F5

Bem-vindo ao repositório do projeto "atendimento", desenvolvido como parte do desafio tecnológico da FIAP. Este projeto visa implementar uma API para triagem online para o SUS.

## 🎓 Projeto Academico

Projeto de pós-graduação em **arquitetura e desenvolvimento JAVA** pela FIAP ALUNOS 5ADJT

## 👨‍💻 Desenvolvedores

- Edson Antonio da Silva Junior
- Gabriel Ricardo dos Santos
- Luiz Henrique Romão de Carvalho
- Marcelo de Souza

## 💡 Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-3.9.9-C71A36?style=for-the-badge&logo=apachemaven)
![MongoDB](https://img.shields.io/badge/MongoDB-8.0-336791?style=for-the-badge&logo=mongodb)
![Docker](https://img.shields.io/badge/Docker-27.5.1-2496ED?style=for-the-badge&logo=docker)
![Swagger](https://img.shields.io/badge/Swagger-3.0-85EA2D?style=for-the-badge&logo=swagger)

## Estrutura do projeto

```markdown
└── src
├── main
├── java
│   └── br
│   │   └── com
│   │       └── powerprogramers
│   │           └── atendimento
│   │               ├── Application.java
│   │               ├── config                  // Configurações da aplicação
│   │               ├── controller              // Rest Controllers
│   │               ├── domain                  // Modelos de dominio
│   │                   ├── enums               // Enums
│   │                   └── paginacao           // Modelo de paginação
│   │               ├── entity                  // Modelos de banco de dados
│   │               ├── exception               // Exceptions
│   │               ├── gateway                 // Interfaces gateway
│   │                   └── impl                // Implementação gateway
│   │               ├── mapper                  // Mapeamento de objetos
│   │               ├── repository              // Repositorios
│   │               ├── service                 // Interfaces service
│   │                   └── impl                // Implementação service
│   │               └── usecase                 // Interfaces Caso de uso
│   │                   └── impl                // Implementação Caso de uso
```

## ▶️ Como Executar o Projeto

Para executar o projeto localmente, siga as etapas abaixo:

1. **Clone o repositorio:**

    ```bash
    git clone <https://github.com/Power-Rangers-FIAP/tc-f5-ms-atendimento.git>
    
    ```

2. **Navegue ate o diretorio do projeto:**

    ```bash
    cd tc-f5-ms-atendimento
    
    ```

3. **Compile o projeto com Maven:**

    ```bash
    mvn clean install -U
    
    ``` 

4. **Inicie a aplicação localmente:**

    ```bash
    mvn spring-boot:run
    
    ```

## 🧪 Como executar testes

- **Para testes de unidade:**

    ```bash
    mvn test
    
    ```

- **Para testes de integração:**

    ```bash
    mvn test -P integration-text
    
    ``` 

- **Para testes de performance:**

  > Com o Docker rodando, execute o comando:

    ```bash
    mvn gatling:test -P performance-test
    
    ``` 

- **Para testes de sistema:**

    ```bash
    mvn test -P system-text
    
    ```
  
## 📄 Relatório de Performance

Apos execução do teste de performance, você pode relatorio da execução abrindo o arquivo index.html como `target/gatling/performancesimulation-\<dataexecução>/index.html`
Exemplo do caminho: `target/gatling/performancesimulation-20241209162646899/index.html`

## 🧪 API Endpoint

A API pode ser executada e testada usando o Swagger. A documentação esta disponivel na URL:
[`Swagger`](http://localhost:8081/swagger-ui/index.html)

## Contribuindo

Contribuições são bem-vindas! Para contribuir com o projeto, por favor siga estes passos:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature ou correção (`git checkout -b feature/nova-feature`).
3. Faça commit das suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Envie suas alterações para o repositório (`git push origin feature/nova-feature`).
5. Abra um pull request.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).