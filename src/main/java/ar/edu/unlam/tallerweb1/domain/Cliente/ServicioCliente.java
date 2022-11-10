package ar.edu.unlam.tallerweb1.domain.Cliente;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiarioYaEfectuadoException;

import java.util.List;

public interface ServicioCliente {

   Cliente buscarCliente(int id);

   Cliente buscarCliente(Cliente cliente);

   List<Cliente> traerClientesSuscriptos();
}
