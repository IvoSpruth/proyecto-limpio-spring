package ar.edu.unlam.tallerweb1.domain.cliente;

import java.util.List;

public interface ServicioCliente {

   Cliente buscarCliente(Long id);

   Cliente buscarCliente(Cliente cliente);

   List<Cliente> traerClientesSuscriptos();

   List<Cliente> obtenerClientes();

    List<Direccion> obtenerDireccionesCliente(Long idCliente);

    Direccion obtenerDireccion(Long idDireccion);
}
