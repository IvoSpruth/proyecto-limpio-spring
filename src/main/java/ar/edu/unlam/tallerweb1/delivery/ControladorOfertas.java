package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.ofertas.Oferta;
import ar.edu.unlam.tallerweb1.domain.ofertas.ServicioOferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ControladorOfertas {

    private ServicioOferta servicioOferta;

    @Autowired
    public ControladorOfertas(ServicioOferta servicioOferta){
        this.servicioOferta = servicioOferta;
    }

    @RequestMapping(path = "/goOfertas", method = RequestMethod.GET )
    public ModelAndView irCierreDiarioView(@ModelAttribute("cierre") CierreDiario cierre){
        ModelMap model = new ModelMap();

        model.addAttribute("fecha", LocalDate.now().toString());
        model.addAttribute("ofertasCargadas", (List<Oferta>) servicioOferta.traerOfertasCargadas());

        return new ModelAndView("ofertas", model);
    }

    @RequestMapping(path="/enviarNotificaciones", method= RequestMethod.POST)
    public ModelAndView ejecutarCierre(@ModelAttribute("cierre") CierreDiario cierre, HttpServletRequest req){
        ModelMap model = new ModelMap();

        try {
            servicioOferta.enviarNotificaciones();
        } catch (Exception e) {
            return new ModelAndView("empleado-duenio-control", getModelError("Hubo un error inesperado"));
        }

        prepareModelSuccess(model);

        return new ModelAndView("ofertas", model);
    }

    private void prepareModelSuccess(ModelMap model) {
        model.addAttribute("exito", true);
        model.addAttribute("mensaje","Las notificaciones fueron enviadas con exito");
        model.addAttribute("fecha", LocalDate.now().toString());
        model.addAttribute("ofertasCargadas", (List<Oferta>) servicioOferta.traerOfertasCargadas());
    }


    private ModelMap getModelError(String mensaje){
        ModelMap modelError = new ModelMap();
        modelError.addAttribute("fecha", LocalDate.now().toString());

        modelError.addAttribute("exito", false);
        modelError.addAttribute("mensaje", mensaje);
        modelError.addAttribute("ofertasCargadas", (List<Oferta>) servicioOferta.traerOfertasCargadas());

        return modelError;
    }
}
