<%

    String erro = (String) request.getAttribute("erro");
    String sucesso = (String) request.getAttribute("sucesso");

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <title>Cadastro | Lista de Tarefas</title>

    </head>

    <body>
        <br><br><br><br>

        <div class="container" style="width: 25%">

            <div id="mensagemSucesso">
                <% try { %>
                    <% if(sucesso.length() > 0) {%>
                    <div class="alert alert-success">
                        <p>
                            <%= "<strong>" + sucesso + "</strong>"%>
                            <a href="LoginServlet">Clique aqui para fazer login.</a>
                        </p>
                    </div>
                    <% }%>
                <% } catch (Exception e) { %>
                <% } %>
            </div>

            <div class="panel panel-primary" align="center">

                <div class="panel-heading">
                    <strong>Faça seu cadastro!</strong>
                </div>				

                <form method="POST" action="CadastroServlet">
                    <div class="panel-body">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input type="email" name="email" class="form-control" placeholder="E-mail" />
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input type="password" name="password" class="form-control" placeholder="Senha" />
                        </div>
                    </div>

                    <div class="panel-footer">
                        <input type="submit" value="Criar nova conta" class="btn btn-success" />
                        <input type="button" value="Retornar" class="btn btn-primary" onClick='history.go(-1)' />
                    </div>

                </form>
            </div>

            <div id="mensagensDeErro">

                <% try { %>
                    <% if(erro.length() > 0) { %>
                        <div class="alert alert-danger"> 
                            <p>
                                <%= "<strong>AVISO:</strong> " + erro %>
                            </p>
                        </div>
                    <% } %>
                <% } catch (Exception e) { %>
                <% } %>

            </div>

        </div>
    </body>
</html>
