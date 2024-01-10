
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

public class ProdutosDAO {
    public void cadastrarProduto (ProdutosDTO produto){
        try {
            // Conexão com o banco
            conectaDAO bd = new conectaDAO();
            bd.conectar();

//  ADICIONAR OS CAMPOS QUE VOCE VAI TER QUE INSERIR
            String sql = "INSERT INTO produtos (nome,valor,status) VALUES (?,?,?)";
            PreparedStatement comando = bd.getConexao().prepareStatement(sql);
            comando.setString(1, produto.getNome());
            comando.setInt(2, produto.getValor());
            comando.setString(3, produto.getStatus());

            comando.execute();
            bd.desconectar();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro no banco de dados.");
        }
        JOptionPane.showMessageDialog(null, "Produto inserido com sucesso" );
    }
}

