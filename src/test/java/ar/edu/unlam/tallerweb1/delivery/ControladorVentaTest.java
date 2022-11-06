package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.SpringTest;

import ar.edu.unlam.tallerweb1.domain.cobros.ServicioMercadoPago;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.ventas.*;
import org.dom4j.rule.Mode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorVentaTest extends SpringTest {

    private ControladorVenta controladorVenta;
    private ServicioVenta servicioVenta;
    private ServicioProducto servicioProducto;
    private ServicioMercadoPago servicioMercadoPago;

    private HttpServletRequest request;

    private Venta venta;



    @Before
    public void init(){
        this.servicioVenta = mock(ServicioVenta.class);
        this.servicioProducto = mock(ServicioProducto.class);
        this.servicioMercadoPago = mock(ServicioMercadoPago.class);
        this.controladorVenta = new ControladorVenta(this.servicioProducto, this.servicioVenta ,this.servicioMercadoPago);
        venta = prepareVenta();
        this.request = mock(HttpServletRequest.class);
    }



    @Test
    public void alAgregarUnaVentaExitosaObtengoMAVCorrecto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException{

        dadoQueExisteUnaVentaCorrecta();
        ModelAndView mav = cuandoRealizoUnaVenta();
        entoncesEncuentro(mav);
        entoncesMeLLevaALaVista(mav, "resumenVenta");

    }

    @Test
    public void cuandoArrojaCantidadInsufienteMeDaElMavCorrecto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException{

        dadoQueExisteUnaVentaConCantidadIncorrecta();
        ModelAndView mav = cuandoRealizoUnaVenta();
        entoncesEncuentro2(mav);
        entoncesMeLLevaALaVista(mav, "ventaForm");

    }

    @Test
    public void cuandoArrojaEmpleadoNoValidoMeDaElMavCorrecto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException{

        dadoQueExisteUnaVentaConEmpleadoInexistente();
        ModelAndView mav = cuandoRealizoUnaVenta();
        entoncesEncuentro2(mav);
        entoncesMeLLevaALaVista(mav, "ventaForm");

    }

    @Test
    public void cuandoSolicitoElResumenMeTraeElMavCorrecto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {

        dadoQueExisteUnaVentaCorrecta();
        ModelAndView mav = cuandoSolicitoElResumen();
        entoncesMeLLevaALaVistaDeResumen(mav, "resumenVenta");

    }

    @Test
    public void alSolicitarElResumenMeTraeLaCantidadVendidaCorrecta() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {

        ModelAndView mav = controladorVenta.addVenta(venta, request);
        Integer cantidadVenta = (Integer) mav.getModel().get("cantidadUno");
        assertThat(cantidadVenta).isEqualTo(venta.getCantidadProducto());

    }


      @Test
        public void alSolicitarElResumenMeTraeElIdDeProductoCorrecto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {

        ModelAndView mav = controladorVenta.addVenta(venta, request);
        Integer idProductoDos = (Integer) mav.getModel().get("idProductoDos");
        assertThat(idProductoDos).isEqualTo(venta.getIdProducto2());
    }

    private void entoncesMeLLevaALaVistaDeResumen(ModelAndView mav, String vistaEsperada) {
        assertThat(mav.getViewName()).isEqualTo(vistaEsperada);
    }


    private ModelAndView cuandoSolicitoElResumen() {
        return controladorVenta.addVenta(this.venta, request);
    }

    private void entoncesMeLLevaALaVista(ModelAndView mav, String vistaEsperada) {
        assertThat(mav.getViewName()).isEqualTo(vistaEsperada);
    }

    private void entoncesEncuentro(ModelAndView mav){
        assertThat(mav.getModel().get("exito").equals(true));
    }

    private void entoncesEncuentro2(ModelAndView mav){
        assertThat(mav.getModel().get("exito").equals(false));
    }

    private ModelAndView cuandoRealizoUnaVenta(){
        return controladorVenta.addVenta(this.venta, request);
    }

    private void dadoQueExisteUnaVentaCorrecta() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        when(this.servicioVenta.addVenta(this.venta)).thenReturn(true);
    }

    private void dadoQueExisteUnaVentaConCantidadIncorrecta() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        when(this.servicioVenta.addVenta(this.venta)).thenThrow(CantidadInsuficienteException.class);
    }

    private void dadoQueExisteUnaVentaConEmpleadoInexistente() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        when(this.servicioVenta.addVenta(this.venta)).thenThrow(IdEmpleadoNoValidoException.class);
    }


    private Venta prepareVenta() {
        Venta venta = new Venta();
        venta.setIdEmpleado(2);
        venta.setIdProducto(2);
        venta.setCantidadProducto(10);
        venta.setIdProducto2(3);
        venta.setCantidadProducto2(20);
        return venta;
    }
}
