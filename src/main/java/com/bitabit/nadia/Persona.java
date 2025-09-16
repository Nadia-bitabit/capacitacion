package com.bitabit.nadia;
public class Persona {
    // Atributos (propiedades)
    String nombre;
    private String apellido;
    private int edad;
    private int dni;
    private String direccion;
    private String telefono;
    private String email;

    // Constructor
    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = 0;
        this.direccion = "";
        this.telefono = "";
    }

    public boolean esClienta() {
        return false;
    }

    public String getNombre() {
        return nombre;
    }
}
