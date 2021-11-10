<%@page import="com.emergentes.modelo.Evento"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    Evento eve = (Evento)request.getAttribute("eve");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><c:if test="$(eve.id == 0)">
                Nuevo
        </c:if>
                <c:if test="$(eve.id != 0)">
                Editar
        </c:if>
            NUEVO EXPOSITOR</h1>
        <form action="MainController" method="post">
            <table>
                <input type="hidden" name="id" value="${eve.id}">
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="${eve.titulo}"></td>
                </tr>
                <tr>
                    <td>Expositor</td>
                    <td><input type="text" name="expositor" value="${eve.expositor}"></td>
                </tr>
                <tr>
                    <td>Fecha</td>
                    <td><input type="text" name="fecha" value="${eve.fecha}"></td>
                </tr>
                <tr>
                    <td>Hora</td>
                    <td><input type="text" name="hora" value="${eve.hora}"></td>
                </tr>
                <tr>
                    <td>Cupo</td>
                    <td><input type="text" name="fecha" value="${eve.cupo}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar" ></td>
                </tr>
                
            </table>
        </form>
    
    </body>
</html>
