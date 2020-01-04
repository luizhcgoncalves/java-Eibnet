<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="jspf/header.jspf" %>
<%@include file="jspf/menu.jspf" %>

<div class="container" align="center">
    <form method="POST" action="CadastrarTarefas" class="form-horizontal" enctype="multipart/form-data">
        <div class="panel panel-primary" style="width: 50%">
            <div class="panel-heading" align="left">Cadastrar nova tarefa</div>
            <br>
            <div class="form-group" style="width: 90%">
                <label class="col-sm-2 control-label">Descrição: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="descricao" placeholder="Descrição da nova tarefa" />
                </div>
            </div>
            <div class="form-group" style="width: 90%">
                <label class="col-sm-2 control-label">Imagem: </label>
                <div class="col-sm-10">
                    <label class="btn btn-default btn-file">
                        Selecionar imagem para tarefa <input type="file" name="imagem" style="display: none" />
                    </label>
                </div>
            </div>

        </div>
        <input type="submit" value="Incluir nova tarefa" class="btn btn-info" />
    </form>
    <br>
    <%@include file="jspf/merro.jspf" %>
</div>
</body>
</html>
