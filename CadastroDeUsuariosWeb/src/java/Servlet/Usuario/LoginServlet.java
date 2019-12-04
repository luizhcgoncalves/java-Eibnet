package Servlet.Usuario;

import dao.UsuarioDAO;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private String email = null;
    private String password = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("erros", null);

        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        email = request.getParameter("email");
        password = request.getParameter("password");

        Usuario u = new Usuario(email, password);

        if(UsuarioDAO.login(u) != null){
            u = UsuarioDAO.login(u);
            
            HttpSession session = request.getSession();
            session.setAttribute("usuario", u);
            
            response.sendRedirect("tarefas.jsp");
        } else {
            request.setAttribute("erro", "Usuário e/ou senha incorretos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
