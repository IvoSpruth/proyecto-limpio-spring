package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.alertas.Alerta;
import ar.edu.unlam.tallerweb1.domain.alertas.ServicioAlertas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/alertas")
public class ControladorAlertas {

    private ServicioAlertas servicioAlertas;

    @Autowired
    public ControladorAlertas(ServicioAlertas servicioAlertas){
        this.servicioAlertas = servicioAlertas;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView obtenerAlertas(){
        ModelMap modelo = new ModelMap();

        List<Alerta> alertasInventario = servicioAlertas.obtenerAlertasInventario();
        modelo.put("alertasInventario", alertasInventario);

        return new ModelAndView("alertas", modelo);
    }
}
