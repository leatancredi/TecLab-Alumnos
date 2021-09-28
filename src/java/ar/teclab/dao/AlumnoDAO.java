/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.teclab.dao;

import ar.teclab.model.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author user
 */
public class AlumnoDAO extends AbstractJDBCDAO {

    public AlumnoDAO(String jdbcURL, Properties dbProperties) {
        super(jdbcURL, dbProperties);
    }

    public boolean insertar(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO alumno (dni, nombre, apellido, email, nota) VALUES (?, ?, ?, ?, ?)";
        conectar();
        PreparedStatement statement = prepareStatement(sql);
        statement.setLong(1, alumno.getDni());
        statement.setString(2, alumno.getNombre());
        statement.setString(3, alumno.getApellido());
        statement.setString(4, alumno.getEmail());
        statement.setDouble(5, alumno.getNota());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        desconectar();
        return rowInserted;
    }

    public List<Alumno> listarAlumnos() throws SQLException {

        List<Alumno> listaAlumnos = new ArrayList<>();
        String sql = "SELECT dni, nombre, apellido, email, nota FROM alumno";
        conectar();
        ResultSet resulSet = executeQuery(sql);
        while (resulSet.next()) {
            long dni = resulSet.getLong("dni");
            String nombre = resulSet.getString("nombre");
            String apellido = resulSet.getString("apellido");
            String email = resulSet.getString("email");
            double nota = resulSet.getDouble("nota");
            Alumno alumno = new Alumno(dni, nombre, apellido, email, nota);
            listaAlumnos.add(alumno);
        }
        desconectar();
        return listaAlumnos;
    }

    // obtener por id
    public Alumno obtenerPorDNI(long dni) throws SQLException {
        Alumno alumno = null;

        String sql = "SELECT * FROM alumno WHERE dni = ? ";
        conectar();
        PreparedStatement statement = prepareStatement(sql);
        statement.setLong(1, dni);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            alumno = new Alumno(res.getLong("dni"),
                    res.getString("nombre"),
                    res.getString("apellido"),
                    res.getString("email"),
                    res.getDouble("nota"));
        }
        res.close();
        desconectar();

        return alumno;
    }

    // actualizar
    public boolean actualizar(Alumno alumno) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE alumno SET nombre=?,apellido=?,email=?,nota=? WHERE dni=?";
        conectar();
        PreparedStatement statement = prepareStatement(sql);
        statement.setString(1, alumno.getNombre());
        statement.setString(2, alumno.getApellido());
        statement.setString(3, alumno.getEmail());
        statement.setDouble(4, alumno.getNota());
        statement.setLong(5, alumno.getDni());

        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminar(Alumno alumno) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM alumno WHERE dni=?";
        conectar();
        PreparedStatement statement = prepareStatement(sql);
        statement.setLong(1, alumno.getDni());

        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        desconectar();

        return rowEliminar;
    }
}
