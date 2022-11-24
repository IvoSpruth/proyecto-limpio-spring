package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.SpringTest;

import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.ventas.*;
import org.dom4j.rule.Mode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorVentaTest extends SpringTest {

    private ControladorVenta controladorVenta;
    private ServicioVenta servicioVenta;
    private ServicioProducto servicioProducto;

    private HttpServletRequest request;

    private Venta venta;



    @Before
    public void init(){
        this.servicioVenta = mock(ServicioVenta.class);
        this.servicioProducto = mock(ServicioProducto.class);
        this.controladorVenta = new ControladorVenta(this.servicioProducto, this.servicioVenta );
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
        venta.setIdEmpleado(1);
        //venta.setProductos(prepareProductos());
        return venta;
    }

    private Set<Producto> prepareProductos(){
        Set<Producto> productos = new HashSet<>();
        Producto producto1, producto2;

        producto1 = new Producto();
        producto1.setId((long)1);
        producto1.setCantidad(50);
        producto1.setCosto(500);
        producto1.setNombre("cargador");
        producto1.setIdProveedor(1);

        producto2 = new Producto();
        producto2.setId((long)2);
        producto2.setCantidad(100);
        producto2.setCosto(1700);
        producto2.setNombre("adaptador");
        producto2.setIdProveedor(2);

        productos.add(producto1);
        productos.add(producto2);

        return productos;
    }
}
