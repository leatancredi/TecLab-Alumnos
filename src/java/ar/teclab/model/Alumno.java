/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.teclab.model;

/**
 *
 * @author user
 */
public class Alumno {

    private long dni;
    private String nombre;
    private String apellido;
    private String email;
    private double nota;

    public Alumno(long dni, String nombre, String apellido, String email, double nota) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", nota=" + nota + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public long getDni() {
        return dni;
    }

    public double getNota() {
        return nota;
    }

    public String getEmail() {
        return email;
    }
public boolean aprobado (){
    if (nota >= 60){
    return true;
   }else{
    return false;
    }
}   

}
