package DAO;

import DTO.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto) throws SQLException{
        
       try {
               
              conn = new conectaDAO().connectDB();
             
        //Inserir o produto
        String sql = "INSERT INTO produto (nome, valor, status) values (?,?,?)";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getValor());
            pstmt.setString(2, produto.getStatus());
            
           //Executar
            pstmt.executeUpdate();
         
          } catch (SQLException e) {
            System.out.println(e);
        }
              
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

