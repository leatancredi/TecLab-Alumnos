/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.teclab.controller;

import ar.teclab.model.Alumno;
import ar.teclab.model.exception.AlumnoSinNombreException;
import ar.teclab.service.AlumnoService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet("/alumno")
public class AlumnoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AlumnoService alumnoService;

    @Override
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        try {
            Properties dbProperties = new Properties();
            dbProperties.setProperty("user", jdbcUsername);
            dbProperties.setProperty("password", jdbcPassword);
            alumnoService = new AlumnoService(jdbcURL, dbProperties);
        } catch (Exception exception) {
            // TODO: agregar logueo de excepcion
            Logger.getLogger(AlumnoController.class.getName()).log(Level.WARNING, "AlumnoController.init: ocurro una excepcion: " + exception.getMessage());
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlumnoController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                index(request, response);
                return;
            }
            switch (action) {
                case "index":
                    index(request, response);
                    break;
                case "nuevo":
                    nuevo(request, response);
                    break;
                case "register":
                    registrar(request, response);
                    break;
                case "mostrar":
                    mostrar(request, response);
                    break;
                case "showedit":
                    showEditar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException exception) {
            //TODO loguear excepcion
            Logger.getLogger(AlumnoController.class.getName()).log(Level.WARNING, "AlumnoController.doGet: ocurro una excepcion SQL: " + exception.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        //mostrar(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        long dni = Long.parseLong(request.getParameter("dni"));
        double nota = Double.parseDouble(request.getParameter("nota"));
        Alumno alumno = new Alumno(dni, nombre, apellido, email, nota);
        try {
            alumnoService.insertar(alumno);
        } catch (AlumnoSinNombreException ex) {
            Logger.getLogger(AlumnoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/alumno/register.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/alumno/mostrar.jsp");
        List<Alumno> listaAlumnos = alumnoService.listarAlumnos();
        request.setAttribute("lista", listaAlumnos);
        request.setAttribute("promedio", alumnoService.calcularPromedio(listaAlumnos));
        dispatcher.forward(request, response);
    }

    private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Alumno alumno = alumnoService.obtenerPorDni(Long.parseLong(request.getParameter("dni")));
        request.setAttribute("alumno", alumno);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/alumno/editar.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Alumno alumno = new Alumno(Long.parseLong(request.getParameter("dni")),
                request.getParameter("nombre"),
                request.getParameter("apellido"),
                request.getParameter("email"),
                Double.parseDouble(request.getParameter("nota")));
        alumnoService.actualizar(alumno);
        index(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Alumno alumno = alumnoService.obtenerPorDni(Long.parseLong(request.getParameter("dni")));
        alumnoService.eliminar(alumno);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
