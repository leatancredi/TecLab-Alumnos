<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrar Alumnos</title>
</head>
<body>
	<h1>Lista  Alumnos</h1>
	<table>
		<tr>
			<td><a href="alumno?action=index" >Ir al menú</a> </td>
		</tr>
	</table>
        <br />
        <a href="alumno?action=nuevo">Nuevo alumno</a>
        <br />
	<table border="1" width="80%">
		<tr>
		 <td>DNI</td>
		 <td>NOMBRE</td>
		 <td>APELLIDO</td>
                 <td>EMAIL</td>
		 <td>NOTA</td>
		 <td colspan=2>ACCIONES</td>
		</tr>
		<c:forEach var="alumno" items="${lista}">
			<tr>
				<td><c:out value="${alumno.dni}"/></td>
				<td><c:out value="${alumno.nombre}"/></td>
				<td><c:out value="${alumno.apellido}"/></td>
                                <td><c:out value="${alumno.email}"/></td>
				<td><c:out value="${alumno.nota}"/></td>
				<td><a href="alumno?action=showedit&dni=<c:out value="${alumno.dni}" />">Editar</a></td>
				<td><a href="alumno?action=eliminar&dni=<c:out value="${alumno.dni}"/>">Eliminar</a> </td>				
			</tr>
		</c:forEach>
	</table>
        <p>Promedio: <c:out value="${promedio}"/></p>

</body>
</html>