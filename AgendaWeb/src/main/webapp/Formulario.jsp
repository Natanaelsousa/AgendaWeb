<%-- 
    Document   : Formulario
    Created on : 16/10/2016, 21:14:59
    Author     : Natanael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrada</title>
        <c:url value="CSS/estilo.css" var="urlEstilos" />
        <link rel="stylesheet" href="${urlEstilos}" />
    </head>
    <body>
        <c:url value="Formulario" var="urlFormulario" />
        <form action="${urlFormulario}" method="post">
            <h1>Agenda de contatos</h1>
            <fieldset>
                <p class="nome">
                    <label for="nomeid">Nome</label>
                    <input type="text" id="nomeid" placeholder="Fulano de tal" required="required" name="nome" />
                </p>

                <p class="nascimetno">
                    <label title="Sua data de nascimento" for="cNascimento">Data de nascimento</label>
                    <input required="required" type="date" data-date="" data-date-format="DD MMMM YYYY" name="nascimento" id="cNascimento"/>
                </p>

                <p class="telefone">
                    <label for="foneid">Telefone</label>
                    <input type="text" id="foneid" placeholder="(xx)xxx-xx-xx-xx" required="required" name="telefone" />
                </p>

                <p class="email">
                    <label for="email">Email</label>
                    <input type="email" id="emailid" placeholder="fulano@mail.com" required="required" name="email" />
                </p>

                <div class="bot">
                    <button type="submit">Cadastrar</button>
                    <button type="reset">Limpar</button>
                </div>

            </fieldset>
        </form>
    </form>
</body>
</html>
