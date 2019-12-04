package Servlet.tarefas;

import dao.TarefaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Tarefa;
import model.Usuario;

@WebServlet(name = "FinalizarTarefaServlet", urlPatterns = {"/FinalizarTarefaServlet"})
public class FinalizarTarefaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");

        String[] tarefasId = request.getParameterValues("tarefas");

        boolean erro = false;

        for (String tId : tarefasId) {
            Tarefa t = new Tarefa();

            int id = Integer.parseInt(tId);
            t.setIdTarefa(id);
            t.setIdUsuario(u.getId());

            boolean sucesso = TarefaDAO.finalizarTarefa(t);

            if (!sucesso) {
                erro = true;
            }
        }
        
        if(erro){
            request.setAttribute("erro", "Ocorreu um erro ao finalizar pelo menos uma das tarefas selecionadas");
            request.getRequestDispatcher("ListarTarefas").forward(request, response);
        } else {
            request.setAttribute("sucesso", "Tarefas finalizadas com sucesso!");
            request.getRequestDispatcher("ListarTarefas").forward(request, response);
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
