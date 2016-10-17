<%-- 
    Document   : Resposta
    Created on : 16/10/2016, 21:16:18
    Author     : natan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Contatos</title>
  </head>
  <body>
    <h1>Lista de Contatos</h1>
    <div>
      <p>Nome: <c:out value="${nome}" /></p>
      <p>Data de nascimento:<fmt:formatDate value="${dtnascimento}" pattern="dd/MM/yyyy" /></p>
      <p>Telefone: <c:out value="${telefone}"/></p>
      <p>E-mail: <c:out value="${email}" /></p>
      
    <a href="Formulario">Voltar para a tela de cadastro</a>
  </p>
</div>
</body>
</html>