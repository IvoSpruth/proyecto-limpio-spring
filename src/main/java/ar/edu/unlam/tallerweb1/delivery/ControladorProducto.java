package ar.edu.unlam.tallerweb1.delivery;

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
public class ControladorProducto {

    private ServicioProducto servicioProducto;

    @Autowired
    public ControladorProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    @RequestMapping(path = "/goProductoForm", method = RequestMethod.GET)
    public ModelAndView irProductoForm(@ModelAttribute("producto") Producto producto, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        //coloco en el modelo el mensaje que está en la sesion seteado en importarCSVProductos()
        model.put("mensaje", request.getSession().getAttribute("mensaje"));
        //luego elimino el atributo de la sesion para dejar via libre a otro posible mensaje|
        request.getSession().removeAttribute("mensaje");
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
                request.getSession().setAttribute("mensaje", "Archivo subido con éxito");
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
        model.addAttribute("exito", true);
        model.addAttribute("mensaje", "El producto se cargó con éxito");
        return new ModelAndView("empleado-duenio-control", model);
    }
}
