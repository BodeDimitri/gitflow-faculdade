## 🚀 Tecnologias Utilizadas
- Java 21 (Temurin)

- Spring Boot

- Maven

- Docker

- GitHub Actions (CI/CD)

- H2 Database (ambiente de desenvolvimento)

- JPA / Hibernate Validator

---

## 🔧 Pré-requisitos

- Java 21 (Temurin)

- Maven 3.8+

- Docker

- GitHub Account com repositório configurado para Actions

- Acesso SSH configurado para deploy remoto (Staging / Produção)

---
## ⚙️ Como executar o projeto localmente

Clone o repositório:
```bash
git clone https://github.com/seu-usuario/meu-app.git
cd meu-app
cd Faculdade
```

Build do projeto com Maven:
```bash
mvn clean package
```

Executar o JAR gerado:
```bash
java -jar target/meu-app-0.0.1-SNAPSHOT.jar
```

Acesse a aplicação:
```bash
http://localhost:8080
```
---
## 🐳 Executando com Docker

Build da imagem:
```bash
docker build -t meu-java-app .
```

Run da aplicação:
```bash
docker run -d --name meu-java-app -p 8080:8080 meu-java-app
```
  
 
