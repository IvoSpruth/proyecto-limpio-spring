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
import java.util.*;

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
        model.addAttribute("fecha", new Date().toString());
        model.addAttribute("productos", (List) servicioProducto.buscarProductos());
        return new ModelAndView("ventaForm", model);
    }

    @RequestMapping(path="/addVenta", method= RequestMethod.POST)    public ModelAndView addVenta(@ModelAttribute("venta") Venta venta, HttpServletRequest req){
        ModelMap model = new ModelMap();
        File factura;
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
//        cargaDeDatos(model, venta);
        cargaDeDatos(model, prepareVentaTest());


        return new ModelAndView("resumenVenta", model);
    }

    private Venta prepareVentaTest() {
        Venta ventaTest = new Venta();
        ventaTest.setIdEmpleado(1);
        ventaTest.setFecha(LocalDate.now());
        Set<Producto> productos = new HashSet<>();
        Producto producto1, producto2, producto3, producto4, producto5;

        producto1 = new Producto();
        producto1.setId((long)2);
        producto1.setCantidad(3);
        producto1.setCosto(500);
        producto1.setNombre("lavarropas");
        producto1.setIdProveedor(1);

        producto2 = new Producto();
        producto2.setId((long)3);
        producto2.setCantidad(2);
        producto2.setCosto(1700);
        producto2.setNombre("notebook");
        producto2.setIdProveedor(2);

        producto3 = new Producto();
        producto3.setNombre("auriculares");
        producto3.setId((long)4);
        producto3.setCantidad(2);
        producto3.setCosto(8855);
        producto3.setIdProveedor(2);

        producto4 = new Producto();
        producto4.setNombre("regla");
        producto4.setId((long)5);
        producto4.setCantidad(2);
        producto4.setCosto(4400);
        producto4.setIdProveedor(2);

        producto5 = new Producto();
        producto5.setNombre("televisor");
        producto5.setId((long)6);
        producto5.setCantidad(2);
        producto5.setCosto(5566);
        producto5.setIdProveedor(2);

        ventaTest.addProducto(producto1);
        ventaTest.addProducto(producto2);
        ventaTest.addProducto(producto3);
        ventaTest.addProducto(producto4);
        ventaTest.addProducto(producto5);

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
        model.addAttribute("productos", (List)prepareProductosModel(venta.getProductos()));

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

    private List<ModelMap> prepareProductosModel(List<Producto> productos){
        ModelMap prods = new ModelMap();
        ArrayList<ModelMap> pp = new ArrayList<>();

        for(Producto p: productos){
            ModelMap producto = new ModelMap();
            producto.addAttribute("nombre", p.getNombre());
            producto.addAttribute("precio", p.getCosto());
            producto.addAttribute("id", p.getId());
            producto.addAttribute("cantidad", p.getCantidad());
            producto.addAttribute("totalProducto", p.getCosto()*p.getCantidad());
            producto.addAttribute("descuento", false);
            pp.add(producto);
        }

        return pp;
    }
}
