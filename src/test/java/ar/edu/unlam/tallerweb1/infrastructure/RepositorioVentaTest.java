package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.RepositorioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.ventas.RepositorioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class RepositorioVentaTest extends SpringTest {


    @Autowired
    private RepositorioVenta repositorioVenta ;


    private static final int ID_EMPLEADO1 = 4;
    private static final String NOMBRE_EMPLEADO = "Pepe";
    private static final String ROL = "Dueño";
    private static final Double SUELDO = 300.0;
    private static final int ID_PRODUCTO = 1;
    private static final int CANTIDAD_PRODUCTO = 2;
    private static final int ID_PRODUCTO2 = 2;
    private static final int CANTIDAD_PRODUCTO2 = 2;



    @Test
    @Transactional
    @Rollback
    public void buscarVentasDeUnEmpleadoPorSuIdDeberiaTraerSoloLasDelEmpleado(){
        Empleado empleado = dadoQueExisteUnNuevoEmpleado(ID_EMPLEADO1,NOMBRE_EMPLEADO, ROL, SUELDO );
        Venta venta = dadoQueExisteUnaVenta(ID_EMPLEADO1, ID_PRODUCTO, CANTIDAD_PRODUCTO, ID_PRODUCTO2, CANTIDAD_PRODUCTO2);
        List<Venta> ventasBuscadas = cuandoBuscoUnaVentaPorIdDelEmpleado(ID_EMPLEADO1);
        entoncesMeTrae(ventasBuscadas, 1);
    }

    @Test
    @Transactional
    @Rollback
    public void buscarTodasLasVentasRealizadas(){
        Empleado empleado = dadoQueExisteUnNuevoEmpleado(ID_EMPLEADO1,NOMBRE_EMPLEADO, ROL, SUELDO );
        Venta venta = dadoQueExisteUnaVenta(ID_EMPLEADO1, ID_PRODUCTO, CANTIDAD_PRODUCTO, ID_PRODUCTO2, CANTIDAD_PRODUCTO2);
        Venta venta2 = dadoQueExisteUnaVenta(ID_EMPLEADO1, ID_PRODUCTO, CANTIDAD_PRODUCTO, ID_PRODUCTO2, CANTIDAD_PRODUCTO2);
        Venta venta3 = dadoQueExisteUnaVenta(ID_EMPLEADO1, ID_PRODUCTO, CANTIDAD_PRODUCTO, ID_PRODUCTO2, CANTIDAD_PRODUCTO2);
        List<Venta> todasLasVentasBuscadas = cuandoBuscoTodasLasVentas();
        entoncesMeTrae(todasLasVentasBuscadas, 3);
    }

    @Test
    @Transactional
    @Rollback
    public void cuandoBuscoVentaPorFechaMeTraeCorrectas(){
        Empleado empleado = dadoQueExisteUnNuevoEmpleado(ID_EMPLEADO1,NOMBRE_EMPLEADO, ROL, SUELDO );
        Venta venta = dadoQueExisteUnaVenta(ID_EMPLEADO1, ID_PRODUCTO, CANTIDAD_PRODUCTO, ID_PRODUCTO2, CANTIDAD_PRODUCTO2);
        Venta venta2 = dadoQueExisteUnaVentaDeOtroDia(ID_EMPLEADO1, ID_PRODUCTO, CANTIDAD_PRODUCTO, ID_PRODUCTO2, CANTIDAD_PRODUCTO2, 1);
        Venta venta3 = dadoQueExisteUnaVenta(ID_EMPLEADO1, ID_PRODUCTO, CANTIDAD_PRODUCTO, ID_PRODUCTO2, CANTIDAD_PRODUCTO2);
        List<Venta> todasLasVentasBuscadas = cuandoBuscoVentaPorFecha();
        entoncesMeTrae(todasLasVentasBuscadas, 3);
    }

    private List<Venta> cuandoBuscoVentaPorFecha() {
        return this.repositorioVenta.buscarVentaPorFecha(LocalDate.now());
    }

    private List<Venta> cuandoBuscoTodasLasVentas() {
        return this.repositorioVenta.buscarTodasLasVentas();
    }

    private void entoncesMeTrae(List<Venta> ventasBuscadas, int icantidadDeVentasEsperadas) {
        assertEquals(ventasBuscadas.size(), icantidadDeVentasEsperadas);
    }

    private List<Venta> cuandoBuscoUnaVentaPorIdDelEmpleado(int idEmpleado) {
        return repositorioVenta.ventasDeUnEmpleado(idEmpleado);
    }

    private Venta dadoQueExisteUnaVenta(int idEmpleado, int idProducto, int cantidadProducto, int idProducto2, int cantidadProducto2) {
        Venta venta = new Venta();
//        venta.setId(1L);
        venta.setIdEmpleado(idEmpleado);
        venta.setTotal(20.0);

        Producto p1, p2;
        p1 = new Producto();
        p1.setNombre("Proyector");
        p1.setCosto(100000.0);
        p1.setIdProveedor(2);
        p1.setCantidad(5);

        p2 = new Producto();
        p2.setNombre("Notebook");
        p2.setCosto(500000.0);
        p2.setIdProveedor(2);
        p2.setCantidad(2);

        session().save(p1);
        session().save(p2);

        Set<Producto> ventaFinal = venta.getProductos();
        ventaFinal.add(p1);
        ventaFinal.add(p2);

        venta.setProductos(ventaFinal);


        session().save(venta);
        return venta;
    }
    private Venta dadoQueExisteUnaVentaDeOtroDia(int idEmpleado, int idProducto, int cantidadProducto, int idProducto2, int cantidadProducto2, int cantDias) {
        Venta venta = new Venta();
//        venta.setId(1L);
        Producto p1, p2;
        p1 = new Producto();
        p1.setNombre("Proyector");
        p1.setCosto(100000.0);
        p1.setIdProveedor(2);
        p1.setCantidad(5);

        p2 = new Producto();
        p2.setNombre("Notebook");
        p2.setCosto(500000.0);
        p2.setIdProveedor(2);
        p2.setCantidad(2);

        venta.getProductos().add(p1);
        venta.getProductos().add(p2);


        venta.setFecha(LocalDate.now().plusDays(2));
        session().save(venta);
        return venta;
    }

    private Empleado dadoQueExisteUnNuevoEmpleado(int idEmpleado, String nombreEmpleado, String rol, Double sueldo) {
        Empleado empleado = new Empleado();
        empleado.setId(4L);
        empleado.setName(nombreEmpleado);
        empleado.setRol(rol);
        empleado.setSueldo(sueldo);
        session().save(empleado);
        return empleado;
    }
}
