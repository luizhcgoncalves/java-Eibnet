package Servlet.Usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;

import dao.UsuarioDAO;

@WebServlet(name = "CadastroServlet", urlPatterns = {"/CadastroServlet"})
public class CadastroServlet extends HttpServlet {

    private String email = "";
    private String password = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("erro", "");
        request.setAttribute("sucesso", "");

        request.getRequestDispatcher("cadastro.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        email = request.getParameter("email");
        password = request.getParameter("password");

        Usuario u = new Usuario();
        u.setEmail(email);
        u.setPassword(password);

        if (Usuario.verificarEmail(email)) {
            request.setAttribute("erro", "Este e-mail já está cadastrado no sistema!");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        }

        if (!Usuario.verificarSenha(password)) {
            request.setAttribute("erro", "A senha deve conter entre 6 e 10 caracteres");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        }

        if (UsuarioDAO.inserirUsuario(u)) {
            request.setAttribute("sucesso", "Cadastro realizado com sucesso");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        } else {
            request.setAttribute("erro", "Houve um erro no cadastro! Tente novamente, ou contate o administrador do sistema");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
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
    }

}
