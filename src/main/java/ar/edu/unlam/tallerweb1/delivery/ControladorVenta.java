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
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
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
    public ControladorVenta(ServicioProducto servicioProducto, ServicioVenta servicioVenta, ServicioMercadoPago servicioMercadoPago, ServicioEmpleado servicioEmpleado) {
        this.servicioProducto = servicioProducto;
        this.servicioVenta = servicioVenta;
        this.servicioMercadoPago = servicioMercadoPago;
        this.servicioEmpleado = servicioEmpleado;
    }

    @RequestMapping(path = "/goVentaForm", method = RequestMethod.GET)
    public ModelAndView irVentaForm(@ModelAttribute("datosVenta") DatosVenta venta) {
        ModelMap model = new ModelMap();
        ModelMap productos = new ModelMap();
        model.addAttribute("fecha", new Date().toString());
        model.addAttribute("productos", (List) servicioProducto.buscarProductos());
        model.addAttribute("empleados", servicioEmpleado.listarEmpleados());
        return new ModelAndView("ventaForm", model);
    }

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

        cargaDeDatos(model, venta);
        return new ModelAndView("resumenVenta", model);
    }

    @RequestMapping(path = "/getFactura",produces = "application/pdf")
    public ResponseEntity<Resource> getFactura(@RequestParam String pathUrl) {
        File factura = new File("C://Users//IvoSpruth//Documents//Personal//Taller Web I//proyecto-limpio-spring//resources//"+pathUrl);
        String filename = factura.getName();
        InputStream targetStream;
        FileInputStream fl;
        InputStreamResource fsr;
        try {
            fl = new FileInputStream(factura);
            targetStream = new ByteArrayInputStream(fl.readAllBytes());
            fsr = new InputStreamResource(targetStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(fsr);
    }

    private void cargaDeDatos(ModelMap model, Venta venta) {
        servicioVenta.fillTotal(venta);


        model.put("idEmpleado", venta.getIdEmpleado());
        model.put("comision", servicioVenta.calcularComisionEmpleado(venta.getTotal()));
        model.put("sumaTotal", venta.getTotal());
        model.addAttribute("fecha", LocalDate.now().toString());
        model.addAttribute("productos", (List) prepareProductosModel(servicioVenta.getProductos(venta)));

        model.put("idVenta", venta.getId());
        model.put("urlFactura", venta.getPathFactura());
        model.addAttribute("exito", true);
        model.addAttribute("mensaje", "La venta se cargó con éxito");

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
        modelError.addAttribute("error", mensaje);
        modelError.addAttribute("empleados",servicioEmpleado.listarEmpleados());
        return modelError;
    }

    private List<ModelMap> prepareProductosModel(List<Producto> productos) {
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
