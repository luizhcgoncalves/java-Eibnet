<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="jspf/header.jspf" %>
<%@include file="jspf/menu.jspf" %>

<div class="container" align="center">
    <c:choose>

        <c:when test="${tarefas.size() == 0}">
            <p>Você ainda não possui nenhuma tarefa! <a href="CadastrarTarefas">Clique aqui para começar a construir sua lista de tarefas!</a></p>
        </c:when>

        <c:otherwise>
            <%@include file="jspf/listatarefas.jspf" %>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
