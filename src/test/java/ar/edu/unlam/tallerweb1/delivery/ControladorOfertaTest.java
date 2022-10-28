package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.ofertas.ServicioOferta;
import ar.edu.unlam.tallerweb1.domain.ventas.CantidadInsuficienteException;
import ar.edu.unlam.tallerweb1.domain.ventas.IdEmpleadoNoValidoException;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorOfertaTest extends SpringTest {

    private ServicioOferta servicioOferta;

    private HttpServletRequest request;





    @Before
    public void init(){
       this.servicioOferta = mock(ServicioOferta.class);
    }



    /*@Test
    public void alEnviarNotificacionesSaleExitoso() throws CantidadInsuficienteException, IdEmpleadoNoValidoException{

        ModelAndView mav = cuandoEnvioNotificaciones();
        entoncesEncuentro(mav);
        entoncesMeLLevaALaVista(mav, "resumenVenta");

    }*/


}
