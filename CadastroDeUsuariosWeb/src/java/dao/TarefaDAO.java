package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import model.Tarefa;
import model.Usuario;


public class TarefaDAO {

    public static ArrayList<Tarefa> buscarTarefasDoUsuario(Usuario u){
        ArrayList<Tarefa> tarefas = new ArrayList();
        
        try (Connection connection = Conexao.abrirConexao()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cadastrodeusuarios.tarefas WHERE id_usuario = ?");
            stmt.setInt(1, u.getId());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id_tarefa");
                String descricao = rs.getString("des_descricao");
                boolean finalizada = rs.getBoolean("boo_finalizada");
                int idUsuario = u.getId();
                
                Tarefa t = new Tarefa(id, descricao, finalizada, idUsuario);
                
                tarefas.add(t);
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return tarefas;
    }
    
    public static boolean inserirNovaTarefa(Tarefa t){
        boolean sucesso = false;
        
        try (Connection connection = Conexao.abrirConexao()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO `cadastrodeusuarios`.`tarefas` (`des_descricao`, `boo_finalizada`, `id_usuario`) VALUES (?, ?, ?)");
            
            stmt.setString(1, t.getDescricao());
            stmt.setBoolean(2, t.isFinalizada());
            stmt.setInt(3, t.getIdUsuario());
            
            int linhasAlteradas = stmt.executeUpdate();
            
            if(linhasAlteradas > 0){
                sucesso = true;
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return sucesso;
    }
    
    public static boolean editarTarefa(Tarefa t){
        boolean sucesso = false;
        
        try(Connection connection = Conexao.abrirConexao()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE `cadastrodeusuarios`.`tarefas` SET `des_descricao` = ? WHERE `id_tarefa` = ? AND `id_usuario` = ?");
            
            stmt.setString(1, t.getDescricao());
            stmt.setInt(2, t.getIdTarefa());
            stmt.setInt(3, t.getIdUsuario());
            
            int linhasAlteradas = stmt.executeUpdate();
            
            if(linhasAlteradas > 0){
                sucesso = true;
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return sucesso;
    }
    
    public static boolean excluirTarefa(Tarefa t){
        boolean sucesso = false;
        
        try(Connection connection = Conexao.abrirConexao()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM `cadastrodeusuarios`.`tarefas` WHERE `id_tarefa` = ? AND `id_usuario` = ?");
            
            stmt.setInt(1, t.getIdTarefa());
            stmt.setInt(2, t.getIdUsuario());
            
            int linhasAlteradas = stmt.executeUpdate();
            
            if(linhasAlteradas > 0){
                sucesso = true;
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return sucesso;
    }
    
    public static boolean finalizarTarefa(Tarefa t){
        boolean sucesso = false;
        
        try(Connection connection = Conexao.abrirConexao()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE `cadastrodeusuarios`.`tarefas` SET `boo_finalizada` = ? WHERE `id_tarefa` = ? AND `id_usuario` = ?");
            
            stmt.setBoolean(1, true);
            stmt.setInt(2, t.getIdTarefa());
            stmt.setInt(3, t.getIdUsuario());
            
            int linhasAlteradas = stmt.executeUpdate();
            
            if(linhasAlteradas > 0){
                sucesso = true;
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return sucesso;
    }
    
}
