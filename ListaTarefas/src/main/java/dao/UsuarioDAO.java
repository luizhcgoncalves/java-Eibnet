package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDAO {
    //Data Access Object - Aqui estarão as funções que usam o objeto para operar o banco

    public static boolean inserirUsuario(Usuario u) {
        boolean sucesso = false;

        try (Connection connection = Conexao.abrirConexao()) {

            PreparedStatement stmt = connection.prepareStatement("Insert INTO cadastrodeusuarios.usuarios"
                    + "(des_email, enc_senha) VALUES (?, ?)");

            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas > 0) {
                sucesso = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    public static Usuario login(Usuario u) {
        Usuario usuario = new Usuario();

        try (Connection connection = Conexao.abrirConexao()) {
            PreparedStatement login = connection.prepareStatement("SELECT * FROM cadastrodeusuarios.usuarios WHERE des_email = ?");

            login.setString(1, u.getEmail());

            ResultSet rs = login.executeQuery();

            while (rs.next()) {
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setEmail(rs.getString("des_email"));
                usuario.setPassword(rs.getString("enc_senha"));
                usuario.setImagem(rs.getBytes("img_imagem"));
            }

            //Se e-mail e senha inseridos são iguais aos do banco de dados
            if (u.getEmail().equals(usuario.getEmail()) && u.getPassword().equals(usuario.getPassword())) {
                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean atualizarUsuario(Usuario u) {
        boolean sucesso = false;

        try (Connection connection = Conexao.abrirConexao()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE cadastrodeusuarios.usuarios"
                    + " SET des_email = ?, enc_senha = ?, img_imagem = ? WHERE (id_usuario = ?)");

            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());
            stmt.setBytes(3, u.getImagem());
            stmt.setInt(4, u.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                sucesso = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}
