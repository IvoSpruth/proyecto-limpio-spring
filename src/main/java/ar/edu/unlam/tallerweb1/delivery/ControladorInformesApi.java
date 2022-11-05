package ar.edu.unlam.tallerweb1.delivery;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/informes")
public class ControladorInformesApi {

    @RequestMapping(path = "/ventaEmpleados", method = RequestMethod.GET)
    public ResponseEntity<String> ventasPorEmpleado(){
        return new ResponseEntity<>("column", HttpStatus.OK);
    }
}
