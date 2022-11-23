package ar.edu.unlam.tallerweb1.domain.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioCliente")
@Transactional
public class ServicioClienteImpl implements ServicioCliente {

    private RepositorioCliente repositorioCliente;

    @Autowired
    public ServicioClienteImpl(RepositorioCliente repositorioCliente){
        this.repositorioCliente = repositorioCliente;
    }

    @Override
    public Cliente buscarCliente(Long id) {
        return repositorioCliente.buscarCliente(id);
    }

    @Override
    public Cliente buscarCliente(Cliente cliente) {
        return repositorioCliente.buscarCliente(cliente);
    }

    @Override
    public List<Cliente> traerClientesSuscriptos() {
        return repositorioCliente.buscarClientesSuscriptos();
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return repositorioCliente.buscarTodosLosClientes();
    }

    @Override
    public List<Direccion> obtenerDireccionesCliente(Long idCliente) {
        return repositorioCliente.obtenerDireccionCliente(idCliente);
    }

    @Override
    public Direccion obtenerDireccion(Long id) {
        return repositorioCliente.obtenerDireccion(id);
    }
}
