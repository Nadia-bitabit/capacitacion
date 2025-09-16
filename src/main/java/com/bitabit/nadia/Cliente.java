package com.bitabit.nadia;

import java.util.ArrayList;

public class Cliente extends Persona implements Saludos {
    private ArrayList<Cuenta> cuentas;

    public Cliente(String nombre, String apellido) {
        super(nombre, apellido);
    }

    @Override
    public boolean esClienta() {
        return true;
    }

    @Override
    public void saludar() {
        System.out.println("¡Hola, " + this.getNombre() + "!");
    }

    public void ejecutarOperacion(Operacion operacion, Cuenta cuentaPatagonia) {
        cuentaPatagonia.addMovimiento(operacion);
    }
}
