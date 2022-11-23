package ar.edu.unlam.tallerweb1.domain.envios;

import ar.edu.unlam.tallerweb1.delivery.forms.FormEnvio;
import ar.edu.unlam.tallerweb1.domain.cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.cliente.Direccion;
import ar.edu.unlam.tallerweb1.domain.cliente.ServicioCliente;
import ar.edu.unlam.tallerweb1.domain.envios.enums.EstadoEnvio;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
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
        //Venta venta = servicioVenta.obtenerVenta(form.getIdVenta());

        envio.setCliente(cliente);
        envio.setDireccionEnvio(direccion);
        //TODO: envio.setVenta(venta);
        envio.setFechaSalida(LocalDateTime.now());
        envio.setFechaLlegada(LocalDateTime.now().plusDays(2));
        envio.setEstadoEnvio(EstadoEnvio.EMPAQUETADO);

        Envio e = repositorioEnvio.guardarEnvio(envio);

        return envio;
    }

    @Override
    public void cambiarEstadoEnvio(Long id){
        Envio envio = repositorioEnvio.obtenerEnvio(id);

        if(envio.getEstadoEnvio() == EstadoEnvio.EMPAQUETADO){
            envio.setEstadoEnvio(EstadoEnvio.ENVIADO);
            repositorioEnvio.actualizarEnvio(envio);
            return;
        }

        if(envio.getEstadoEnvio() == EstadoEnvio.ENVIADO){
            envio.setEstadoEnvio(EstadoEnvio.ENTREGADO);
            repositorioEnvio.actualizarEnvio(envio);
            return;
        }
    }

    @Override
    public List<Envio> obtenerEnvios() {
        return repositorioEnvio.obtenerTodosLosEnvios();
    }

}
