package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.forms.FormEnvio;
import ar.edu.unlam.tallerweb1.domain.cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.cliente.ServicioCliente;
import ar.edu.unlam.tallerweb1.domain.envios.Envio;
import ar.edu.unlam.tallerweb1.domain.envios.ServicioEnvio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView irAFormCliente(@RequestParam("idVenta") Long idVenta){
        ModelMap modelo = new ModelMap();

        FormEnvio form = new FormEnvio();
        form.setIdVenta(idVenta);

        modelo.put("clientes", servicioCliente.obtenerClientes());
        modelo.put("form", form);

        return new ModelAndView("envioFormCliente", modelo);
    }

    @RequestMapping(path = "/form/datosEnvio", method = RequestMethod.POST)
    public ModelAndView irAFormEnvio(@ModelAttribute FormEnvio form){
        ModelMap modelo = new ModelMap();

        Cliente cliente = servicioCliente.buscarCliente(form.getIdCliente());

        modelo.put("cliente", cliente);
        modelo.put("direcciones", cliente.getDirecciones());
        modelo.put("form", form);

        return new ModelAndView("envioFormEnvio", modelo);
    }

    @RequestMapping(path = "/procesarEnvio", method = RequestMethod.POST)
    public ModelAndView procesarInfoEnvio(@ModelAttribute FormEnvio form){
        ModelMap modelo = new ModelMap();

        Envio envio = servicioEnvio.concretarEnvio(form);
        modelo.put("envio", envio);

        return new ModelAndView("envioConfirmacion", modelo);
    }

    @RequestMapping(path = "/siguienteEtapa", method = RequestMethod.GET)
    public ModelAndView pasarASiguienteEtapa(@RequestParam("idEnvio") Long idEnvio){

        servicioEnvio.siguienteEtapaEnvio(idEnvio);

        return new ModelAndView("redirect:/envios/mostrar");
    }

    @RequestMapping(path = "/anteriorEtapa", method = RequestMethod.GET)
    public ModelAndView pasarAnteriorEtapa(@RequestParam("idEnvio") Long idEnvio){

        servicioEnvio.anteriorEtapaEnvio(idEnvio);

        return new ModelAndView("redirect:/envios/mostrar");
    }

    @RequestMapping(path = "/devolver", method = RequestMethod.GET)
    public ModelAndView devolverEnvio(@RequestParam("idEnvio") Long idEnvio){

        servicioEnvio.devolverEnvio(idEnvio);

        return new ModelAndView("redirect:/envios/mostrar");
    }

    @RequestMapping(path = "/mostrar", method = RequestMethod.GET)
    public ModelAndView mostrarEnvios(){
        ModelMap modelo = new ModelMap();

        List<Envio> envios = servicioEnvio.obtenerEnvios();

        modelo.put("envios", envios);

        return new ModelAndView("envioMostrarEnvios", modelo);
    }
}
