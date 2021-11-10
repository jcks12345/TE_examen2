<%@page import="com.emergentes.modelo.Evento"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Evento> Evento = (List<Evento>)request.getAttribute("evento");
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>
                    <h2>SEGUNDO PARCIAL TEM-742</h2>
                    <h2>Nombre: Juan Carlos Kantuta Siñani</h2>
                    <h2>Carnet: 6720278 LP.</h2>
                </th>
            </tr>
        </table>
        <h1>Lista de Seminarios</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border=1">
            <tr>
                <th>Id</th>
                <th>Titulo</th>
                <th>Expositor</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Cupo</th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${evento}">
            <tr>
                <td>${item.id}</td>
                <td>${item.titulo}</td>
                <td>${item.expositor}</td>
                <td>${item.fecha}</td>
                <td>${item.hora}</td>
                <td>${item.cupo}</td>
               <td><a href="MainController?op=editar&id=$(item.id)">Editar</a></td>
                <td><a href="MainController?op=eliminar&id=${item.id}" 
                       onclick="return(confirm('Está seguro ???'))">Eliminar</a></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
