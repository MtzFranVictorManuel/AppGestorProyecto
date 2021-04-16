/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author azul_
 */
public class Integrante {
    public String nombre;
    public String apellido;
    public String cargo;
    public String fechaNacimiento;
    public String curp;
    public String email;
    public String contrasena;

    public Integrante() {
    }

    public Integrante(String nombre, String apellido, String cargo, String fechaNacimiento, String curp, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.fechaNacimiento = fechaNacimiento;
        this.curp = curp;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Integrante(String nombre, String apellido, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
}
