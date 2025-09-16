package Clases;

import java.util.ArrayList;

public class Cliente extends Persona {
    private ArrayList<Operacion> operaciones;
    private ArrayList<Cuenta> cuentas;

    public Cliente(String nombre, String apellido) {
        super(nombre, apellido);
        this.operaciones = new ArrayList<Operacion>();
        this.cuentas = new ArrayList<Cuenta>();
    }

    @Override
    public boolean esClienta() {
        return true;
    }

    public void ejecutarOperacion(Operacion movimiento) {
        this.operaciones.add(movimiento);
    }

    public ArrayList<Operacion> getOperaciones() {
        return operaciones;
    }
}
