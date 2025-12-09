package br.com.pi.dao;

import br.com.pi.db.DatabaseConnection;
import br.com.pi.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, codigo, preco, categoria, tipo, disponivel FROM produtos ORDER BY nome";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("codigo"),
                    rs.getDouble("preco"),
                    rs.getString("categoria"),
                    rs.getString("tipo"),
                    rs.getInt("disponivel") == 1
                );
                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }

    public boolean editar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, codigo = ?, preco = ?, categoria = ?, tipo = ?, disponivel = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getCodigo());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setString(4, produto.getCategoria());
            pstmt.setString(5, produto.getTipo());
            pstmt.setInt(6, produto.isDisponivel() ? 1 : 0);
            pstmt.setInt(7, produto.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao editar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir produto: " + e.getMessage());
            return false;
        }
    }
}
