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
        Usuario usuario = null;

        try (Connection connection = Conexao.abrirConexao()) {
            PreparedStatement login = connection.prepareStatement("Select * from cadastrodeusuarios.usuarios WHERE des_email = ?");

            login.setString(1, u.getEmail());

            ResultSet rs = login.executeQuery();

            while (rs.next()) {
                usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("des_email"), rs.getString("enc_senha"));
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
}
