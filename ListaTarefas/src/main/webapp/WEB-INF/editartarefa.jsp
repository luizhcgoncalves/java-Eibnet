<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="jspf/header.jspf" %>
<%@include file="jspf/menu.jspf" %>

<div class="container" align="center">
    <c:choose>
        <c:when test="${tarefas.size() > 0}">
            <form method="POST" action="EditarTarefas" class="form-horizontal">
                <div class="panel panel-primary" style="width: 50%">

                    <div class="panel-heading" align="left">Selecione tarefas a serem editadas</div>


                    <table class="table table-hover table-condensed">
                        <thead>
                            <tr>
                                <th><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></th>
                                <th>Descrição</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${tarefas}" var="t">
                                <tr>
                                    <td><input type="checkbox" name="tarefas" value="${t.idTarefa}" /></td>
                                    <td><input type="text" class="form-control input-sm" name="tdescricao" placeholder="${t.descricao}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <input type="submit" value="Editar tarefas selecionadas" class="btn btn-info" />
            
            </form>
            <br>
            <%@include file="jspf/merro.jspf" %>
        </c:when>

        <c:otherwise>
            <p>Você ainda não possui nenhuma tarefa! <a href="CadastrarTarefas">Clique aqui para começar a construir sua lista de tarefas!</a></p>
        </c:otherwise>

    </c:choose>
</div>
</body>
</html>
