package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.forms.Fechas;
import ar.edu.unlam.tallerweb1.domain.cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.cliente.ServicioCliente;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

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

    @RequestMapping(path = "exportarClientes",method = RequestMethod.GET)
    public ResponseEntity<Resource> exportarCSVClientes(){
        String filename = "clientes.csv";
        InputStreamResource file = new InputStreamResource(servicioCliente.exportarCSV());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

}
