import Clases.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Persona laura = new Persona("Laura", "Perez");
        Cuenta cuentaNacion = new Cuenta();
        Operacion operacion = new Operacion();
        Operacion deposito = new Deposito();
        Operacion extraccion = new Extraccion();
        Operacion movimiento = new Movimiento();
        Cliente melody = new Cliente("Melody", "Gonzalez");

        //Entidad Bancaria
        EntidadBancaria entidadBancaria = new EntidadBancaria();
        entidadBancaria.agregarCliente(melody);

        //herencia y polimorfismo
        laura.esClienta();
        System.out.println(laura.getNombre() + " es clienta: " + laura.esClienta());
        System.out.println(melody.getNombre() + " es clienta: " + melody.esClienta());

        //Realizar movimiento
        melody.ejecutarOperacion(movimiento);
        //Realizar deposito
        melody.ejecutarOperacion(deposito);
        //Realizar extracción
        melody.ejecutarOperacion(extraccion);
        System.out.println(melody.getOperaciones().size() + " operaciones");
        //

    }
}