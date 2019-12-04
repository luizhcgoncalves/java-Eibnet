<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                    <li>
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
    </body>
</html>
