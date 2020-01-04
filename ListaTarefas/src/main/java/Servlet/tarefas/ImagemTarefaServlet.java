package Servlet.tarefas;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

@WebServlet(name="ImagemTarefaServlet", urlPatterns={"/ImagemTarefaServlet"})
public class ImagemTarefaServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");
        
        String idUsuario = String.valueOf(u.getId());
        String nomeImagem = request.getParameter("imagem");
        String uploadsDirectory = "C:\\Users\\luizh\\OneDrive\\Documentos\\NetBeansProjects\\CadastroDeUsuariosWeb\\uploads";
        
        File pastaUsuario = new File(uploadsDirectory, idUsuario);
        
        if(pastaUsuario.exists()) {
            File caminhoImagem = new File(pastaUsuario, nomeImagem);
            
            try(InputStream stream = Files.newInputStream(caminhoImagem.toPath(), StandardOpenOption.READ)) {
                
                String contentType = URLConnection.guessContentTypeFromStream(stream);
                response.setContentType(contentType);
                
                byte[] buffer = new byte[10240];
                
                while(stream.read(buffer) != -1){
                    response.getOutputStream().write(buffer);
                }
            }
        }
        
        

    } 

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
