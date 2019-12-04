package Servlet.tarefas;

import dao.TarefaDAO;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Tarefa;

import model.Usuario;

@WebServlet(name = "ListarTarefas", urlPatterns = {"/ListarTarefas"})
public class ListarTarefas extends HttpServlet {

    private Integer id;
    private String descricao;
    private boolean finalizada;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");

        ArrayList<Tarefa> tarefas = TarefaDAO.buscarTarefasDoUsuario(u);
        u.setTarefas(tarefas);

        request.setAttribute("tarefas", tarefas);

        request.getRequestDispatcher("listadetarefas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");

        ArrayList<Tarefa> tarefas = TarefaDAO.buscarTarefasDoUsuario(u);
        u.setTarefas(tarefas);

        request.setAttribute("tarefas", tarefas);

        request.getRequestDispatcher("listadetarefas.jsp").forward(request, response);
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
