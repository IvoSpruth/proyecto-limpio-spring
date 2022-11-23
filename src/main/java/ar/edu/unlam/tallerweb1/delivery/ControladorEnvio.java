package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.forms.FormEnvio;
import ar.edu.unlam.tallerweb1.domain.cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.cliente.ServicioCliente;
import ar.edu.unlam.tallerweb1.domain.cliente.Direccion;
import ar.edu.unlam.tallerweb1.domain.envios.Envio;
import ar.edu.unlam.tallerweb1.domain.envios.ServicioEnvio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/envios")
public class ControladorEnvio {

    private ServicioCliente servicioCliente;
    private ServicioEnvio servicioEnvio;

    @Autowired
    public ControladorEnvio(ServicioCliente servicioCliente, ServicioEnvio servicioEnvio){
        this.servicioCliente = servicioCliente;
        this.servicioEnvio = servicioEnvio;
    }

    @RequestMapping(path = "/form/datosCliente", method = RequestMethod.GET)
    public ModelAndView irAFormCliente(){
        ModelMap modelo = new ModelMap();

        modelo.put("clientes", servicioCliente.obtenerClientes());
        modelo.put("form", new FormEnvio());

        return new ModelAndView("formCliente", modelo);
    }

    @RequestMapping(path = "/form/datosEnvio", method = RequestMethod.POST)
    public ModelAndView irAFormEnvio(@ModelAttribute FormEnvio form){
        ModelMap modelo = new ModelMap();

        Cliente cliente = servicioCliente.buscarCliente(form.getIdCliente());
        List<Direccion> direcciones = servicioCliente.obtenerDireccionesCliente(form.getIdCliente());

        modelo.put("cliente", cliente);
        modelo.put("direcciones", direcciones);
        modelo.put("form", form);

        return new ModelAndView("formEnvio", modelo);
    }

    @RequestMapping(path = "/procesarEnvio", method = RequestMethod.POST)
    public ModelAndView procesarInfoEnvio(@ModelAttribute FormEnvio formEnvio){
        ModelMap modelo = new ModelMap();

        Envio envio = servicioEnvio.concretarEnvio(formEnvio);
        modelo.put("envio", envio);

        return new ModelAndView("confirmacionEnvio", modelo);
    }
}
