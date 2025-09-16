package com.bitabit.nadia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Cuenta {
    private Enum estadoCuenta;
    private String nroCuenta;
    private String tipoDeCuenta;
    private String moneda;
    private double saldo;
    private String fechaApertura;
    private Movimiento movimiento;
    private Cliente titular;

    public Cuenta(Cliente cliente) {
        this.titular = cliente;
        this.movimiento = new Movimiento();
        this.saldo = 0;
        this.estadoCuenta = EstadoCuenta.ACTIVADA;
        this.fechaApertura = new Date().toString();
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return this.titular;
    }

    public ArrayList<Operacion> getMovimientos() {
        return this.movimiento.getOperaciones();
    }

    public void addMovimiento(Operacion operacion) {
        try {
            this.verificarOperacion(operacion);
            this.movimiento.agregarOperacion(operacion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void verificarOperacion(Operacion operacion) throws CuentaException {
        //Como validar mejor?
        double saldoAux = 0;
        if (operacion.getClass() == Extraccion.class) {
            saldoAux = this.saldo - operacion.monto();
            if (saldoAux < 0) {
                throw new RuntimeException("Saldo insuficiente");
            }
        }
        if (operacion.getClass() == Deposito.class) {
            this.saldo = this.saldo + operacion.monto();
        }
        if(this.estadoCuenta == EstadoCuenta.DESACTIVADA){
            throw new CuentaException("La cuenta esta desactivada");
        }
    }

    public void desactivar() {
        this.estadoCuenta = EstadoCuenta.DESACTIVADA;
    }

    public void consultarMovimiento() {
        ArrayList movimientos = this.movimiento.getOperaciones();
        try {
            PrintWriter doc = new PrintWriter("movimientos.txt");
            movimientos.forEach(movimiento -> {
                Operacion operacion = (Operacion) movimiento;
                doc.println("Monto: " + operacion.monto());
                doc.println("Fecha: " + operacion.getFecha());
                doc.println("Operacio: " + operacion.getNroOperacion());
            });
            doc.close();
            System.out.println("Se realizo el doc");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
