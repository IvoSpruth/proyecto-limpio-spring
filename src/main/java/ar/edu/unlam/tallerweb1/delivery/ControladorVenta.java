package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//            servicioVenta.addVenta(venta); !!! DESCOMENTAR SI NO ESTA TESTEANDO !!!
            servicioVenta.addVenta(prepareVentaTest());

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

    private Venta prepareVentaTest() {
        Venta ventaTest = new Venta();
        ventaTest.setIdEmpleado(1);
        ventaTest.setFecha(LocalDate.now());
        Set<Producto> productos = new HashSet<>();
        Producto producto1, producto2;

        producto1 = new Producto();
        producto1.setId((long)2);
        producto1.setCantidad(3);
        producto1.setCosto(500);
        producto1.setNombre("cargador");
        producto1.setIdProveedor(1);

        producto2 = new Producto();
        producto2.setId((long)3);
        producto2.setCantidad(2);
        producto2.setCosto(1700);
        producto2.setNombre("adaptador");
        producto2.setIdProveedor(2);

        productos.add(producto1);
        productos.add(producto2);
        ventaTest.setProductos(productos);
        return ventaTest;
    }

    private void cargaDeDatos(ModelMap model, Venta venta) {
        servicioVenta.fillTotal(venta);

        model.put("idEmpleado", venta.getIdEmpleado());
        model.put("comision", servicioVenta.calcularComisionEmpleado(venta.getTotal()));
        model.put("sumaTotal", venta.getTotal());
        model.addAttribute("fecha", LocalDate.now().toString());
        model.addAttribute("exito", true);
        model.addAttribute("mensaje","La venta se cargo con exito");

//        model.addAttribute("factura",factura.getPath());
    }

    private ModelMap getModelError(String mensaje){
        ModelMap modelError = new ModelMap();
        modelError.addAttribute("fecha", new Date().toString());
        modelError.addAttribute("productos", (List) servicioProducto.buscarProductos());
        modelError.addAttribute("exito", false);
        modelError.addAttribute("message", mensaje);
        return modelError;
    }

//    public ModelMap getModelProductos(){
//        ModelMap productos = new ModelMap();
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
//    }
}
