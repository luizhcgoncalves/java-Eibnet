package Servlet.Usuario;

import dao.UsuarioDAO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Usuario;

@WebServlet(name="PerfilServlet", urlPatterns={"/PerfilServlet"})
@MultipartConfig //Necessário para dizer que essa ServLet é capaz de receber o tipo de dados do formulário
public class PerfilServlet extends HttpServlet {
    
    private String mensagemSucesso = "";
    private String mensagemErro = "";
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/perfil.jsp").forward(request, response);

    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");
        
        String senha = request.getParameter("senha");
        Part imagem = request.getPart("imagem");
        
        InputStream iStream = imagem.getInputStream(); //Recebe os bytes da imagem que está chegando - Não se sabe qual o tamanho do array de bytes
        ByteArrayOutputStream oStream = new ByteArrayOutputStream(); //Vai armazenar os bytes que estão chegando para garantir a integridade do arquivo
        byte[] buffer = new byte[10240]; //Buffer de 10kb - O buffer é o intermediário entre o iStream e o oStream, necessário por não saber o tamanho do arquivo
        
        while(iStream.read(buffer) > 0) { //o iStream fará a leitura dos dados que estão chegando e vai guardar no buffer. Quando não houver mais dados, vai retornar -1
            oStream.write(buffer); //Faz o oStream pegar os dados do buffer e ir acumulando
        }
        
        u.setImagem(oStream.toByteArray());
        u.setPassword(senha);
        
        boolean atualizado = UsuarioDAO.atualizarUsuario(u);
        
        if(atualizado){
            mensagemSucesso = "Perfil atualizado com sucesso!";
        } else {
            mensagemErro = "Erro na atualização do perfil";
        }
        
        request.setAttribute("mensagemErro", mensagemErro);
        request.setAttribute("mensagemSucesso", mensagemSucesso);
        
        request.getRequestDispatcher("/WEB-INF/perfil.jsp").forward(request, response);

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
