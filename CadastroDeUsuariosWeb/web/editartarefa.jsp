<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Tarefa"%>
<%
    ArrayList<Tarefa> tarefas = (ArrayList<Tarefa>) request.getAttribute("tarefas");
%>
<%
    String erro = (String) request.getAttribute("erro");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

        <title>Lista de Tarefas</title>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-static-top">
            <a class="navbar-brand" href="#">Lista de Tarefas</a>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a class="nav-link" href="ListarTarefas"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> Listar</a>
                    </li>
                    <li>
                        <a class="nav-link" href="CadastrarTarefas"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Criar</a>
                    </li>
                    <li class="active">
                        <a class="nav-link" href="EditarTarefas"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Editar</a>
                    </li>
                    <li>
                        <a class="nav-link" href="ExcluirTarefas"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Excluir</a>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="nav-link" href="LogoutServlet"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Sair</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container" align="center">
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
                            <% for (Tarefa t : tarefas) {%>
                            <tr>
                                <td><input type="checkbox" name="tarefas" value="<%= t.getIdTarefa()%>" /></td>
                                <td><input type="text" class="form-control input-sm" name="tdescricao" placeholder="<%= t.getDescricao()%>" /></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <input type="submit" value="Editar tarefas selecionadas" class="btn btn-info" />
            </form>
            <br>
            <div id="mensagemErro" style="width: 50%">
                <% try { %>
                <% if (erro.length() > 0) {%>
                <div class="alert alert-danger">
                    <p>
                        <%= "<strong>Aviso: </strong>" + erro%>
                    </p>
                </div>
                <% } %>
                <% } catch (Exception e) { %>
                <% }%>
            </div>
        </div>
    </body>
</html>
