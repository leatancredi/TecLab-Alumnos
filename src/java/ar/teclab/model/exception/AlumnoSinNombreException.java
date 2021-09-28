package ar.teclab.model.exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author user
 */
public class AlumnoSinNombreException extends Exception {

    /**
     * Creates a new instance of <code>AlumnoSinNombre</code> without detail
     * message.
     */
    public AlumnoSinNombreException() {
    }

    /**
     * Constructs an instance of <code>AlumnoSinNombre</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public AlumnoSinNombreException(String msg) {
        super(msg);
    }
}
