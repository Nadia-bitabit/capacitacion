package Models;

import java.util.Date;

public class Operacion {
    private int nroOperacion;
    private double monto;
    private Date fecha;
    private Cliente clienteDestino;

    public Operacion(int nroOperacion, Date date, double monto) {
        this.nroOperacion = nroOperacion;
        this.fecha = date;
        this.monto = monto;
    }
    public double monto() {
        return monto;
    }
    public Date getFecha() {
        return fecha;
    }
    public int getNroOperacion() {
        return nroOperacion;
    }

}
