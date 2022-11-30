package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.delivery.forms.DatosVenta;
import ar.edu.unlam.tallerweb1.domain.cobros.MercadoPago;
import ar.edu.unlam.tallerweb1.domain.cobros.ServicioMercadoPago;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.ventas.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorVentaTest extends SpringTest {

    private ControladorVenta controladorVenta;
    private ServicioVenta servicioVenta;
    private ServicioProducto servicioProducto;
    private ServicioMercadoPago servicioMercadoPago;
    private ServicioEmpleado servicioEmpleado;
    private DatosVenta datosVenta;

    private HttpServletRequest request;

    private Venta venta;


    @Before
    public void init() {
        this.datosVenta = mock(DatosVenta.class);
        this.servicioVenta = mock(ServicioVenta.class);
        this.servicioProducto = mock(ServicioProducto.class);
        this.servicioMercadoPago = mock(ServicioMercadoPago.class);
        this.servicioEmpleado = mock(ServicioEmpleado.class);
        this.controladorVenta = new ControladorVenta(this.servicioProducto, this.servicioVenta, this.servicioMercadoPago, this.servicioEmpleado);
        venta = prepareVenta();
        this.request = mock(HttpServletRequest.class);
    }


    @Test
    public void alAgregarUnaVentaExitosaObtengoMAVCorrecto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueMockeMercadoPago();
        dadoQueExisteUnaVentaCorrecta();
        ModelAndView mav = cuandoRealizoUnaVenta();
        entoncesEncuentro(mav);
        entoncesMeLLevaALaVista(mav, "resumenVenta");

    }

    @Test
    public void cuandoArrojaCantidadInsufienteMeDaElMavCorrecto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueMockeMercadoPago();
        dadoQueExisteUnaVentaConCantidadIncorrecta();
        ModelAndView mav = cuandoRealizoUnaVenta();
        entoncesEncuentro2(mav);
        entoncesMeLLevaALaVista(mav, "ventaForm");
    }

    @Test
    public void cuandoArrojaEmpleadoNoValidoMeDaElMavCorrecto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueMockeMercadoPago();
        dadoQueExisteUnaVentaConEmpleadoInexistente();
        ModelAndView mav = cuandoRealizoUnaVenta();
        entoncesEncuentro2(mav);
        entoncesMeLLevaALaVista(mav, "ventaForm");

    }

    @Test
    public void cuandoSolicitoElResumenMeTraeElMavCorrecto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueMockeMercadoPago();
        dadoQueExisteUnaVentaCorrecta();
        ModelAndView mav = cuandoSolicitoElResumen();
        entoncesMeLLevaALaVistaDeResumen(mav, "resumenVenta");

    }

    @Test
    public void alSolicitarElResumenMeTraeLaCantidadVendidaCorrecta() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueMockeMercadoPago();
        dadoQueExisteUnaVentaCorrecta();
        ModelAndView mav = controladorVenta.addVenta(datosVenta, request);
        List<ModelMap> cantidadVenta = (List<ModelMap>) mav.getModel().get("productos");
        int esperado = (int) cantidadVenta.get(0).get("cantidad");
        int obtenido = venta.getProductos().get(0).getCantidad();
        assertThat(esperado).isEqualTo(obtenido);
    }

    @Test
    public void alSolicitarElResumenMeTraeElIdDeProductoCorrecto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueMockeMercadoPago();
        dadoQueExisteUnaVentaCorrecta();
        ModelAndView mav = controladorVenta.addVenta(datosVenta, request);
        List<ModelMap> cantidadVenta = (List<ModelMap>) mav.getModel().get("productos");
        assertThat(cantidadVenta.get(0).get("id")).isEqualTo(venta.getProductos().get(0).getId());
    }

    //Dado
    private void dadoQueMockeMercadoPago() {
        MercadoPago ml = new MercadoPago(any(), "", "");
        when(servicioMercadoPago.obtener(venta)).thenReturn(ml);
    }


    //Cuando
    private Venta prepareVenta() {
        Venta venta = new Venta();
        venta.setIdEmpleado(1);
        venta.setProductos(prepareProductos(venta));
        return venta;
    }

    private List<VentaProducto> prepareProductos(Venta venta) {
        List<Producto> productos = new ArrayList<>();
        Producto producto = new Producto();
        VentaProducto producto1, producto2;
        VentaProductoId ventaProductoId1 = new VentaProductoId(1L, 1L);
        VentaProductoId ventaProductoId2 = new VentaProductoId(1L, 2L);

        producto1 = new VentaProducto(venta, producto, 1);
        producto1.setId(ventaProductoId1);
        producto1.setCantidad(50);

        producto2 = new VentaProducto(venta, producto, 1);
        producto2.setId(ventaProductoId2);
        producto2.setCantidad(100);


        List<VentaProducto> ventaProducto = new ArrayList<>();
        ventaProducto.add(producto1);
        ventaProducto.add(producto2);

        venta.addProducto(producto);
        return ventaProducto;
    }
    private List<ModelMap> prepareProductosModel(List<Producto> productos) {
        ModelMap prods = new ModelMap();
        ArrayList<ModelMap> pp = new ArrayList<>();

        for (Producto p : productos) {
            ModelMap producto = new ModelMap();
            producto.addAttribute("nombre", p.getNombre());
            producto.addAttribute("precio", p.getCosto());
            producto.addAttribute("id", p.getId());
            producto.addAttribute("cantidad", p.getCantidad());
            producto.addAttribute("totalProducto", p.getCosto() * p.getCantidad());
            producto.addAttribute("descuento", false);
            pp.add(producto);
        }

        return pp;
    }

    private ModelAndView cuandoSolicitoElResumen() {
        return controladorVenta.addVenta(datosVenta, request);
    }

    private ModelAndView cuandoRealizoUnaVenta() {
        return controladorVenta.addVenta(this.datosVenta, request);
    }

    //Entonces

    private void entoncesMeLLevaALaVistaDeResumen(ModelAndView mav, String vistaEsperada) {
        assertThat(mav.getViewName()).isEqualTo(vistaEsperada);
    }

    private void entoncesMeLLevaALaVista(ModelAndView mav, String vistaEsperada) {
        assertThat(mav.getViewName()).isEqualTo(vistaEsperada);
    }

    private void entoncesEncuentro(ModelAndView mav) {
        assertThat(mav.getModel().get("exito").equals(true));
    }

    private void entoncesEncuentro2(ModelAndView mav) {
        assertThat(mav.getModel().get("exito").equals(false));
    }


    private void dadoQueExisteUnaVentaCorrecta() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        when(this.datosVenta.toVenta()).thenReturn(prepareVenta());
        when(this.servicioVenta.addVenta(this.venta)).thenReturn(true);
        when(this.servicioVenta.buscarVenta(this.venta)).thenReturn(this.venta);
        when(servicioVenta.getProductos(any())).thenReturn(venta.getProductos());
    }

    private void dadoQueExisteUnaVentaConCantidadIncorrecta() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        CantidadInsuficienteException exception = new CantidadInsuficienteException("test");
        when(servicioVenta.addVenta(any())).thenThrow(exception);
    }

    private void dadoQueExisteUnaVentaConEmpleadoInexistente() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        when(this.servicioVenta.addVenta(any())).thenThrow(IdEmpleadoNoValidoException.class);
    }
}
