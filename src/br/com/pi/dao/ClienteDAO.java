package br.com.pi.dao;

import br.com.pi.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    
    public boolean cadastrar(String nome, String email, String cpf, String sexo, String uf, boolean newsletter) {
        String sql = "INSERT INTO clientes (nome, email, cpf, sexo, uf, newsletter) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, cpf);
            pstmt.setString(4, sexo);
            pstmt.setString(5, uf);
            pstmt.setInt(6, newsletter ? 1 : 0);
            
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
            return false;
        }
    }
}


