package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.informe.ServicioInforme;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "/informes")
public class ControladorInformes {

    private ServicioInforme servicioInforme;

    private Gson gson = new Gson();

    @Autowired
    public ControladorInformes(ServicioInforme servicioInforme){
        this.servicioInforme = servicioInforme;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView irAInformes(){
        ModelMap modelo = new ModelMap();
        modelo.put("fechas", new Fechas());
        return new ModelAndView("informes", modelo);
    }

    @RequestMapping(path = "/ventasEmpleados", method = RequestMethod.POST)
    public ModelAndView informeDeVentasPorEmpleados(@ModelAttribute("fechas") Fechas fechas){
        ModelMap modelo = new ModelMap();

        List<DataChart<Double>> listaDatos = servicioInforme.obtenerVentasPorEmpleadoYPorFecha(fechas.getFechaFinal(), fechas.getFechaInicial());

        modelo.put("datos", gson.toJson(listaDatos));
        modelo.put("titulo", servicioInforme.generarTituloChart(fechas.getFechaInicial(), fechas.getFechaFinal()));

        return new ModelAndView("informeVentasEmpleados", modelo);
    }
}
