package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.ofertas.ServicioOferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControladorOferta {

    private ServicioOferta servicioOferta;

    @Autowired
    public ControladorOferta(ServicioOferta servicioOferta){
        this.servicioOferta = servicioOferta;
    }
}
