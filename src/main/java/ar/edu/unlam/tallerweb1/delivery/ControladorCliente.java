package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.forms.Fechas;
import ar.edu.unlam.tallerweb1.domain.cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.cliente.Direccion;
import ar.edu.unlam.tallerweb1.domain.cliente.ServicioCliente;
import ar.edu.unlam.tallerweb1.domain.utils.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "/clientes")
public class ControladorCliente {

    private ServicioCliente servicioCliente;

    @Autowired
    public ControladorCliente(ServicioCliente servicioCliente) {
        this.servicioCliente = servicioCliente;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView listaClientes() {
        ModelMap modelo = new ModelMap();
        modelo.put("cliente", new Cliente());
        modelo.put("clientes", servicioCliente.obtenerClientes());
        return new ModelAndView("clientes", modelo);
    }

    @RequestMapping(path = "/addCliente", method = RequestMethod.POST)
    public ModelAndView addCliente(HttpServletRequest request, @ModelAttribute("cliente") Cliente cliente) {

        cliente.setFechaIngreso(LocalDate.now());
        servicioCliente.crearCliente(cliente);
        request.getSession().setAttribute("mensaje", "El cliente se genero con exito");
        return new ModelAndView("redirect:/clientes");
    }

    @RequestMapping(path = "activarNotif", method = RequestMethod.GET)
    public ModelAndView activarNotif(HttpServletRequest request, Long id) {
        Cliente cliente = servicioCliente.obtenerCliente(id);
        cliente.setNotifEnable(true);
        servicioCliente.actualizarCliente(cliente);
        request.getSession().setAttribute("mensaje", "Se activaron las noticaciones para el cliente " + cliente.getNombre());
        return new ModelAndView("redirect:/clientes");
    }

    @RequestMapping(path = "desactivarNotif", method = RequestMethod.GET)
    public ModelAndView desactivarNotif(HttpServletRequest request, Long id) {
        Cliente cliente = servicioCliente.obtenerCliente(id);
        cliente.setNotifEnable(false);
        servicioCliente.actualizarCliente(cliente);
        request.getSession().setAttribute("mensaje", "Se desactivaron las noticaciones para el cliente " + cliente.getNombre());
        return new ModelAndView("redirect:/clientes");
    }

    @RequestMapping(path = "exportarClientes", method = RequestMethod.GET)
    public ResponseEntity<Resource> exportarCSVClientes() {
        String filename = "clientes.csv";
        InputStreamResource file = new InputStreamResource(servicioCliente.exportarCSV());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @RequestMapping(path = "/importarClientes", method = RequestMethod.POST)
    public ModelAndView importarCSVClientes(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

        ModelMap model = new ModelMap();

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                servicioCliente.importarSCV(file);
                request.getSession().setAttribute("mensaje", "Archivo subido con éxito");
            } catch (Exception e) {
                request.getSession().setAttribute("mensaje", "No se pudo subir el archivo: " + file.getName() + "!");
            }
        } else {
            request.getSession().setAttribute("mensaje", "El archivo subido no es un CSV");
        }
        return new ModelAndView("redirect:/clientes");
    }

    @RequestMapping(path = "/direcciones", method = RequestMethod.GET)
    public ModelAndView mostrarDirecciones(HttpServletRequest request, Long id) {
        Cliente cliente = servicioCliente.buscarCliente(id);

        if (cliente == null) {
            request.getSession().setAttribute("error", "No existe un cliente con ese ID");
            return new ModelAndView("redirect:/clientes");
        }
        ModelMap modelo = new ModelMap();
        modelo.put("direccion", new Direccion());
        modelo.put("cliente", cliente);
        modelo.put("direcciones", servicioCliente.obtenerDireccionesCliente(id));
        return new ModelAndView("direcciones", modelo);
    }

    @RequestMapping(path = "/direcciones/addDireccion", method = RequestMethod.POST)
    public ModelAndView addDireccion(HttpServletRequest request, @ModelAttribute("cliente") Direccion direccion, Long id) {
        Cliente cliente = servicioCliente.buscarCliente(id);

        if (cliente == null) {
            request.getSession().setAttribute("error", "No existe un cliente con ese ID");
            return new ModelAndView("redirect:/clientes/direcciones?id=" + id);
        }

        List<Direccion> direcciones = cliente.getDirecciones();

        direcciones.add(direccion);

        cliente.setDirecciones(direcciones);

        servicioCliente.actualizarCliente(cliente);

        return new ModelAndView("redirect:/clientes/direcciones?id=" + id);
    }

}
