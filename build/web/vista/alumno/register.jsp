<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Alumno</title>
    </head>
    <body>
        <h1>Registrar Alumno</h1>
        <form action="alumno?action=register" method="post">
            <table border="1">
                <tr>
                    <td><label for="dni">DNI:</label></a></td>		
                    <td><input type="text" id="dni" name="dni"/></td>	
                </tr>
                <tr>
                    <td><label for="nombre">Nombre:</label></a></td>		
                    <td><input type="text" id="nombre" name="nombre"/></td>	
                </tr>
                <tr>
                    <td><label for="apellido">Apellido:</label></a></td>		
                    <td><input type="text" id="apellido" name="apellido"/></td>	
                </tr>
                <tr>
                    <td><label for="email">Email:</label></a></td>		
                    <td><input type="text" id="email" name="email"/></td>	
                </tr>
                <tr>
                    <td><label for="nota">Nota:</label></a></td>		
                    <td><input type="text" id="nota" name="nota"/></td>	
                </tr>
            </table>
            <br />
            <input type="submit" value="Registrar alumno" name="agregar">
        </form>
    </body>
</html>