package br.com.pi.dao;

import br.com.pi.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    
    public boolean validarLogin(int id, String senha) {
        String sql = "SELECT * FROM administradores WHERE id = ? AND senha = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.setString(2, senha);
            
            ResultSet rs = pstmt.executeQuery();
            boolean valido = rs.next();
            rs.close();
            
            return valido;
            
        } catch (SQLException e) {
            System.err.println("Erro ao validar login: " + e.getMessage());
            return false;
        }
    }
}

