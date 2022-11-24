package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.utils.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorABM {

    private ServicioProducto servicioProducto;
    private ServicioEmpleado servicioEmpleado;

    @Autowired
    public ControladorABM(ServicioProducto servicioProducto, ServicioEmpleado servicioEmpleado) {
        this.servicioProducto = servicioProducto;
        this.servicioEmpleado = servicioEmpleado;
    }

    @RequestMapping(path = "/goProductoForm", method = RequestMethod.GET)
    public ModelAndView irProductoForm(@ModelAttribute("producto") Producto producto) {
        ModelMap model = new ModelMap();
        model.put("productos", servicioProducto.buscarProductos());
        return new ModelAndView("productoForm", model);
    }

    @RequestMapping(path = "/exportarProductos")
    public ResponseEntity<Resource> exportarCSVProductos() {
        String filename = "productos.csv";
        InputStreamResource file = new InputStreamResource(servicioProducto.exportarCSV());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @RequestMapping(path = "/importarProductos", method = RequestMethod.POST)
    public ModelAndView importarCSVProductos(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        ModelMap model = new ModelMap();

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                servicioProducto.importarSCV(file);
                request.getSession().setAttribute("mensaje", "Archivo subido con exito");
            } catch (Exception e) {
                request.getSession().setAttribute("mensaje", "No se pudo subir el archivo: " + file.getName() + "!");
            }
        } else {
            request.getSession().setAttribute("mensaje", "El archivo subido no es un CSV");
        }
        return new ModelAndView("redirect:goProductoForm");
    }

    @RequestMapping(path = "/addProducto", method = RequestMethod.POST)
    public ModelAndView addProducto(@ModelAttribute("producto") Producto producto, BindingResult bindingResult, HttpServletRequest req) {
        ModelMap model = new ModelMap();
        try {
            servicioProducto.addProducto(producto);

        } catch (Exception e) {
            return new ModelAndView("empleado-duenio-control", new ModelMap());
        }
        model.addAttribute("exito", false);
        model.addAttribute("mensaje", "El producto se cargo con exito");
        return new ModelAndView("empleado-duenio-control", model);
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
        model.addAttribute("mensaje", "El Empleado se cargo con exito");
        return new ModelAndView("empleado-duenio-control", model);
    }


}
