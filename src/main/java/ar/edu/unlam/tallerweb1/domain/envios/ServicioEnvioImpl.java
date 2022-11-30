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

import java.util.List;

@Service("servicioEnvio")
@Transactional
public class ServicioEnvioImpl implements ServicioEnvio {

    private ServicioCliente servicioCliente;
    private ServicioVenta servicioVenta;
    private RepositorioEnvio repositorioEnvio;

    @Autowired
    public ServicioEnvioImpl(ServicioCliente servicioCliente, ServicioVenta servicioVenta, RepositorioEnvio repositorioEnvio) {
        this.servicioCliente = servicioCliente;
        this.servicioVenta = servicioVenta;
        this.repositorioEnvio = repositorioEnvio;
    }

    @Override
    public Envio concretarEnvio(FormEnvio form) {

        Cliente cliente = servicioCliente.buscarCliente(form.getIdCliente());
        Direccion direccion = servicioCliente.obtenerDireccion(form.getIdDireccion());
        Venta venta = servicioVenta.buscarVenta(form.getIdVenta());

        Envio envio = new Envio(cliente, direccion, venta);
        repositorioEnvio.guardarEnvio(envio);
        venta.agregarEnvio(envio);
        servicioVenta.actualizarVenta(venta);

        return envio;
    }

    @Override
    public void siguienteEtapaEnvio(Long id) {
        Envio envio = repositorioEnvio.obtenerEnvio(id);

        if (envio.getEstadoEnvio() == EstadoEnvio.EN_PREPARACION) {
            envio.setEstadoEnvio(EstadoEnvio.EN_CAMINO);
            repositorioEnvio.actualizarEnvio(envio);
            return;
        }

        if (envio.getEstadoEnvio() == EstadoEnvio.EN_CAMINO) {
            envio.setEstadoEnvio(EstadoEnvio.ENTREGADO);
            repositorioEnvio.actualizarEnvio(envio);
            return;
        }
    }

    @Override
    public void anteriorEtapaEnvio(Long id) {
        Envio envio = repositorioEnvio.obtenerEnvio(id);

        if (envio.getEstadoEnvio() == EstadoEnvio.ENTREGADO) {
            envio.setEstadoEnvio(EstadoEnvio.EN_CAMINO);
            repositorioEnvio.actualizarEnvio(envio);
            return;
        }

        if (envio.getEstadoEnvio() == EstadoEnvio.EN_CAMINO) {
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

        if(envio.getEstadoEnvio() == EstadoEnvio.EN_PREPARACION){
            Venta venta = envio.getVenta();
            venta.setTotal(venta.getTotal() - envio.getCosto());
            servicioVenta.actualizarVenta(venta);
            envio.setCosto(0);
        }

        envio.setEstadoEnvio(EstadoEnvio.DEVUELTO);
        repositorioEnvio.actualizarEnvio(envio);
    }

    @Override
    public Envio obtenerEnvio(Long idEnvio) {
        return repositorioEnvio.obtenerEnvio(idEnvio);
    }

    @Override
    public List<Envio> obtenerEnviosPorVentaValidos(Long idVenta) {
        return repositorioEnvio.obtenerEnviosValidos(servicioVenta.buscarVenta(idVenta));
    }

}
