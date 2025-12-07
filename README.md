# Sistema de Cadastro - Java Swing + MySQL

Sistema de cadastro de clientes e produtos com interface gráfica Java Swing e banco de dados MySQL.

## 🚀 Como Executar

1. **Criar o banco de dados:**
   - Acesse phpMyAdmin: `http://localhost/phpmyadmin`
   - Execute o arquivo `criar_banco.sql`

2. **Executar o projeto:**
   - Abra `src/br/com/pi/Main.java` no Cursor
   - Clique no botão "Run" (▶) ou pressione F5

## 🔐 Login Padrão

- **ID:** `1`
- **Senha:** `1234`

## ⚙️ Configurações

Se seu MySQL tiver senha, edite `src/br/com/pi/db/DatabaseConnection.java`:
```java
private static final String PASSWORD = "sua_senha";
```

## 📁 Estrutura do Projeto

```
src/br/com/pi/
├── dao/          # Data Access Objects
├── db/           # Conexão com banco
├── ui/           # Interface gráfica
└── Main.java     # Classe principal
```

## 📦 Dependências

- Driver MySQL: `lib/mysql-connector-j-9.5.0.jar`
- Java Swing (incluído no JDK)
