package ar.edu.unlam.tallerweb1.domain.envios;

import ar.edu.unlam.tallerweb1.delivery.forms.FormEnvio;
import ar.edu.unlam.tallerweb1.domain.cliente.ServicioCliente;
import ar.edu.unlam.tallerweb1.domain.envios.enums.EstadoEnvio;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    public Envio concretarEnvio(FormEnvio formEnvio) {

        Envio envio = new Envio();

        envio.setCliente(servicioCliente.buscarCliente(formEnvio.getIdCliente()));
        envio.setDireccionEnvio(servicioCliente.obtenerDireccion(formEnvio.getIdDireccion()));
        //TODO: envio.setVenta(servicioVenta.buscarVenta(formEnvio.getIdVenta()));
        envio.setFechaSalida(LocalDateTime.now());
        envio.setFechaLlegada(LocalDateTime.now().plusDays(2));
        envio.setEstadoEnvio(EstadoEnvio.EMPAQUETADO);

        repositorioEnvio.guardarEnvio(envio);

        return envio;
    }

    public void cambiarEstadoEnvio(Envio envio){
        if(envio.getEstadoEnvio() == EstadoEnvio.EMPAQUETADO){
            envio.setEstadoEnvio(EstadoEnvio.ENVIADO);
            return;
        }

        if(envio.getEstadoEnvio() == EstadoEnvio.ENVIADO){
            envio.setEstadoEnvio(EstadoEnvio.ENTREGADO);
            return;
        }
    }
}
