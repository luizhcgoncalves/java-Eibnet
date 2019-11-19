package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
                processRequest(request, response);

        response.setContentType("text/html;charset=UTF-8");

        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            PreparedStatement login = conn.prepareStatement("Select * from cadastrodeusuarios.usuarios WHERE des_email = ?");

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            login.setString(1, email);

            ResultSet rs = login.executeQuery();

            while (rs.next()) {
                String emailCapturado = rs.getString("des_email");
                String senhaCapturada = rs.getString("enc_senha");

                if (email.equals(emailCapturado) && password.equals(senhaCapturada)) { //Login com sucesso
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<meta http-equiv=\"Refresh\" content=\"10; url=\"tarefas.html\" />");
                        out.println("<title>Login | Lista de Tarefas</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Login realizado com sucesso!</h1>");
                        out.println("<p>Você será redirecionado em 10 segundos. Caso não queira esperar, basta clicar <a href=\"tarefas.html\">aqui</a>");
                        out.println("</body>");
                        out.println("</html>");
                    } catch (Exception e) {

                    }
                } else { //Falha no login por senha e/ou e-mail inválidos
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Login | Lista de Tarefas</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Falha no login!</h1>");
                        out.println("<p>E-mail e/ou senha incorretos! Tente novamente ou consulte o administrador do sistema.");
                        out.println("</body>");
                        out.println("</html>");
                    } catch (Exception e) {

                    }
                }
            }
        } catch (SQLException e) { //Exceção do try inicial / conexão com o banco de dados

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
