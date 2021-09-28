<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actualizar Alumno</title>
</head>
<body>
<h1>Actualizar Alumno</h1>
	<form action="alumno?action=editar" method="post" >
		<table border="1">
			<tr>
				<td><label for="dni">DNI</label></td>
                                <td><input disabled="true" type="text" id="dni" value="<c:out value="${alumno.dni}"></c:out>" ></td>
			</tr>
			<tr>
				<td><label for="nombre">Nombre</label></td>
				<td><input type="text" id="nombre" value='<c:out value="${alumno.nombre}"></c:out>' ></td>
			</tr>
			<tr>
				<td><label for="apellido">Apellido</label></td>
				<td><input type="text" id="apellido" name="apellido" value='<c:out value="${alumno.apellido}"></c:out>' ></td>
			</tr>
                        <tr>
                            <td><label for="email">Email:</label></a></td>		
                            <td><input type="text" id="email" name="email" value='<c:out value="${alumno.email}"></c:out>' ></td>	
                        </tr>
			<tr>
				<td><label for="nota">Nota</label></td>
				<td><input type="text" id="nota" value='<c:out value="${alumno.nota}"></c:out>' ></td>
			</tr>
		</table>
	
		<input type="submit" name="registrar" value="Guardar"> 
	</form>

</body>
</html>