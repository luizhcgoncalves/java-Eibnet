package model;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {

    private Integer id;
    private String email;
    private String password;

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Usuario(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean verificarEmail(String email) {
        boolean emailCadastrado = true;
        String emailJaCadastrado = null;

        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

            //Procurar e-mail no banco de dados
            PreparedStatement checkEmail = connection.prepareStatement("SELECT * from cadastrodeusuarios.usuarios WHERE des_email = ?");
            checkEmail.setString(1, email);

            ResultSet rs = checkEmail.executeQuery();

            while (rs.next()) {
                emailJaCadastrado = rs.getString("des_email");
            }
            //Fim da procura do e-mail no banco

            //E-mail não encontrado
            if (emailJaCadastrado == null) {
                emailCadastrado = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emailCadastrado;
    }

    public static boolean verificarSenha(String password) {
        boolean senhaCorreta = false;

        if (password.length() >= 6 && password.length() < 11) {
            senhaCorreta = true;
        }

        return senhaCorreta;
    }

}
