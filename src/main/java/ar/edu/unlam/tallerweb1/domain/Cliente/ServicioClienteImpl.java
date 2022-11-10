package ar.edu.unlam.tallerweb1.domain.Cliente;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiarioYaEfectuadoException;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.RepositorioCierreDiario;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.ServicioCierreDiario;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioClienteImpl implements ServicioCliente {

   private RepositorioCliente repositorioCliente;

    @Autowired
    public ServicioClienteImpl(RepositorioCliente repositorioCliente){
        this.repositorioCliente = repositorioCliente;
    }

    @Override
    public Cliente buscarCliente(int id) {
        return null;
    }

    @Override
    public Cliente buscarCliente(Cliente cliente) {
        return repositorioCliente.buscarCliente(cliente);
    }

    @Override
    public List<Cliente> traerClientesSuscriptos() {
        return repositorioCliente.buscarClientesSuscriptos();
    }
}
