package Clases;

import java.util.HashSet;
import java.util.Set;

public class EntidadBancaria extends Entidad {
    private Set<Cliente> clientes = new HashSet<Cliente>();

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void agregarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }
}
