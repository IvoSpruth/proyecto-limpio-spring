package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.delivery.DataChart;
import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.informe.ServicioInforme;
import ar.edu.unlam.tallerweb1.domain.informe.ServicioInformeImpl;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class ServicioInformeTest{

    private ServicioInforme servicioInforme;

    @Mock
    private ServicioEmpleado servicioEmpleadoMock;

    @Mock
    private ServicioVenta servicioVentaMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.servicioInforme = new ServicioInformeImpl(servicioEmpleadoMock, servicioVentaMock);
    }

    @Test
    public void cuandoPidoListarVentasPorEmpleadoReciboElObjetoCorrespondiente(){
        dadoQueTengoEmpleados();
        dadoQueTengoVentasConTotal();

        List<DataChart<Double>> ventasPorEmpleado = cuandoObtengoVentasPorEmpleadoYFecha(null, null);

        entoncesObtengoLosDatosCorrectos(ventasPorEmpleado);
    }

    @Test
    public void cuandoPidoListarVentasPorHoraSegunFechaReciboElObjetoCorrespondiente(){
        dadoQueTengoVentasConHora();
        LocalDate fechaFiltro = LocalDate.now();

        List<DataChart<Integer>> ventasPorHora = cuandoObtengoVentasPorHoraSegunFecha(fechaFiltro);

        entoncesObtengoElObjetoCorrespondiente(ventasPorHora);
    }

    private void entoncesObtengoLosDatosCorrectos(List<DataChart<Double>> ventasPorEmpleado) {
        Double total1 = ventasPorEmpleado.get(0).getData().get(0);
        Double total2 = ventasPorEmpleado.get(1).getData().get(0);

        assertThat(total1).isEqualTo(181.12);
        assertThat(total2).isEqualTo(415.58);
    }

    private void entoncesObtengoElObjetoCorrespondiente(List<DataChart<Integer>> ventasPorHora) {
        Integer cantidadHora1 = ventasPorHora.get(0).getData().get(0);
        Integer cantidadHora2 = ventasPorHora.get(1).getData().get(0);
        String hora1 = ventasPorHora.get(0).getName();
        String hora2 = ventasPorHora.get(1).getName();

        assertThat(hora1).isEqualTo("10");
        assertThat(hora2).isEqualTo("11");
        assertThat(cantidadHora1).isEqualTo(1);
        assertThat(cantidadHora2).isEqualTo(1);
    }

    private List<DataChart<Integer>> cuandoObtengoVentasPorHoraSegunFecha(LocalDate fechaFiltro) {
        return servicioInforme.obtenerVentasPorHoraSegunDia(fechaFiltro);
    }

    private List<DataChart<Double>> cuandoObtengoVentasPorEmpleadoYFecha(LocalDate fechaInicio, LocalDate fechaFinal) {
        return servicioInforme.obtenerVentasPorEmpleadoYPorFecha(fechaInicio, fechaFinal);
    }

    private void dadoQueTengoEmpleados() {
        Empleado e1 = new Empleado();
        e1.setId(1L);
        Empleado e2 = new Empleado();
        e2.setId(2L);

        List<Empleado> listaEmpleados = new ArrayList<>();
        listaEmpleados.add(e1);
        listaEmpleados.add(e2);

        when(servicioEmpleadoMock.listarEmpleados()).thenReturn(listaEmpleados);
    }

    private void dadoQueTengoVentasConTotal() {
        List<Venta> listaVentasEmp1 = new ArrayList<>();
        agregarVentaALista(listaVentasEmp1, 60.67D);
        agregarVentaALista(listaVentasEmp1, 120.45D);

        List<Venta> listaVentasEmp2 = new ArrayList<>();
        agregarVentaALista(listaVentasEmp2, 390.28D);
        agregarVentaALista(listaVentasEmp2, 25.3D);

        when(servicioVentaMock.listarPorEmpleadoYPorFecha(1L, null, null)).thenReturn(listaVentasEmp1);
        when(servicioVentaMock.listarPorEmpleadoYPorFecha(2L, null, null)).thenReturn(listaVentasEmp2);

    }

    private void dadoQueTengoVentasConHora() {
        List<Venta> listaVentas = new ArrayList<>();
        agregarVentaALista(listaVentas, LocalTime.of(10,30));
        agregarVentaALista(listaVentas, LocalTime.of(11,30));

        when(servicioVentaMock.buscarVentasPorFecha(any())).thenReturn(listaVentas);
    }
    private void agregarVentaALista(List<Venta> lista, Double total){
        Venta venta = new Venta();
        venta.setTotal(total);
        lista.add(venta);
    }

    private void agregarVentaALista(List<Venta> lista, LocalTime hora){
        Venta venta = new Venta();
        venta.setHora(hora);
        lista.add(venta);
    }
}
