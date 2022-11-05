package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.ventas.RepositorioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@Transactional
@Rollback
public class RepositorioVentaTest extends SpringTest {

    @Autowired
    private RepositorioVenta repositorioVenta;

    private static final int ID_EMPLEADO1 = 4;
    private static final String NOMBRE_EMPLEADO = "Pepe";
    private static final String ROL = "Dueño";
    private static final Double SUELDO = 300.0;
    private static final int ID_PRODUCTO = 1;
    private static final int CANTIDAD_PRODUCTO = 2;
    private static final int ID_PRODUCTO2 = 2;
    private static final int CANTIDAD_PRODUCTO2 = 2;

    @Test
    public void cuandoBuscoVentaPorFechaMeTraeCorrectas(){
        Empleado empleado = dadoQueExisteUnNuevoEmpleado(ID_EMPLEADO1,NOMBRE_EMPLEADO, ROL, SUELDO );
        Venta venta = dadoQueExisteUnaVenta(ID_EMPLEADO1, ID_PRODUCTO, CANTIDAD_PRODUCTO, ID_PRODUCTO2, CANTIDAD_PRODUCTO2);
//        Venta venta2 = dadoQueExisteUnaVentaDeOtroDia(ID_EMPLEADO1, ID_PRODUCTO, CANTIDAD_PRODUCTO, ID_PRODUCTO2, CANTIDAD_PRODUCTO2, 1);
        Venta venta3 = dadoQueExisteUnaVenta(ID_EMPLEADO1, ID_PRODUCTO, CANTIDAD_PRODUCTO, ID_PRODUCTO2, CANTIDAD_PRODUCTO2);
        List<Venta> todasLasVentasBuscadas = cuandoBuscoVentaPorFecha();
        entoncesMeTrae(todasLasVentasBuscadas, 2);
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
        venta.setIdProducto(idProducto);
        venta.setCantidadProducto(cantidadProducto);
        venta.setIdProducto2(idProducto2);
        venta.setCantidadProducto2(cantidadProducto2);
        venta.setTotal(20.0);
        session().save(venta);
        return venta;
    }
    private Venta dadoQueExisteUnaVentaDeOtroDia(int idEmpleado, int idProducto, int cantidadProducto, int idProducto2, int cantidadProducto2, int cantDias) {
        Venta venta = new Venta();
//        venta.setId(1L);
        venta.setIdEmpleado(idEmpleado);
        venta.setIdProducto(idProducto);
        venta.setCantidadProducto(cantidadProducto);
        venta.setIdProducto2(idProducto2);
        venta.setCantidadProducto2(cantidadProducto2);
        venta.setTotal(20.0);
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

    @Test
    public void cuandoListoVentasPorEmpleadoObtengoVentasCorrectas(){
        List<Venta> listaRecibida = cuandoListoLasVentas(1, null, null);

        entoncesObtengoVentasCorrectas(listaRecibida, 9);
    }

    @Test
    public void cuandoListoVentasPorEmpleadoDesdeDeUnaFechaObtengoVentasCorrectas(){
        LocalDate fechaInicio = dadoQueTengoUnaFecha(2022, 10, 27);

        List<Venta> listaRecibida = cuandoListoLasVentas(1, fechaInicio, null);

        entoncesObtengoVentasCorrectas(listaRecibida, 5);
    }

    @Test
    public void cuandoListoVentasPorEmpleadoHastaUnaFechaObtengoVentasCorrectas(){
        LocalDate fechaFinal = dadoQueTengoUnaFecha(2020, 12, 31);

        List<Venta> listaRecibida = cuandoListoLasVentas(1, null, fechaFinal);

        entoncesObtengoVentasCorrectas(listaRecibida, 2);
    }

    @Test
    public void cuandoListoVentasPorEmpleadoEntreFechasObtengoVentasCorrectas(){
        LocalDate fechaInicial = dadoQueTengoUnaFecha(2020, 10, 28);
        LocalDate fechaFinal = dadoQueTengoUnaFecha(2021, 10, 27);

        List<Venta> listaRecibida = cuandoListoLasVentas(1, fechaInicial, fechaFinal);

        entoncesObtengoVentasCorrectas(listaRecibida, 2);
    }
    /* esto está comentado porque se cargan las ventas desde la base de datos previamente
    private List<Venta> dadoQueTengoUnSetDeVentas() {
        List<Venta> listaVentas = new ArrayList<>();

        listaVentas.add(crearVenta(2022, 11, 1, 1));
        listaVentas.add(crearVenta(2022, 11, 2, 1));
        listaVentas.add(crearVenta(2022, 11, 3, 1));
        listaVentas.add(crearVenta(2021, 10, 2, 1));
        listaVentas.add(crearVenta(2021, 9, 18, 1));
        listaVentas.add(crearVenta(2020, 10, 2, 1));
        listaVentas.add(crearVenta(2018, 1, 1, 1));

        return listaVentas;
    }

    private Venta crearVenta(Integer anio, Integer mes, Integer dia, Integer idEmpleado) {
        Venta venta = new Venta();
        venta.setFecha(LocalDate.of(anio, mes, dia));
        venta.setIdEmpleado(idEmpleado);
        session().save(venta);
        return venta;
    }
    */
    private List<Venta> cuandoListoLasVentas(Integer idEmpleado, LocalDate fechaInicio, LocalDate fechaFinal) {
        return repositorioVenta.listarVentasPorEmpleadoYFechas(idEmpleado, fechaInicio, fechaFinal);
    }

    private void entoncesObtengoVentasCorrectas(List<Venta> listaObtenida, Integer tamanio) {
        assertThat(listaObtenida).hasSize(tamanio);
    }

    private LocalDate dadoQueTengoUnaFecha(Integer anio, Integer mes, Integer dia) {
        return LocalDate.of(anio, mes, dia);
    }
}
