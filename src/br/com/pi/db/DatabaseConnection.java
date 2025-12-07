package br.com.pi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    // Configurações do MySQL - AJUSTE AQUI SE NECESSÁRIO
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "sistema_cadastro";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false&serverTimezone=UTC";
    private static Connection connection;
    private static boolean tabelasCriadas = false;
    
    static {
        // Carrega o driver MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERRO: Driver MySQL não encontrado!");
            System.err.println("Por favor, adicione o arquivo mysql-connector-java-X.X.X.jar ao Build Path do projeto.");
            System.err.println("Download: https://dev.mysql.com/downloads/connector/j/");
        }
    }
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!tabelasCriadas) {
                criarTabelas();
                tabelasCriadas = true;
            }
        }
        return connection;
    }
    
    private static void criarTabelas() {
        try (Statement stmt = connection.createStatement()) {
            // Tabela de clientes
            String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) NOT NULL," +
                    "cpf VARCHAR(14) NOT NULL," +
                    "sexo VARCHAR(20)," +
                    "uf VARCHAR(2)," +
                    "newsletter TINYINT DEFAULT 0" +
                    ")";
            stmt.execute(sqlClientes);
            
            // Tabela de produtos
            String sqlProdutos = "CREATE TABLE IF NOT EXISTS produtos (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "codigo VARCHAR(50) NOT NULL," +
                    "preco DECIMAL(10,2)," +
                    "categoria VARCHAR(50)," +
                    "tipo VARCHAR(50)," +
                    "disponivel TINYINT DEFAULT 0" +
                    ")";
            stmt.execute(sqlProdutos);
            
            // Tabela de administradores
            String sqlAdmins = "CREATE TABLE IF NOT EXISTS administradores (" +
                    "id INT PRIMARY KEY," +
                    "senha VARCHAR(50) NOT NULL" +
                    ")";
            stmt.execute(sqlAdmins);
            
            // Inserir admin padrão se não existir
            String verificarAdmin = "SELECT COUNT(*) FROM administradores WHERE id = 1";
            var rs = stmt.executeQuery(verificarAdmin);
            if (rs.next() && rs.getInt(1) == 0) {
                String inserirAdmin = "INSERT INTO administradores (id, senha) VALUES (1, '1234')";
                stmt.execute(inserirAdmin);
            }
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}

