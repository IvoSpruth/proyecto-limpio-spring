package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.cobros.MercadoPago;
import ar.edu.unlam.tallerweb1.domain.cobros.ServicioMercadoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class ControladorMercadoPago {

    ServicioMercadoPago servicioMercadoPago;

    public ControladorMercadoPago() {
    }

    @Autowired
    public ControladorMercadoPago(ServicioMercadoPago servicioMercadoPago) {
        this.servicioMercadoPago = servicioMercadoPago;
    }

    @RequestMapping(path = "/ml", method = RequestMethod.GET)
    public ModelAndView retornoPago(@RequestParam String status, @RequestParam String preference_id, @RequestParam String external_reference, @RequestParam String payment_id) {

        MercadoPago preferencia = servicioMercadoPago.obtener(preference_id);
        ModelMap model = new ModelMap();
        if (preferencia != null) {

            if (Objects.equals(external_reference, String.valueOf(preferencia.getVenta().getId()))) {
                preferencia.setEstado(status);
                preferencia.setIdPago(payment_id);
                servicioMercadoPago.actualizarPreferencia(preferencia);
                model.put("preferencia", preferencia);
                return new ModelAndView("resultadoMercadoPago", model);
            } else {
                model.put("msg", "El ID de preferencia no coincide con el ID de venta");
                return new ModelAndView("error", model);
            }
        }
        model.put("msg", "El ID de preferencia no existe en el sistema");
        return new ModelAndView("error", model);
    }

}
