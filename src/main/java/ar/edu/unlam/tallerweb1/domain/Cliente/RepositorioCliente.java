package ar.edu.unlam.tallerweb1.domain.cliente;

import java.util.List;

public interface RepositorioCliente {

   Cliente buscarCliente(Cliente cliente);

   List<Cliente> buscarClientesSuscriptos();

   List<Cliente> buscarTodosLosClientes();

   Cliente buscarCliente(Long id);

   Direccion obtenerDireccion(Long id);

   List<Direccion> obtenerDireccionCliente(Long idCliente);
}
