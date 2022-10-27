package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.utils.PdfManager;
import ar.edu.unlam.tallerweb1.domain.ventas.CantidadInsuficienteException;
import ar.edu.unlam.tallerweb1.domain.ventas.IdEmpleadoNoValidoException;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import com.itextpdf.text.DocumentException;
import ar.edu.unlam.tallerweb1.domain.ventas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorVenta {

    private ServicioVenta servicioVenta;
    private ServicioProducto servicioProducto;

    private ServicioEmpleado servicioEmpleado;

    @Autowired
    public ControladorVenta(ServicioProducto servicioProducto, ServicioVenta servicioVenta){
        this.servicioProducto = servicioProducto;
        this.servicioVenta = servicioVenta;
    }

    @RequestMapping(path = "/goVentaForm", method = RequestMethod.GET )
    public ModelAndView irVentaForm(@ModelAttribute("venta") Venta venta ){
        ModelMap model = new ModelMap();
        ModelMap productos = new ModelMap();

        //productos = servicioProducto.buscarProductos();
        /*for(Producto p : servicioProducto.buscarProductos()){
            productos.addAttribute(p.getId().toString(),p.getNombre()+"|"+p.getCantidad()+"|"+p.getCosto());
        }*/

        model.addAttribute("fecha", new Date().toString());
        model.addAttribute("productos", (List) servicioProducto.buscarProductos());
        return new ModelAndView("ventaForm", model);
    }


/*    @RequestMapping(path = "/goResumen", method = RequestMethod.GET )
    public ModelAndView irAResumen(@ModelAttribute("venta") Venta venta ) {
        ModelMap model = new ModelMap();

        return new ModelAndView("resumenVenta", model);
    }*/

    @RequestMapping(path="/addVenta", method= RequestMethod.POST)
    public ModelAndView addVenta(@ModelAttribute("venta") Venta venta, HttpServletRequest req){
        ModelMap model = new ModelMap();
        File factura;
        factura = servicioVenta.createFactura(venta);
        try {

            servicioVenta.addVenta(venta);

        } catch (CantidadInsuficienteException cie) {
            return new ModelAndView("ventaForm", getModelError(cie.getMessage()));
        } catch (IdEmpleadoNoValidoException ienve){
            return new ModelAndView("ventaForm", getModelError(ienve.getMessage()));
        }
        catch (Exception e) {
            return new ModelAndView("empleado-dueño-control", getModelError("Hubo un error inesperado"));
        }


        //String nombreEmpleado = servicioVenta.buscarNombreEmpleado(venta.getIdEmpleado());
        //model.put("nombreEmpleado", nombreEmpleado);
        //String nombreEmpleado = servicioVenta.buscarNombreDeEmpleadoPorId(venta.getIdEmpleado());

        cargaDeDatos(model, venta);
        //model.addAttribute("factura",factura.getAbsolutePath());

        return new ModelAndView("resumenVenta", model);
    }

    private void cargaDeDatos(ModelMap model, Venta venta) {

        model.put("idEmpleado", venta.getIdEmpleado());
        //model.put("nombreEmpleado", nombreEmpleado);

        String nombreProductoUno = servicioVenta.buscarNombreProducto(venta.getIdProducto());
        double costoProductoUno = servicioVenta.buscarCostoProducto(venta.getIdProducto());
        servicioVenta.fillTotal(venta);
        double totalProductoUno = servicioVenta.getSubtotalProducto1();
        double sumaTotal = servicioVenta.getSubtotalProductos();

        model.put("nombreProductoUno", nombreProductoUno);
        model.put("precioUnitarioUno", costoProductoUno);
        model.put("idProductoUno", venta.getIdProducto());
        model.put("cantidadUno", venta.getCantidadProducto());
        model.put("totalProductoUno", totalProductoUno);
        // sumaTotal deberia traer el total en el momento de ejecutarlo
        // pero esta trayendo la suma total de todos los productos
        model.put("totalProductoUno", totalProductoUno);

        double comision = servicioVenta.calcularComisionEmpleado(sumaTotal);
        model.put("comision", comision);

        String nombreProductoDos = servicioVenta.buscarNombreProducto(venta.getIdProducto2());
        double costoProductoDos = servicioVenta.buscarCostoProducto(venta.getIdProducto2());
        servicioVenta.fillTotal(venta);
        double totalProductoDos = servicioVenta.getSubtotalProducto2();

        model.put("nombreProductoDos", nombreProductoDos);
        model.put("precioUnitarioDos", costoProductoDos);
        model.put("idProductoDos", venta.getIdProducto2());
        model.put("cantidadDos", venta.getCantidadProducto2());
        model.put("totalProductoDos", totalProductoDos);
        model.put("sumaTotal", sumaTotal);
        model.addAttribute("fecha", new Date().toString());
        model.addAttribute("exito", true);
        model.addAttribute("mensaje","La venta se cargo con exito");

        //model.addAttribute("factura",factura.getPath());



    }

    private ModelMap getModelError(String mensaje){
        ModelMap modelError = new ModelMap();
        modelError.addAttribute("fecha", new Date().toString());
        modelError.addAttribute("productos", (List) servicioProducto.buscarProductos());
        modelError.addAttribute("exito", false);
        modelError.addAttribute("message", mensaje);
        return modelError;
    }
}
