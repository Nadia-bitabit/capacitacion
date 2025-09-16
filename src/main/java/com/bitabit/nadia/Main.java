package com.bitabit.nadia;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Persona laura = new Persona("Laura", "Perez");
        Operacion deposito = new Deposito(01, new Date(), 2000);
        Operacion extraccion = new Extraccion(01, new Date(), 50);

        //Clientes
        Cliente melody = new Cliente("Melody", "Gonzalez");
        Cliente gaston = new Cliente("Gaston", "Perez");
        Cliente romina = new Cliente("Romina", "Aquiles");
        Cliente angel = new Cliente("Angel", "Villanueva");

        //Cuenta de Melody
        Cuenta cuentaNacionMelody = new Cuenta(melody);
        Cuenta cuentaPatagoniaMelody = new Cuenta(melody);
        Cuenta cuentaGaliciaMelody = new Cuenta(melody);

        //Cuenta de Gaston
        Cuenta cuentaNacionGaston = new Cuenta(gaston);
        Cuenta cuentaPatagoniaGaston = new Cuenta(gaston);
        Cuenta cuentaGaliciaGaston = new Cuenta(gaston);

        //Cuenta de Romina
        Cuenta cuentaNacionRomina = new Cuenta(romina);
        Cuenta cuentaPatagoniaRomina = new Cuenta(romina);
        Cuenta cuentaGaliciaRomina = new Cuenta(romina);

        //Cuenta de Angel
        Cuenta cuentaNacionAngel = new Cuenta(angel);
        Cuenta cuentaPatagoniaAngel = new Cuenta(angel);
        Cuenta cuentaGaliciaAngel = new Cuenta(angel);

        //Entidad Bancaria
        EntidadBancaria entidadBancaria = new EntidadBancaria();
        entidadBancaria.agregarCliente(melody);
        entidadBancaria.agregarCliente(gaston);
        entidadBancaria.agregarCliente(romina);
        entidadBancaria.agregarCliente(angel);

        List<Cuenta> cuentas = Arrays.asList(
                cuentaNacionMelody, cuentaPatagoniaMelody, cuentaGaliciaMelody,
                cuentaNacionGaston, cuentaPatagoniaGaston, cuentaGaliciaGaston,
                cuentaNacionRomina, cuentaPatagoniaRomina, cuentaGaliciaRomina,
                cuentaNacionAngel, cuentaPatagoniaAngel, cuentaGaliciaAngel);

        //lamda
        cuentas.forEach(entidadBancaria::abrirCuenta);

        //herencia y polimorfismo
        laura.esClienta();
        System.out.println(laura.getNombre() + " es clienta: " + laura.esClienta());
        System.out.println(melody.getNombre() + " es clienta: " + melody.esClienta());

        //Realizar deposito y extracción
        melody.ejecutarOperacion(deposito, cuentaNacionMelody);
        melody.ejecutarOperacion(deposito, cuentaGaliciaMelody);
        melody.ejecutarOperacion(deposito, cuentaGaliciaMelody);

        //Exception
        //melody.ejecutarOperacion(extraccion, cuentaPatagonia);
        //Visualiza la cantidad de movimientos por cuenta
        System.out.println(cuentaPatagoniaMelody.getMovimientos().size() + " operaciones");

        //Desactivar cuenta - Manejo de error - Exception Personalizado
        cuentaGaliciaMelody.desactivar();
        //melody.ejecutarOperacion(extraccion, cuentaGalicia);

        //Interfaz
        entidadBancaria.saludar();
        melody.saludar();

        //Lectura y escritura de archivos
        cuentaNacionMelody.consultarMovimiento();

        //stream saluda el usuario con nombre Melody
        entidadBancaria.saludaConNombre("Melody");

        //stream ordenados por nombre
        entidadBancaria.ordenadosAlfabeticamenteXNombre();

        //Ejemplo
        Hilo proceso = new Hilo();
        proceso.start();

        Thread proceso2 = new Thread(new HiloRunnable());
        proceso2.start();

        // Hilo 1: transfiere de cuenta1 a cuenta2
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                entidadBancaria.transferir(cuentaNacionMelody, cuentaPatagoniaMelody, 200);
            }
        }, "Cliente A");

        // Hilo 2: transfiere de cuenta2 a cuenta1
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                entidadBancaria.transferir(cuentaPatagoniaMelody, cuentaNacionMelody, 150);
            }
        }, "Cliente B");

        t1.start();
        t2.start();
    }
}