package br.com.pi.dao;

import br.com.pi.db.DatabaseConnection;
import br.com.pi.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, nome, email, cpf, sexo, uf, newsletter FROM clientes ORDER BY nome";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("sexo"),
                    rs.getString("uf"),
                    rs.getInt("newsletter") == 1
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }

        return clientes;
    }

    public boolean editar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, email = ?, cpf = ?, sexo = ?, uf = ?, newsletter = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getSexo());
            pstmt.setString(5, cliente.getUf());
            pstmt.setInt(6, cliente.isNewsletter() ? 1 : 0);
            pstmt.setInt(7, cliente.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao editar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
            return false;
        }
    }
}





