package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.ServicioCierreDiario;
import ar.edu.unlam.tallerweb1.domain.cobros.ServicioMercadoPago;
import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.RepositorioEmpleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleadoImpl;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.RepositorioProducto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProductoImpl;
import ar.edu.unlam.tallerweb1.domain.ventas.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioVentaTest extends SpringTest {

    private ServicioVenta servicioVenta;
    private RepositorioVenta repositorioVenta;
    private ServicioProducto servicioProducto;
    private ServicioEmpleado servicioEmpleado;

    private ServicioCierreDiario servicioCierre;
    private ServicioMercadoPago servicioMercadoPago;

    private HttpServletRequest request;


    private Venta venta;
    private Producto productoUno, productoDos;

    public static final int CANTIDAD_ESPERADA = 40;

    @Before
    public void init() {
        this.repositorioVenta = mock(RepositorioVenta.class);
        this.servicioProducto = new ServicioProductoImpl(mock(RepositorioProducto.class));
        this.servicioEmpleado = new ServicioEmpleadoImpl(mock(RepositorioEmpleado.class));
        this.servicioCierre = mock(ServicioCierreDiario.class);
        this.servicioMercadoPago = mock(ServicioMercadoPago.class);
        this.servicioVenta = new ServicioVentaImpl(this.repositorioVenta, this.servicioProducto, this.servicioEmpleado, this.servicioCierre, this.servicioMercadoPago);
        venta = prepareVenta();
        //productoUno = prepareProductoUno();
        //productoDos = prepareProductoDos();
        this.request = mock(HttpServletRequest.class);
    }

    @Test
    public void alRealizarUnaVentaConUnProductoLaSumaCorrectamente() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueSumoUnaVentaConUnProducto(venta);
        Integer cantidad = cuandoConsultoLosProductosDeLaVenta();
        entoncesEncuentroLaCantidadCorrecta(cantidad, CANTIDAD_ESPERADA);
    }

    @Rollback
    @Test
    public void queMePermitaRealizarUnaVenta() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueExisteUnaVenta();
        assertTrue(cuandoAgregaUnaVenta(prepareVenta()));
    }

    @Test(expected = IdEmpleadoNoValidoException.class)
    public void queNoPermitaRealizarLaVentaDebidoAQueSeIngresoUnIdNoValido() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueExisteUnaVenta();
        assertTrue(cuandoAgregaUnaVenta(prepareVentaConIdEmpleadoInvalido()));
    }

    @Test(expected = CantidadInsuficienteException.class)
    public void queNoPermitaRealizarLaVentaDebidoAQueNoHaySuficienteStockDelProducto() throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        dadoQueExisteUnaVenta();
        assertTrue(cuandoAgregaUnaVenta(prepareVentaConCantidadProductosInsuficientes()));
    }

    private Venta prepareVentaConCantidadProductosInsuficientes() {
        Venta venta = new Venta();
        venta.setIdEmpleado(1);
        venta.setIdProducto(1);
        venta.setCantidadProducto(10000);
        venta.setIdProducto2(2);
        venta.setCantidadProducto2(80);
        return venta;
    }

    private Venta prepareVentaConIdEmpleadoInvalido() {
        Venta venta = new Venta();
        venta.setIdEmpleado(6);
        venta.setIdProducto(1);
        venta.setCantidadProducto(40);
        venta.setIdProducto2(2);
        venta.setCantidadProducto2(80);
        return venta;
    }

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

    private void dadoQueExisteUnaVenta() {
        when(this.servicioProducto.buscarProductos()).thenReturn(prepareProductos());
        when(this.servicioEmpleado.traemeTodosLosEmpleados()).thenReturn(prepareEmpleados());
        //when(this.servicioProducto.updateProductos(prepareProductos())).getMock();
    }

    private boolean cuandoAgregaUnaVenta(Venta venta) throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        return servicioVenta.addVenta(venta);
    }

    private List<Producto> prepareProductos() {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        Producto producto1, producto2;

        producto1 = new Producto();
        producto1.setId((long) 1);
        producto1.setCantidad(50);
        producto1.setCosto(500);
        producto1.setNombre("cargador");
        producto1.setIdProveedor(1);

        producto2 = new Producto();
        producto2.setId((long) 2);
        producto2.setCantidad(100);
        producto2.setCosto(1700);
        producto2.setNombre("adaptador");
        producto2.setIdProveedor(2);

        productos.add(producto1);
        productos.add(producto2);

        return productos;
    }

    private List<Empleado> prepareEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        Empleado e1, e2;

        e1 = new Empleado();
        e1.setId(1L);
        e1.setName("lucas");
        e1.setRol("gerente");
        e1.setSueldo(75000);

        e2 = new Empleado();
        e2.setId(2L);
        e2.setName("Virginia");
        e2.setRol("dueño");
        e2.setSueldo(500000);

        empleados.add(e1);
        empleados.add(e1);

        return empleados;
    }

    private Venta prepareVenta() {
        Venta venta = new Venta();
        venta.setIdEmpleado(1);
        venta.setIdProducto(1);
        venta.setCantidadProducto(40);
        venta.setIdProducto2(2);
        venta.setCantidadProducto2(80);
        return venta;
    }
}
