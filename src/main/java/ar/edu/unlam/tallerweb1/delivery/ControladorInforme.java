package ar.edu.unlam.tallerweb1.delivery;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/informes")
public class ControladorInforme {

     @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView irAInformes(){
         ModelMap modelo = new ModelMap();
         return new ModelAndView("informes", modelo);
    }
}
