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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Rollback
public class RepositorioVentaTest extends SpringTest {

    @Autowired
    private RepositorioVenta repositorioVenta;

    @Test
    public void cuandoBuscoVentaPorFechaMeTraeCorrectas(){
        dadoQueExisteUnaVenta(LocalDate.now(), 1);
        dadoQueExisteUnaVenta(LocalDate.now(), 2);

        List<Venta> todasLasVentasBuscadas = cuandoBuscoVentaPorFecha(LocalDate.now());

        entoncesMeTrae(todasLasVentasBuscadas, 2);
    }

    private List<Venta> cuandoBuscoVentaPorFecha(LocalDate fechaFiltro) {
        return this.repositorioVenta.buscarVentaPorFecha(fechaFiltro);
    }

    private List<Venta> cuandoBuscoTodasLasVentas() {
        return this.repositorioVenta.buscarTodasLasVentas();
    }

    private void entoncesMeTrae(List<Venta> ventasBuscadas, Integer ventasEsperadas) {
        assertThat(ventasBuscadas).hasSize(ventasEsperadas);
    }

    private List<Venta> cuandoBuscoUnaVentaPorIdDelEmpleado(int idEmpleado) {
        return repositorioVenta.ventasDeUnEmpleado(idEmpleado);
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
        dadoQueExisteUnaVenta(LocalDate.now(), 1);
        dadoQueExisteUnaVenta(LocalDate.now(), 2);

        List<Venta> listaRecibida = cuandoListoLasVentas(1, null, null);

        entoncesObtengoVentasCorrectas(listaRecibida, 1);
    }
    @Test
    public void cuandoListoVentasPorEmpleadoDesdeDeUnaFechaObtengoVentasCorrectas(){
        LocalDate fechaInicio = dadoQueTengoUnaFecha(2020, 1, 1);

        dadoQueExisteUnaVenta(LocalDate.of(2020,10,27), 1);
        dadoQueExisteUnaVenta(LocalDate.of(2020,11,27), 1);
        dadoQueExisteUnaVenta(LocalDate.of(2020,12,27), 1);

        dadoQueExisteUnaVenta(LocalDate.of(2019,10,27), 1);
        dadoQueExisteUnaVenta(LocalDate.of(2020,10,27), 2);

        List<Venta> listaRecibida = cuandoListoLasVentas(1, fechaInicio, null);

        entoncesObtengoVentasCorrectas(listaRecibida, 3);
    }
    @Test
    public void cuandoListoVentasPorEmpleadoHastaUnaFechaObtengoVentasCorrectas(){
        LocalDate fechaFinal = dadoQueTengoUnaFecha(2020, 12, 30);

        dadoQueExisteUnaVenta(LocalDate.of(2020,10,27), 1);
        dadoQueExisteUnaVenta(LocalDate.of(2020,11,27), 1);
        dadoQueExisteUnaVenta(LocalDate.of(2020,12,27), 1);

        dadoQueExisteUnaVenta(LocalDate.of(2022,10,27), 1);
        dadoQueExisteUnaVenta(LocalDate.of(2020,10,27), 2);

        List<Venta> listaRecibida = cuandoListoLasVentas(1, null, fechaFinal);

        entoncesObtengoVentasCorrectas(listaRecibida, 3);
    }
    //TODO: arreglar test, con los datos precargados funcionaba y ahora no
    @Test
    public void cuandoListoVentasPorEmpleadoEntreFechasObtengoVentasCorrectas(){
        LocalDate fechaInicial = dadoQueTengoUnaFecha(2020, 10, 28);
        LocalDate fechaFinal = dadoQueTengoUnaFecha(2020, 11, 28);

        dadoQueExisteUnaVenta(LocalDate.of(2020,10,27), 1);
        dadoQueExisteUnaVenta(LocalDate.of(2020,10,27), 1);
        dadoQueExisteUnaVenta(LocalDate.of(2020,10,27), 1);

        dadoQueExisteUnaVenta(LocalDate.of(2019,10,27), 1);
        dadoQueExisteUnaVenta(LocalDate.of(2021,10,27), 1);
        dadoQueExisteUnaVenta(LocalDate.of(2020,10,27), 3);

        List<Venta> listaRecibida = cuandoListoLasVentas(1, fechaInicial, fechaFinal);

        entoncesObtengoVentasCorrectas(listaRecibida, 3);
    }

    private Venta dadoQueExisteUnaVenta(LocalDate fecha, Integer idEmpleado) {
        Venta venta = new Venta();
        venta.setFecha(fecha);
        venta.setIdEmpleado(idEmpleado);
        session().save(venta);
        return venta;
    }

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
