package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.alertas.ServicioAlertas;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorAlertas {

    private Gson gson = new Gson();
    private ServicioAlertas servicioAlertas;

    @Autowired
    public ControladorAlertas(ServicioAlertas servicioAlertas){
        this.servicioAlertas = servicioAlertas;
    }

    @GetMapping("/alertas")
    public String obtenerAlertas(){

        String alertasInventario = servicioAlertas.obtenerAlertasInventario();

        return alertasInventario;
    }
}
