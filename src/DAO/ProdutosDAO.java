package DAO;

import DTO.ProdutosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto(ProdutosDTO produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, valor, status) VALUES (?, ?, ?)";
        
        try (Connection conn = conectaDAO.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Inserir o produto
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getValor());
            pstmt.setString(3, produto.getStatus()); // Corrigido para o índice 3
            
            // Executar
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }
     public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto where status = 'Vendido'";

        try (Connection conn = conectaDAO.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id")); // Certifique-se de que a coluna "id" existe
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
        
        return produtos; // Retorna a lista de produtos
    }
     
     public String buscarStatusProduto(int id) {
    String status = null;
    try (Connection conn = conectaDAO.getConnection(); 
         PreparedStatement stmt = conn.prepareStatement("SELECT status FROM produto WHERE id = ?")) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            status = rs.getString("status");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return status;
}

public boolean venderProduto(int id) {
    try (Connection conn = conectaDAO.getConnection(); 
         PreparedStatement stmt = conn.prepareStatement("UPDATE produto SET status = 'vendido' WHERE id = ?")) {
        stmt.setInt(1, id);
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
     
     
     
     
}