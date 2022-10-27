package ar.edu.unlam.tallerweb1.domain.Cliente;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioCliente {

   Cliente buscarCliente(Cliente cliente);

   List<Cliente> buscarClientesSuscriptos();

}
