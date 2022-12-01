package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorEmpleado {

    private ServicioEmpleado servicioEmpleado;

    @Autowired
    public ControladorEmpleado(ServicioEmpleado servicioEmpleado) {
        this.servicioEmpleado = servicioEmpleado;
    }

    @RequestMapping(path = "/goEmpleadoForm", method = RequestMethod.GET)
    public ModelAndView irEmpleadoForm(@ModelAttribute("empleado") Empleado empleado) {
        return new ModelAndView("EmpleadoForm");
    }


    @RequestMapping(path = "/addEmpleado", method = RequestMethod.POST)
    public ModelAndView addProducto(@ModelAttribute("empleado") Empleado empleado, BindingResult bindingResult, HttpServletRequest req) {
        ModelMap model = new ModelMap();
        try {
            servicioEmpleado.addEmpleado(empleado);

        } catch (Exception e) {
            return new ModelAndView("empleado-duenio-control", new ModelMap());
        }
        model.addAttribute("exito", false);
        model.addAttribute("mensaje", "El empleado se cargó con éxito");
        return new ModelAndView("empleado-duenio-control", model);
    }

}
