<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="jspf/header.jspf" %>
<%@include file="jspf/menu.jspf" %>

        <div class="container" align="center">
            <%@include file="jspf/msucesso.jspf" %>
            <form method="POST" action="PerfilServlet" enctype="multipart/form-data">
                <div class="panel panel-primary" style="width: 30%">
                    <div class="panel-heading" align="left">Perfil do usuário</div>
                    <br>
                    <div class="form-group" align="left" style="width: 90%">
                        <p>
                            <label>E-mail: </label>
                            <input type="email" value="${usuario.email}" size="25" disabled />
                        </p>

                        <p>
                            <label>Senha: </label>
                            <input type="password" name="senha" id="senha" size="25" value="${usuario.password}" />
                            <span class="glyphicon glyphicon-eye-open" onClick="showPassword()"></span>
                        </p>

                        <!-- Stolen from w3schools.com -->
                        <script>
                            function showPassword() {
                                var x = document.getElementById("senha");
                                if (x.type === "password") {
                                    x.type = "text";
                                } else {
                                    x.type = "password";
                                }
                            }
                        </script>

                        <p>
                            <label>Imagem: </label>
                            
                            <c:if test="${usuario.imagem != null}">
                                <img src="ImagemPerfilServlet" width="200" height="200" />
                            </c:if>
                                
                            <c:if test="${usuario.imagem == null}">
                                <img src="http://placehold.it/200x200" width="200" height="200" />
                            </c:if>
                                
                            <br><br>
                            <label class="btn btn-default btn-file">
                                Selecionar nova imagem <input type="file" name="imagem" style="display: none" />
                            </label>
                        </p>
                    </div>

                </div>
                <input type="submit" value="Salvar alterações" class="btn btn-info" />
            </form>
            <%@include file="jspf/merro.jspf" %>
        </div>
    </body>
</html>