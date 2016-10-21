<%-- 
    Document   : Resposta
    Created on : 16/10/2016, 21:16:18
    Author     : Natanael
--%>


<%@page import="agenda_web.agenda.entidade.Contato"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de contatos</h1>
        <c:forEach items="${listaPessoas}" var="p">
            <div>
                <p><c:out value="${p.nome}" /></p>
                <p><c:out value="${p.email}" /></p>
                <c:choose>
                    <c:when test="${p.sexo == 1}">
                        <p>Masculino</p>
                    </c:when>
                    <c:otherwise>
                        <p>Feminino</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
    <a href="Formulario">Voltar para a tela de cadastro</a>
    </body>
</html>

