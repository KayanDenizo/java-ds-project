package br.com.pi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_cadastro?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection() throws SQLException {
        try {
            // Tenta conectar diretamente
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException e) {
            System.err.println("Erro de conexão MySQL: " + e.getMessage());
            System.err.println("Verifique se:");
            System.err.println("1. MySQL está rodando na porta 3306");
            System.err.println("2. O banco 'sistema_cadastro' existe");
            System.err.println("3. Usuário 'root' tem permissão");
            throw e;
        }
    }
}

