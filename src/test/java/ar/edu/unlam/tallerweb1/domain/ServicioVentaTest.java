package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.delivery.ControladorVenta;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.ventas.*;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioVentaImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioVentaTest extends SpringTest {

    private ServicioVenta servicioVenta;
    private ServicioProducto servicioProducto;
    //private ServicioEmpleado servicioEmpleado;

    private RepositorioVenta repositorioVenta;

    private HttpServletRequest request;

    private Venta venta;
    private Producto productoUno, productoDos;

    public static final int CANTIDAD_ESPERADA = 10;

    @Before
    public void init(){
        //this.servicioEmpleado = mock(ServicioEmpleado.class);
        this.servicioVenta = mock(ServicioVenta.class);
        this.servicioProducto = mock(ServicioProducto.class);
        this.repositorioVenta = mock(RepositorioVenta.class);
        venta = prepareVenta();
        productoUno = prepareProductoUno();
        productoDos = prepareProductoDos();
        this.request = mock(HttpServletRequest.class);
    }

    @Test
    public void alRealizarUnaVentaConUnProductoLaSumaCorrectamente() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueSumoUnaVentaConUnProducto(venta);
        Integer cantidad = cuandoConsultoLosProductosDeLaVenta();
        entoncesEncuentroLaCantidadCorrecta(cantidad, CANTIDAD_ESPERADA);
    }

/*    @Test
    public void alGenerarUnaVentaBuscaCorrectamenteElNombreDelProductoPorId() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueSumoUnaVentaConUnProducto(venta);
        String nombrePorIdProducto = cuandoBuscoElNombreDelProductoPorSuId(productoUno.getId());
        String nombrePorIdVenta = cuandoBuscoElNombreDelProductoPorElIdQueIndicaLaVenta(venta.getIdProducto());
        entoncesObtengoElNombreEsperado(nombrePorIdVenta, nombrePorIdProducto);
    }*/

/*    private String cuandoBuscoElNombreDelProductoPorSuId(Long id) {
        return productoUno.getNombre();
    }

    private void entoncesObtengoElNombreEsperado(String nombrePorIdVenta, String nombrePorIdProducto) {
        assertThat(nombrePorIdVenta).isEqualTo(nombrePorIdProducto);
    }

    private String cuandoBuscoElNombreDelProductoPorElIdQueIndicaLaVenta(int idProducto) {
        return servicioVenta.buscarNombreProducto(idProducto);
    }*/

    private void entoncesEncuentroLaCantidadCorrecta(Integer cantidad, int cantidadEsperada) {
        assertThat(cantidad).isEqualTo(cantidadEsperada);
    }

    private Integer cuandoConsultoLosProductosDeLaVenta() {
        Integer cantidadProducto = venta.getCantidadProducto();
        return cantidadProducto;
    }

    private void dadoQueSumoUnaVentaConUnProducto(Venta venta) throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        servicioVenta.addVenta(venta);
    }


    private Venta prepareVenta() {
        Venta venta = new Venta();
        venta.setIdEmpleado(1);
        venta.setIdProducto(2);
        venta.setCantidadProducto(10);
        venta.setIdProducto2(3);
        venta.setCantidadProducto2(20);
        return venta;
    }

    private Producto prepareProductoUno() {
        Producto productoUno = new Producto();
        productoUno.setId(1L);
        productoUno.setCosto(1000);
        productoUno.setNombre("notebook");
        productoUno.setIdProveedor(2);
        productoUno.setCantidad(20);
        return productoUno;
    }

    private Producto prepareProductoDos() {
        Producto productoDos = new Producto();
        productoDos.setId(2L);
        productoDos.setCosto(3000);
        productoDos.setNombre("heladera");
        productoDos.setIdProveedor(1);
        productoDos.setCantidad(15);
        return productoDos;
    }
}
