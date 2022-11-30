package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.forms.DatosVenta;
import ar.edu.unlam.tallerweb1.domain.cobros.MercadoPago;
import ar.edu.unlam.tallerweb1.domain.cobros.MercadoPagoCredenciales;
import ar.edu.unlam.tallerweb1.domain.cobros.ServicioMercadoPago;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.ventas.CantidadInsuficienteException;
import ar.edu.unlam.tallerweb1.domain.ventas.IdEmpleadoNoValidoException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorVenta {

    private ServicioVenta servicioVenta;
    private ServicioProducto servicioProducto;

    private ServicioEmpleado servicioEmpleado;

    private ServicioMercadoPago servicioMercadoPago;

    @Autowired
    public ControladorVenta(ServicioProducto servicioProducto, ServicioVenta servicioVenta, ServicioMercadoPago servicioMercadoPago) {
        this.servicioProducto = servicioProducto;
        this.servicioVenta = servicioVenta;
        this.servicioMercadoPago = servicioMercadoPago;
    }

    @RequestMapping(path = "/goVentaForm", method = RequestMethod.GET)
    public ModelAndView irVentaForm(@ModelAttribute("datosVenta") DatosVenta venta) {
        ModelMap model = new ModelMap();
        ModelMap productos = new ModelMap();
        model.addAttribute("fecha", new Date().toString());
        model.addAttribute("productos", (List) servicioProducto.buscarProductos());
        return new ModelAndView("ventaForm", model);
    }


/*    @RequestMapping(path = "/goResumen", method = RequestMethod.GET )
    public ModelAndView irAResumen(@ModelAttribute("venta") Venta venta ) {
        ModelMap model = new ModelMap();

        return new ModelAndView("resumenVenta", model);
    }*/

    @RequestMapping(path = "/addVenta", method = RequestMethod.POST)
    public ModelAndView addVenta(@ModelAttribute("datosVenta") DatosVenta datosVenta, HttpServletRequest req) {
        ModelMap model = new ModelMap();
        File factura;
        Venta venta;
        try {
            venta = datosVenta.toVenta();
            servicioVenta.addVenta(venta);
        } catch (CantidadInsuficienteException cie) {
            return new ModelAndView("ventaForm", getModelError(cie.getMessage()));
        } catch (IdEmpleadoNoValidoException ienve) {
            return new ModelAndView("ventaForm", getModelError(ienve.getMessage()));
        } catch (Exception e) {
            return new ModelAndView("ventaForm", getModelError("Hubo un error inesperado" + e.getMessage()));
        }

        //String nombreEmpleado = servicioVenta.buscarNombreEmpleado(venta.getIdEmpleado());
        //model.put("nombreEmpleado", nombreEmpleado);
        //String nombreEmpleado = servicioVenta.buscarNombreDeEmpleadoPorId(venta.getIdEmpleado());

        cargaDeDatos(model, venta);
        return new ModelAndView("resumenVenta", model);
    }

    private void cargaDeDatos(ModelMap model, Venta venta) {
        servicioVenta.fillTotal(venta);


        model.put("idEmpleado", venta.getIdEmpleado());
        model.put("comision", servicioVenta.calcularComisionEmpleado(venta.getTotal()));
        model.put("sumaTotal", venta.getTotal());
        model.addAttribute("fecha", LocalDate.now().toString());
        model.addAttribute("productos", (List) prepareProductosModel(servicioVenta.getProductos(venta)));

        //venta.setId(1L);
        model.put("idVenta", venta.getId()); //id de venta para envio
        model.addAttribute("exito", true);
        model.addAttribute("mensaje", "La venta se cargo con exito");

        //Link de pago
        MercadoPago link = servicioMercadoPago.obtener(venta);
        if (link != null) {
            model.put("preferenciaID", link.getId_preferencia());
            model.put("linkDePago", link.getLinkDePago());
            model.put("PUBLIC_ACCESS_TOKEN", MercadoPagoCredenciales.PUBLIC_ACCESS_TOKEN);
        }

        //model.addAttribute("factura",factura.getPath());
    }

    private ModelMap getModelError(String mensaje) {
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

    private List<ModelMap> prepareProductosModel(List<Producto> productos) {
        ModelMap prods = new ModelMap();
        ArrayList<ModelMap> pp = new ArrayList<>();

        for (Producto p : productos) {
            ModelMap producto = new ModelMap();
            producto.addAttribute("nombre", p.getNombre());
            producto.addAttribute("precio", p.getCosto());
            producto.addAttribute("id", p.getId());
            producto.addAttribute("cantidad", p.getCantidad());
            producto.addAttribute("totalProducto", p.getCosto() * p.getCantidad());
            producto.addAttribute("descuento", false);
            pp.add(producto);
        }

        return pp;
    }
}
