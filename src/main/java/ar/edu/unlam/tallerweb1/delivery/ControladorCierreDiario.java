package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiarioYaEfectuadoException;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.ServicioCierreDiario;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
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
public class ControladorCierreDiario {

    private ServicioCierreDiario servicioCierre;
    private ServicioVenta servicioVenta;

    @Autowired
    public ControladorCierreDiario(ServicioCierreDiario servicioCierre, ServicioVenta servicioVenta){
        this.servicioCierre = servicioCierre;
        this.servicioVenta = servicioVenta;
    }

    @RequestMapping(path = "/goCierreDiario", method = RequestMethod.GET )
    public ModelAndView irCierreDiarioView(@ModelAttribute("cierre") CierreDiario cierre){
        ModelMap model = new ModelMap();

        model.addAttribute("fecha", LocalDate.now().toString());
        model.addAttribute("cierres", (List) servicioCierre.historialCierres());
        model.addAttribute("ventasDia", (List) servicioVenta.buscarVentasPorFecha(LocalDate.now()));
        return new ModelAndView("CierreDiario", model);
    }



    @RequestMapping(path="/ejecutarCierreDiario", method= RequestMethod.POST)
    public ModelAndView ejecutarCierre(@ModelAttribute("cierre") CierreDiario cierre, HttpServletRequest req){
        ModelMap model = new ModelMap();

        try{
            servicioCierre.ejecutarCierre();
        } catch (CierreDiarioYaEfectuadoException ece) {
            return new ModelAndView("CierreDiario", getModelError(ece.getMessage()));
        } catch (Exception e) {
            return new ModelAndView("empleado-duenio-control", getModelError("Hubo un error inesperado"));
        }

        prepareModelSuccess(model);

        return new ModelAndView("CierreDiario", model);
    }

    private void prepareModelSuccess(ModelMap model) {
        model.addAttribute("exito", true);
        model.addAttribute("mensaje","El Cierre se ejecuto con Exito!!");
        model.addAttribute("botonHabilitado", false);
        model.addAttribute("fecha", LocalDate.now().toString());
        model.addAttribute("cierres", (List) servicioCierre.historialCierres());
        model.addAttribute("ventasDia", (List) servicioVenta.buscarVentasPorFecha(LocalDate.now()));
    }

    @RequestMapping(path = "/goPDF", method = RequestMethod.GET )
    public ModelAndView goPFD(){
        ModelMap model = new ModelMap();
        return new ModelAndView("pdfView", model);
    }


    private ModelMap getModelError(String mensaje){
        ModelMap modelError = new ModelMap();
        modelError.addAttribute("fecha", LocalDate.now().toString());
        modelError.addAttribute("cierres", (List) servicioCierre.historialCierres());
        modelError.addAttribute("exito", false);
        modelError.addAttribute("mensaje", mensaje);
        modelError.addAttribute("botonHabilitado", false);
        modelError.addAttribute("ventasDia", (List) servicioVenta.buscarVentasPorFecha(LocalDate.now()));
        return modelError;
    }
}
