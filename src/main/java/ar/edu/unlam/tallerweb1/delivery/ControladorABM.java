package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class ControladorABM {

    private ServicioProducto servicioProducto;
    private ServicioEmpleado servicioEmpleado;

    private ServicioVenta servicioVenta;

    @Autowired
    public ControladorABM(ServicioProducto servicioProducto, ServicioEmpleado servicioEmpleado, ServicioVenta servicioVenta){
        this.servicioProducto = servicioProducto;
        this.servicioEmpleado = servicioEmpleado;
        this.servicioVenta = servicioVenta;
    }

    @RequestMapping(path = "/goProductoForm", method = RequestMethod.GET)
    public ModelAndView irProductoForm(@ModelAttribute("producto") Producto producto) {
        return new ModelAndView("productoForm");
    }


    @RequestMapping(path="/addProducto", method= RequestMethod.POST)
    public ModelAndView addProducto(@ModelAttribute("producto") Producto producto, BindingResult bindingResult, HttpServletRequest req){
        ModelMap model = new ModelMap();
        try {
            servicioProducto.addProducto(producto);

        } catch (Exception e) {
            return new ModelAndView("empleado-dueño-control", new ModelMap());
        }
        model.addAttribute("exito", false);
        model.addAttribute("mensaje","El producto se cargo con exito");
        return new ModelAndView("empleado-dueño-control", model);
    }

    @RequestMapping(path = "/goEmpleadoForm", method = RequestMethod.GET)
    public ModelAndView irEmpleadoForm(@ModelAttribute("empleado") Empleado empleado) {
        return new ModelAndView("EmpleadoForm");
    }


    @RequestMapping(path="/addEmpleado", method= RequestMethod.POST)
    public ModelAndView addProducto(@ModelAttribute("empleado") Empleado empleado, BindingResult bindingResult, HttpServletRequest req){
        ModelMap model = new ModelMap();
        try {
            servicioEmpleado.addEmpleado(empleado);

        } catch (Exception e) {
            return new ModelAndView("empleado-dueño-control", new ModelMap());
        }
        model.addAttribute("exito", false);
        model.addAttribute("mensaje","El Empleado se cargo con exito");
        return new ModelAndView("empleado-dueño-control", model);
    }




    /*@RequestMapping(path="/addEmpleado", method= RequestMethod.POST)
    public ModelAndView addEmpleado(@ModelAttribute("datosEmpleado") Empleado empleado, HttpServletRequest req){

    }*/

    @RequestMapping(path = "/goVentaForm", method = RequestMethod.GET )
    public ModelAndView irVentaForm(@ModelAttribute("venta") Venta venta ){
        ModelMap model = new ModelMap();
        ModelMap productos;

        productos = (ModelMap) servicioProducto.buscarProductos();

        model.addAttribute("fecha", new Date().toString());
        model.addAttribute("productos", productos);
        return new ModelAndView("ventaForm", model);
    }

    @RequestMapping(path="/addVenta", method= RequestMethod.POST)
    public ModelAndView addVenta(@ModelAttribute("venta") Venta venta, HttpServletRequest req){
        ModelMap model = new ModelMap();
        try {
            servicioVenta.addVenta(venta);

        } catch (Exception e) {
            return new ModelAndView("empleado-dueño-control", new ModelMap());
        }
        model.addAttribute("exito", false);
        model.addAttribute("mensaje","La venta se cargo con exito");
        return new ModelAndView("empleado-dueño-control", model);
    }





}
