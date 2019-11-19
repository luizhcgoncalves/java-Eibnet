package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import model.Usuario;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "CadastroServlet", urlPatterns = {"/CadastroServlet"})
public class CadastroServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastroServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastroServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        response.setContentType("text/html;charset=UTF-8");

        try {
            //Conectar ao banco de dados
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

            PreparedStatement cadastro = conn.prepareStatement("Insert INTO cadastrodeusuarios.usuarios"
                    + "(des_email, enc_senha) VALUES (?, ?)");

            //List<Usuario> usuarios = new ArrayList<>();
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            //Testar se email está correto
            boolean emailCorreto = false;

            if (email.contains("@") && email.length() > 1) {
                emailCorreto = true;
            }

            //Testar se senha está correta
            boolean senhaCorreta = true;

            if (password.length() < 6 || password.length() > 11) {
                senhaCorreta = false;
            }

            //Validar se ambos senha e e-mail estão corretos
            boolean ok = false;

            if (senhaCorreta && emailCorreto) {
                ok = true;
            }

            if (ok) { //Usuário pronto para ser cadastrado
                cadastro.setString(1, email);
                cadastro.setString(2, password);

                int cadastroComSucesso = cadastro.executeUpdate();

                if (cadastroComSucesso > 0) { //Cadastro inserido no banco de dados com sucesso
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Cadastro Concluído | Lista de Tarefas</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Cadastro feito com sucesso!</h1>");
                        out.println("<a href=\"index.html\">Voltar para a página inicial</a>");
                        out.println("</body>");
                        out.println("</html>");
                    } catch (Exception e) {
                        //Como preencher aqui?
                    }
                } else { //Houve um erro no momento de inserir os dados no banco de dados
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Falha no Cadastro | Lista de Tarefas</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Cadastro não realizado!</h1>");
                        out.println("<p>Erro interno no cadastro. Por favor contate o administrador do sistema.</p>");
                        out.println("<a href=\"index.html\">Voltar para a página inicial</a>");
                        out.println("</body>");
                        out.println("</html>");
                    } catch (Exception e) {
                        //Como preencher aqui?
                    }
                }
            } else { //Houve um erro no e-mail ou senha que inviabilizou o cadastro do novo usuário
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Falha no cadastro | Lista de Tarefas</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Cadastro não realizado!</h1>");
                    out.println("<p>E-mail e/ou senha inválidos. Por favor, tente novamente ou contate o administrador do sistema.");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } catch (SQLException e) {
            System.out.println("Problemas na conexão com o banco de dados!");
            System.out.println("Erro: " + e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
