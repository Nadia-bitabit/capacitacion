package com.bitabit.nadia;

import java.util.*;

public class EntidadBancaria implements Saludos {

    private String sucursal;
    private String email;
    private HashMap<Cliente, ArrayList<Cuenta>> cuentasClientes;

    public EntidadBancaria() {
        this.sucursal = "Berazategui";
        this.email = "banco@email.com";
        this.cuentasClientes = new HashMap<Cliente, ArrayList<Cuenta>>();
    }

    @Override
    public void saludar() {
        System.out.println("¡Bienvenido/a al Banco!");
    }

    public Set<Cliente> getClientes() {
        return this.cuentasClientes.keySet();
    }

    public void agregarCliente(Cliente cliente) {
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        this.cuentasClientes.put(cliente, cuentas );
    }

    public void eliminarCliente(Cliente cliente) {
        this.cuentasClientes.remove(cliente);
    }

    public void abrirCuenta(Cuenta cuenta) {
        Cliente titular = cuenta.getTitular();
        this.cuentasClientes.get(titular).add(cuenta);
    }

    public void saludaConNombre(String nombre) {
        this.getClientes().stream()
                .filter(cliente -> cliente.getNombre().equals(nombre))
                .forEach(cliente -> { System.out.println("Soy " + cliente.getNombre() + " desde un stream!"); });

    }

    public void ordenadosAlfabeticamenteXNombre() {
        this.getClientes().stream()
                .sorted(Comparator.comparing(Cliente::getNombre))
                .forEach(cliente ->  { System.out.println("Soy " + cliente.getNombre() + "!"); });
        //.collect(Collectors.toList()); //Devuelve una lista ordenada

    }
    public static void transferir(Cuenta origen, Cuenta destino, int monto) {
        Cliente titular = origen.getTitular();
        synchronized (origen) {
            synchronized (destino) {
                if (origen.getSaldo() > monto) {
                    Transferencia transferencia = new Transferencia(25, new Date(), monto);
                    destino.addMovimiento(transferencia);
                    System.out.println(Thread.currentThread().getName() +
                            " transfirió " + monto + " de " + titular.getNombre());
                } else {
                    System.out.println(Thread.currentThread().getName() +
                            " no pudo transferir, saldo insuficiente de " + titular.getNombre());
                }
            }
        }
    }
}
