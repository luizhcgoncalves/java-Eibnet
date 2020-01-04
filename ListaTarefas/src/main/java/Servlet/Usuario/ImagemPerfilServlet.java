package Servlet.Usuario;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

@WebServlet(name="ImagemPerfilServlet", urlPatterns={"/ImagemPerfilServlet"})
public class ImagemPerfilServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");
        
        InputStream stream = new BufferedInputStream(new ByteArrayInputStream(u.getImagem()));
        
        String contentType = URLConnection.guessContentTypeFromStream(stream);
        
        response.setContentType(contentType);
        response.getOutputStream().write(u.getImagem());
        
    } 

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
