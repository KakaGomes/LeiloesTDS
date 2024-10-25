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
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

