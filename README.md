## 🚀 Tecnologias Utilizadas

* Java 21 (Temurin)

* Spring Boot

* Maven

* Docker

* GitHub Actions (CI/CD)

* H2 Database (ambiente de desenvolvimento)

* JPA / Hibernate Validator

---

## 🔧 Pré-requisitos

* Java 21 (Temurin)

* Maven 3.8+

* Docker

* GitHub Account com repositório configurado para Actions

* Acesso SSH configurado para deploy remoto (Staging / Produção)

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

---

## ✅ Executando os Testes

O projeto utiliza **JUnit 5**, **Cucumber** e **RestAssured** para validações e testes de contrato.

### 🧪 Executar todos os testes

```bash
mvn test
```

### 📜 Estrutura dos testes

* Testes de unidade e integração ficam em:

  ```
  src/test/java/com/example/faculdade/
  ```

* Os arquivos `.feature` do Cucumber ficam em:

  ```
  src/test/resources/features/
  ```

* Os schemas JSON utilizados para validação ficam em:

  ```
  src/test/resources/schemas/
  ```

### ⚠️ Possíveis erros

Se o teste de contrato falhar com a mensagem `Schema to use cannot be null`, verifique:

* Se o arquivo `incident-schema.json` existe em `src/test/resources/schemas/`
* Se ele está corretamente referenciado no teste com:

  ```java
  matchesJsonSchemaInClasspath("schemas/incident-schema.json")
  ```

(Eu cometi esse erro varias vezes).
