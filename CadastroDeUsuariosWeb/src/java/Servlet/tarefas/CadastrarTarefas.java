package Servlet.tarefas;

import dao.TarefaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import model.Tarefa;

@WebServlet(name = "CadastrarTarefas", urlPatterns = {"/CadastrarTarefas"})
public class CadastrarTarefas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("cadastrartarefa.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");

        String descricao = request.getParameter("descricao");

        Tarefa t = new Tarefa();
        t.setDescricao(descricao);
        t.setFinalizada(false);
        t.setIdUsuario(u.getId());

        boolean sucesso = TarefaDAO.inserirNovaTarefa(t);
        
        if(sucesso){
            response.sendRedirect("ListarTarefas");
        } else {
            request.setAttribute("erro", "Ocorreu um erro no cadastro da tarefa");
            request.getRequestDispatcher("cadastrartarefa.jsp").forward(request, response);
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
