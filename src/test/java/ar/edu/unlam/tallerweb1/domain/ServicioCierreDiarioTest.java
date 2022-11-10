package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.*;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioCierreDiarioTest extends SpringTest {

    private ServicioVenta servicioVenta;

    private ServicioCierreDiario servicioCierre;
    private RepositorioCierreDiario repositorioCierre;


    @Before
    public void init(){
        this.servicioVenta = mock(ServicioVenta.class);
        this.repositorioCierre = mock(RepositorioCierreDiario.class);
        this.servicioCierre = new ServicioCierreDiarioImpl(repositorioCierre);
    }


    @Rollback
    @Test
    public void queMePermitaRealizarUnCierreCuandoNoLoHiceAntes() throws CierreDiarioYaEfectuadoException{
        dadoUnCierreExistente();
        boolean exito = cuandoEjecutoElCierre();
        entoncesEncuentro(exito);
    }

    @Rollback
    @Test(expected = CierreDiarioYaEfectuadoException.class)
    public void queLanceErrorCuandoQuieroHacerUnSegundoCierre() throws CierreDiarioYaEfectuadoException{
        dadoUnCierreYaCerrado();
        boolean exito = cuandoEjecutoElCierre();
    }

    private void dadoUnCierreExistente() {
        CierreDiario cd = new CierreDiario();
        cd.setId(11l);
        cd.setTotal(60000.0);

        Venta v1, v2;
        v1 = new Venta();
        v2 = new Venta();

        v1.setId(12l);
        v1.setCierre(cd);
        v1.setTotal(10000);
        v1.setIdProducto(1);
        v1.setCantidadProducto(10);
        v1.setIdProducto2(2);
        v1.setCantidadProducto2(5);

        v2.setId(9l);
        v2.setCierre(cd);
        v2.setTotal(5000);
        v2.setIdProducto(1);
        v2.setCantidadProducto(10);
        v2.setIdProducto2(2);
        v2.setCantidadProducto2(5);

        cd.getVentas().add(v1);
        cd.getVentas().add(v2);

        when(this.repositorioCierre.buscarCierrePorFecha(LocalDate.now())).thenReturn(cd);
    }

    private void dadoUnCierreYaCerrado(){
        CierreDiario cd = new CierreDiario();
        cd.setId(11l);
        cd.setTotal(60000.0);

        Venta v1, v2;
        v1 = new Venta();
        v2 = new Venta();

        v1.setId(12l);
        v1.setCierre(cd);
        v1.setTotal(10000);
        v1.setIdProducto(1);
        v1.setCantidadProducto(10);
        v1.setIdProducto2(2);
        v1.setCantidadProducto2(5);

        v2.setId(9l);
        v2.setCierre(cd);
        v2.setTotal(5000);
        v2.setIdProducto(1);
        v2.setCantidadProducto(10);
        v2.setIdProducto2(2);
        v2.setCantidadProducto2(5);

        cd.getVentas().add(v1);
        cd.getVentas().add(v2);
        cd.setCerrado(true);
        when(this.repositorioCierre.buscarCierrePorFecha(LocalDate.now())).thenReturn(cd);
    }

    private void entoncesEncuentro(boolean exito) {
        assertTrue(exito);
    }

    private boolean cuandoEjecutoElCierre() throws CierreDiarioYaEfectuadoException {
        return servicioCierre.ejecutarCierre();
    }

    private void dadoQueExisteUnaVenta(){
        //when(this.servicioProducto.buscarProductos()).thenReturn(prepareProductos());
    }

    private boolean cuandoAgregaUnaVenta(Venta venta) throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        return servicioVenta.addVenta(venta);
    }

    private List<Producto> prepareProductos(){
        ArrayList<Producto> productos = new ArrayList<Producto>();
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

    private List<Empleado> prepareEmpleados(){
       ArrayList<Empleado> empleados = new ArrayList<Empleado>();
       Empleado e1,e2;

       e1 = new Empleado();
           e1.setId((long)1);
           e1.setName("lucas");
           e1.setRol("gerente");
           e1.setSueldo(75000);

        e2 = new Empleado();
            e2.setId((long)2);
            e2.setName("Virginia");
            e2.setRol("dueño");
            e2.setSueldo(500000);

        empleados.add(e1);
        empleados.add(e1);

        return empleados;
    }

    private Venta prepareVenta(){
        Venta venta = new Venta();
        venta.setIdEmpleado(1);
        venta.setIdProducto(1);
        venta.setCantidadProducto(40);
        venta.setIdProducto2(2);
        venta.setCantidadProducto2(80);
        return venta;
    }
}