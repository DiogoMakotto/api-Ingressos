# api-Ingressos

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13.4-blue)](https://www.postgresql.org/)
[![AWS](https://img.shields.io/badge/AWS-RDS-orange)](https://aws.amazon.com/rds/)

## Descrição

A API para gerenciamento de ingressos de eventos, desenvolvida em Java com Spring Boot, utiliza banco de dados PostgreSQL hospedado na AWS RDS. Este projeto visa demonstrar habilidades em desenvolvimento backend, integração com serviços da AWS e manipulação de dados em PostgreSQL.

## Demonstração

> **Nota:** Insira aqui capturas de tela, gifs ou links para vídeos que mostram a aplicação em funcionamento.

## Funcionalidades

- Cadastro de eventos
- Gerenciamento de ingressos (compra, venda, cancelamento)
- Autenticação e autorização de usuários
- Relatórios de vendas e estatísticas

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação
- **Spring Boot 2.7.0**: Framework para criação de aplicações Java
- **PostgreSQL 13.4**: Banco de dados relacional
- **AWS RDS**: Serviço de banco de dados relacional na nuvem
- **Docker**: Containerização da aplicação
- **Maven**: Gerenciamento de dependências e build

## Instalação e Configuração

1. **Clone o repositório**:
    ```bash
    git clone https://github.com/DiogoMakotto/api-Ingressos.git
    cd api-Ingressos
    ```

2. **Configure o banco de dados**:
    - Crie uma instância PostgreSQL no AWS RDS.
    - Configure as variáveis de ambiente no `application.properties` com as credenciais do banco de dados.

3. **Build e Execute a aplicação**:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Uso

Acesse a documentação da API (Swagger) em: `http://localhost:8080/swagger-ui.html` para explorar os endpoints disponíveis.

### Exemplos de Endpoints

- **Listar Eventos**: `GET /api/v1/eventos`
- **Criar Ingresso**: `POST /api/v1/ingressos`
- **Autenticação**: `POST /api/v1/auth/login`

## Contribuição

Contribuições são bem-vindas! Siga os passos abaixo para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/sua-feature`)
3. Commit suas alterações (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/sua-feature`)
5. Abra um Pull Request

## Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Contato

Diogo Makotto  
[GitHub](https://github.com/DiogoMakotto)  
[LinkedIn](https://www.linkedin.com/in/diogomakotto/)  
[Email](mailto:diogo.makotto@example.com)
