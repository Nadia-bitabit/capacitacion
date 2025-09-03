package Clases;

import java.util.HashSet;
import java.util.Set;

public class EntidadBancaria extends Entidad {
    private int identificadorFiscal;
    private String sucursal;
    private String email;
    private Set<Cliente> clientes = new HashSet<Cliente>();


    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void agregarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }
}
