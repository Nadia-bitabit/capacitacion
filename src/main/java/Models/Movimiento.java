package Models;

import java.util.ArrayList;

public class Movimiento {
    private ArrayList<Operacion> operaciones;

    public Movimiento() {
        this.operaciones = new ArrayList<>();
    }
    public void agregarOperacion(Operacion operacion) {
        this.operaciones.add(operacion);
    }
    public ArrayList<Operacion> getOperaciones() {
        return operaciones;
    }

}
