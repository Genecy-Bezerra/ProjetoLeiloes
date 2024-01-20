
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir registro no banco de dados.");
        }
        JOptionPane.showMessageDialog(null, "Produto inserido com sucesso" );
    }

    public ArrayList<ProdutosDTO> listarProdutos(){
        var produtos = new ArrayList<ProdutosDTO>();

        try {
            // Conectamos com o banco de dados MySQL
            conectaDAO bd = new conectaDAO();
            bd.conectar();

            // Construir a query e executar lá no MySQL
            String sql = "SELECT * FROM produtos";
            PreparedStatement comando = bd.getConexao().prepareStatement(sql);
            ResultSet resposta = comando.executeQuery();

            // Criar uma lista de produto baseado no ResultSet que tivemos do banco de dados
            while (resposta.next()) {
                // Declaro um objeto produto vazio
                var p = new ProdutosDTO();

                // Vou populando esse objeto produto com as informações do banco
                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("status"));

                // Adiciono a lista de produto
                produtos.add(p);
            }

            bd.desconectar();

        } catch (SQLException ex) {
            System.out.println("Erro ao listar registros do banco de dados.");
        }
        return produtos;
    }
    
    public void venderProduto(int id) {
        

            // Conexão com o banco
            conectaDAO bd = new conectaDAO();
            bd.conectar();

//  ADICIONAR OS CAMPOS QUE VOCE VAI TER QUE INSERIR
            String sql = "UPDATE  produtos SET status =? WHERE id=?";
            
            try{
            PreparedStatement comando = bd.getConexao().prepareStatement(sql);
            comando.setString(1, "Vendido");
            comando.setInt(2, id);

            comando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Vendido com sucesso" ); 
            bd.desconectar();
            
        } catch (SQLException ex) {
            System.out.println("Não foi possível vender o produto " + ex.getMessage());
        }       
      
      }
      
      
    }
 
    
    


