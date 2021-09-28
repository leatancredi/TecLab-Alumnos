/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.teclab.service;

import ar.teclab.model.Alumno;
import ar.teclab.dao.AlumnoDAO;
import ar.teclab.model.exception.AlumnoSinNombreException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author user
 */
public class AlumnoService {
    private static Logger logger = Logger.getLogger(AlumnoService.class.getName());
    private final AlumnoDAO alumnoDAO;

    public AlumnoService(String jdbcURL, Properties dbProperties) {
        this.alumnoDAO = new AlumnoDAO(jdbcURL, dbProperties);
    }
    
    AlumnoService() {
        //OJO! Este constructor solo es para hacer el testCalcularPromedio
        this.alumnoDAO = null;
    }

    public void insertar(Alumno alumno) throws SQLException, AlumnoSinNombreException {
        long inicio = System.currentTimeMillis();
        if(alumno.getNombre()== null){
            throw new AlumnoSinNombreException();
        }
        long fin = System.currentTimeMillis();
        long tiempoDeRespuesta = inicio - fin;
        String texto = ("el tiempo de respuesta del metedo fue " + tiempoDeRespuesta);
        logger.log(Level.INFO,texto);
    }     

    public List<Alumno> listarAlumnos() throws SQLException {
        List<Alumno> alumnos = alumnoDAO.listarAlumnos();
        return alumnos;
    }

    public void actualizar(Alumno alumno) throws SQLException {
        alumnoDAO.actualizar(alumno);
    }

    public Alumno obtenerPorDni(Long dni) throws SQLException {
        return alumnoDAO.obtenerPorDNI(dni);
    }

    public void eliminar(Alumno alumno) throws SQLException {
        alumnoDAO.eliminar(alumno);
    }

    public Double calcularPromedio(List<Alumno> listaAlumnos) {
        double promedio = 0D;
        double sumaDeNotas = 0D;
        for (int index=0; index<listaAlumnos.size(); index++){
            sumaDeNotas = sumaDeNotas + listaAlumnos.get(index).getNota();
        }
            
        return promedio;
    }
}
    