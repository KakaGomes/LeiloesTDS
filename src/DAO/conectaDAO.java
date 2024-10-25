package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    private static final String URL = "jdbc:mysql://localhost/uc11?useTimezone=true&serverTimezone=America/Sao_Paulo";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public boolean conectar() {
        try {
            Connection conn = getConnection();
            System.out.println("Conexão estabelecida com sucesso.");
            conn.close(); // Fechar a conexão após o uso
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        conectaDAO connectDB = new conectaDAO();
        if (connectDB.conectar()) {
            System.out.println("Conexão bem-sucedida.");
        } else {
            System.out.println("Falha na conexão.");
        }
    }
}
