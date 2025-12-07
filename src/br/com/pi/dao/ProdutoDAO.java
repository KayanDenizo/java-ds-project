package br.com.pi.dao;

import br.com.pi.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {
    
    public boolean cadastrar(String nome, String codigo, double preco, String categoria, String tipo, boolean disponivel) {
        String sql = "INSERT INTO produtos (nome, codigo, preco, categoria, tipo, disponivel) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nome);
            pstmt.setString(2, codigo);
            pstmt.setDouble(3, preco);
            pstmt.setString(4, categoria);
            pstmt.setString(5, tipo);
            pstmt.setInt(6, disponivel ? 1 : 0);
            
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar produto: " + e.getMessage());
            return false;
        }
    }
}

