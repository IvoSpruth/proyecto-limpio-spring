package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiarioYaEfectuadoException;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.ServicioCierreDiario;
import ar.edu.unlam.tallerweb1.domain.cobros.ServicioMercadoPago;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorCierreTest extends SpringTest {

    private ServicioCierreDiario servicioCierre;
    private ControladorCierreDiario controladorCierre;
    private ServicioVenta servicioVenta;
    private ServicioMercadoPago servicioMercadoPago;

    private HttpServletRequest request;


    @Before
    public void init() {
        this.request = mock(HttpServletRequest.class);
        this.servicioCierre = mock(ServicioCierreDiario.class);
        this.servicioVenta = mock(ServicioVenta.class);
        this.servicioMercadoPago = mock(ServicioMercadoPago.class);
        this.controladorCierre = new ControladorCierreDiario(servicioCierre, servicioVenta, servicioMercadoPago);
    }

    @Test
    public void alEjecutarUnCierrePorPrimeraVezObtengoMavCorrecto() throws CierreDiarioYaEfectuadoException {
        dadoUnCierreExitoso();
        ModelAndView mav = cuandoEjecutoElCierre();
        entoncesEncuentro(mav);
        entoncesMeLLevaALaVista(mav, "CierreDiario");

    }

    @Test
    public void alEjecutarUnCierreYaCerradoObtengoMavCorrecto() throws CierreDiarioYaEfectuadoException {
        dadoUnCierreFallido();
        ModelAndView mav = cuandoEjecutoElCierre();
        entoncesEncuentro2(mav);
        entoncesMeLLevaALaVista(mav, "CierreDiario");

    }

    @Test
    public void queGoCierreViewMeLLeve() throws CierreDiarioYaEfectuadoException {
        ModelAndView mav = cuandoVoyAlFormDeCierre();
        entoncesMeLLevaALaVista(mav, "CierreDiario");
    }

    private ModelAndView cuandoVoyAlFormDeCierre() {
        return controladorCierre.irCierreDiarioView(new CierreDiario());
    }

    private void dadoUnCierreExitoso() throws CierreDiarioYaEfectuadoException {
        when(this.servicioCierre.ejecutarCierre()).thenReturn(true);
    }

    private void dadoUnCierreFallido() throws CierreDiarioYaEfectuadoException {
        when(this.servicioCierre.ejecutarCierre()).thenThrow(new CierreDiarioYaEfectuadoException());
    }

    private void entoncesMeLLevaALaVista(ModelAndView mav, String vistaEsperada) {
        assertThat(mav.getViewName()).isEqualTo(vistaEsperada);
    }

    private void entoncesEncuentro(ModelAndView mav) {
        assertThat(mav.getModel().get("exito").equals(true));
        assertThat(mav.getModel().get("mensaje").toString().equals("El Cierre se ejecuto con Exito!!"));
    }

    private void entoncesEncuentro2(ModelAndView mav) {
        assertThat(mav.getModel().get("exito").equals(false));
        assertThat(mav.getModel().get("mensaje").toString().contains("El cierre de la fecha"));
    }

    private ModelAndView cuandoEjecutoElCierre() {
        return controladorCierre.ejecutarCierre(new CierreDiario(), request);
    }


}
