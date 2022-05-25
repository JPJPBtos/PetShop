package Controle;
import Modelo.Dog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PetShopDAO {
    private Connection con;
    private PreparedStatement cmd;
    
    
    public PetShopDAO(){
        this.con = Conexao.conectar();
    }
    
    public int inserir(Dog p) throws SQLException{
        try{
            String sql = "INSERT INTO cadastro(nome, raca, dtnasc, cor) VALUES(?,?,?,?);";    
            cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, p.getNome());
            cmd.setString(2, p.getRaca());
            cmd.setString(3, p.getDtnasc());
            cmd.setString(4, p.getCor());
            
            if(cmd.executeUpdate() > 0){
                ResultSet rs = cmd.getGeneratedKeys();
                return (rs.next()) ? rs.getInt(1) : -1;
            }else{
                return -1;
            }
        }catch(Exception e){
            System.out.println("ERRO: " + e.getMessage());
            return -1;
        }finally{
            Conexao.desconectar(con);
        }
    }
    
    public int atualizar(Dog p){
        try{
            String sql= "UPDATE cadastro SET nome= ?, raca= ?, dtnasc= ?, cor= ? WHERE id = ?";
            cmd = con.prepareStatement(sql);
            cmd.setString(1, p.getNome());
            cmd.setString(2, p.getRaca());
            cmd.setString(3, p.getDtnasc());
            cmd.setString(4, p.getCor());
            cmd.setInt(5, p.getId());
            
            if(cmd.executeUpdate() > 0){
                return p.getId();
            }else{
                return -1;
            }
        }catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
            return -1;
        }finally{
            Conexao.desconectar(con);
        }
    }
    
    public int deletar(Dog p){
        try{
            String sql = "DELETE FROM cadastro WHERE id = ?";
            cmd = con.prepareStatement(sql);
            cmd.setInt(1, p.getId());
            return cmd.executeUpdate();
        }catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
            return -1;
        }finally{
            Conexao.desconectar(con);
        }
    }
    
    public List<Dog> listar(){
        try{
            String sql = "SELECT * FROM cadastro ORDER BY id";
            cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            List<Dog> lista = new ArrayList<>();
            while(rs.next()){
                Dog p = new Dog();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setRaca(rs.getString("raca"));
                p.setDtnasc(rs.getString("dtnasc"));
                p.setCor(rs.getString("cor"));
                lista.add(p);
            }
            return lista;
        }catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }finally{
            Conexao.desconectar(con);
        }
    }
    
    public List<Dog> pesquisarPorNome(String nome){
        try{
            String sql = "SELECT * FROM cadastro WHERE nome LIKE ? ORDER BY nome";
            cmd = con.prepareStatement(sql);
            cmd.setString(1, "%" + nome + "%");
            ResultSet rs = cmd.executeQuery();
            List<Dog> lista = new ArrayList<>();
            while (rs.next()){
                Dog p = new Dog();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setRaca(rs.getString("raca"));
                p.setDtnasc(rs.getString("dtnasc"));
                p.setCor(rs.getString("cor"));
                lista.add(p);
            }
            return lista;
        }catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }finally{
            Conexao.desconectar(con);
        }
    }   
}
