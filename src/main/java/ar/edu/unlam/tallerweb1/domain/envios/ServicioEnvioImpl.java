package ar.edu.unlam.tallerweb1.domain.envios;

import ar.edu.unlam.tallerweb1.delivery.forms.FormEnvio;
import ar.edu.unlam.tallerweb1.domain.cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.cliente.Direccion;
import ar.edu.unlam.tallerweb1.domain.cliente.ServicioCliente;
import ar.edu.unlam.tallerweb1.domain.envios.enums.EstadoEnvio;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("servicioEnvio")
@Transactional
public class ServicioEnvioImpl implements ServicioEnvio {

    private ServicioCliente servicioCliente;
    private ServicioVenta servicioVenta;

    private RepositorioEnvio repositorioEnvio;

    @Autowired
    public ServicioEnvioImpl(ServicioCliente servicioCliente, ServicioVenta servicioVenta, RepositorioEnvio repositorioEnvio){
        this.servicioCliente = servicioCliente;
        this.servicioVenta = servicioVenta;
        this.repositorioEnvio = repositorioEnvio;
    }
    @Override
    public Envio concretarEnvio(FormEnvio form) {

        Envio envio = new Envio();

        Cliente cliente = servicioCliente.buscarCliente(form.getIdCliente());
        Direccion direccion = servicioCliente.obtenerDireccion(form.getIdDireccion());
        Venta venta = servicioVenta.buscarVenta(form.getIdVenta());

        envio.setCliente(cliente);
        envio.setDireccionEnvio(direccion);
        envio.setVenta(venta);
        envio.setCosto(venta.getTotal() * 0.2);
        envio.setFechaSalida(LocalDateTime.now());
        envio.setFechaLlegada(LocalDateTime.now().plusDays(2));
        envio.setEstadoEnvio(EstadoEnvio.EN_PREPARACION);
        repositorioEnvio.guardarEnvio(envio);

        return envio;
    }

    @Override
    public void siguienteEtapaEnvio(Long id){
        Envio envio = repositorioEnvio.obtenerEnvio(id);

        if(envio.getEstadoEnvio() == EstadoEnvio.EN_PREPARACION){
            envio.setEstadoEnvio(EstadoEnvio.EN_CAMINO);
            repositorioEnvio.actualizarEnvio(envio);
            return;
        }

        if(envio.getEstadoEnvio() == EstadoEnvio.EN_CAMINO){
            envio.setEstadoEnvio(EstadoEnvio.ENTREGADO);
            repositorioEnvio.actualizarEnvio(envio);
            return;
        }
    }

    @Override
    public void anteriorEtapaEnvio(Long id){
        Envio envio = repositorioEnvio.obtenerEnvio(id);

        if(envio.getEstadoEnvio() == EstadoEnvio.ENTREGADO){
            envio.setEstadoEnvio(EstadoEnvio.EN_CAMINO);
            repositorioEnvio.actualizarEnvio(envio);
            return;
        }

        if(envio.getEstadoEnvio() == EstadoEnvio.EN_CAMINO){
            envio.setEstadoEnvio(EstadoEnvio.EN_PREPARACION);
            repositorioEnvio.actualizarEnvio(envio);
            return;
        }

    }

    @Override
    public List<Envio> obtenerEnvios() {
        return repositorioEnvio.obtenerTodosLosEnvios();
    }

    @Override
    public void devolverEnvio(Long id) {
        Envio envio = repositorioEnvio.obtenerEnvio(id);

        envio.setEstadoEnvio(EstadoEnvio.DEVUELTO);
        repositorioEnvio.actualizarEnvio(envio);
    }

}
