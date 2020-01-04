package Servlet.tarefas;

import dao.TarefaDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Usuario;
import model.Tarefa;

@WebServlet(name = "CadastrarTarefas", urlPatterns = {"/CadastrarTarefas"})
@MultipartConfig
public class CadastrarTarefas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/cadastrartarefa.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");

        String descricao = request.getParameter("descricao");

        //Início do processamento da imagem
        String imagem = null;
        Part arqImagem = request.getPart("imagem"); //Recebe a imagem que foi enviada
        String nomeImagem = Paths.get(arqImagem.getSubmittedFileName()).getFileName().toString(); //pega o nome da imagem enviada e transforma em String

        String uploadDirectory = "C:\\Users\\luizh\\OneDrive\\Documentos\\NetBeansProjects\\CadastroDeUsuariosWeb\\uploads"; //Define para onde a imagem vai

        String idUsuario = String.valueOf(u.getId());

        File uploadsDir = new File(uploadDirectory, idUsuario); //Define que a imagem vai para o diretório com o id do usuário

        if (uploadsDir.exists() || uploadsDir.mkdir()) { //OU o diretório já existe, OU ele cria o diretório
            
            File caminhoImagem = new File(uploadsDir, nomeImagem); //Define o endereço diretório/imagem
            
            try(InputStream stream = arqImagem.getInputStream()){
                Files.copy(stream, caminhoImagem.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                imagem = nomeImagem; //Se não houver nada para submeter, imagem continua null
            }
        }

        Tarefa t = new Tarefa();
        t.setDescricao(descricao);
        t.setFinalizada(false);
        t.setIdUsuario(u.getId());
        t.setNomeImagem(imagem);

        boolean sucesso = TarefaDAO.inserirNovaTarefa(t);

        if (sucesso) {
            response.sendRedirect("ListarTarefas");
        } else {
            request.setAttribute("erro", "Ocorreu um erro no cadastro da tarefa");
            request.getRequestDispatcher("WEB-INF/cadastrartarefa.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
