package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiarioYaEfectuadoException;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.ServicioCierreDiario;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorCierreDiario {

    private ServicioCierreDiario servicioCierre;

    @Autowired
    public ControladorCierreDiario(ServicioCierreDiario servicioCierre){
        this.servicioCierre = servicioCierre;
    }

    @RequestMapping(path = "/goCierreDiario", method = RequestMethod.GET )
    public ModelAndView irCierreDiarioView(){
        ModelMap model = new ModelMap();

        model.addAttribute("fecha", LocalDate.now().toString());
        model.addAttribute("cierres", (List) servicioCierre.historialCierres());
        return new ModelAndView("CierreDiario", model);
    }

    @RequestMapping(path="/ejecutarCierreDiario", method= RequestMethod.POST)
    public ModelAndView ejecutarCierre(HttpServletRequest req){
        ModelMap model = new ModelMap();

        try{
            servicioCierre.ejecutarCierre();
        } catch (CierreDiarioYaEfectuadoException ece) {
            return new ModelAndView("CierreDiarioForm", getModelError(ece.getMessage()));
        } catch (Exception e) {
            return new ModelAndView("empleado-dueño-control", getModelError("Hubo un error inesperado"));
        }


        model.addAttribute("exito", true);
        model.addAttribute("mensaje","El Cierre se ejecuto con Exito!!");
        model.addAttribute("botonHabilitado", true);

        return new ModelAndView("CierreDiario", model);
    }



    private ModelMap getModelError(String mensaje){
        ModelMap modelError = new ModelMap();
        modelError.addAttribute("fecha", new Date().toString());
        modelError.addAttribute("cierres", (List) servicioCierre.historialCierres());
        modelError.addAttribute("exito", false);
        modelError.addAttribute("mensaje", mensaje);
        modelError.addAttribute("botonHabilitado", false);
        return modelError;
    }
}
