package ar.edu.unlam.tallerweb1.domain.cliente;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface ServicioCliente {

   Cliente buscarCliente(Long id);

   Cliente buscarCliente(Cliente cliente);

   List<Cliente> traerClientesSuscriptos();

   List<Cliente> obtenerClientes();

    List<Direccion> obtenerDireccionesCliente(Long idCliente);

    Direccion obtenerDireccion(Long idDireccion);

    void crearCliente(Cliente cliente);

    Cliente obtenerCliente(Long id);

    void actualizarCliente(Cliente cliente);

    InputStream exportarCSV();

    @Transactional
    void importarSCV(MultipartFile file);
}
