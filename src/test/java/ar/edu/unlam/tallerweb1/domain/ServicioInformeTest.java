package ar.edu.unlam.tallerweb1.domain;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void cuandoPidoListarVentasPorEmpleadoEntoncesReciboElMapaCorrespondiente(){
        dadoQueTengoEmpleados();
        dadoQueTengoVentas();
        Map<Long, Double> ventasPorEmpleado = cuandoObtengoVentasPorEmpleadoYFecha(null, null);
        entoncesObtengoElMapa(ventasPorEmpleado);
    }

    private Map<Long, Double> cuandoObtengoVentasPorEmpleadoYFecha(LocalDate fechaInicio, LocalDate fechaFinal) {
        return (Map<Long, Double>) servicioInforme.obtenerVentasPorEmpleadoYPorFecha(fechaInicio, fechaFinal);
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

    private void dadoQueTengoVentas() {
        List<Venta> listaVentasEmp1 = new ArrayList<>();
        agregarVentaConTotalALista(listaVentasEmp1, 60.67D);
        agregarVentaConTotalALista(listaVentasEmp1, 120.45D);

        List<Venta> listaVentasEmp2 = new ArrayList<>();
        agregarVentaConTotalALista(listaVentasEmp2, 390.28D);
        agregarVentaConTotalALista(listaVentasEmp2, 25.3D);

        when(servicioVentaMock.listarPorEmpleadoYPorFecha(1L, null, null)).thenReturn(listaVentasEmp1);
        when(servicioVentaMock.listarPorEmpleadoYPorFecha(2L, null, null)).thenReturn(listaVentasEmp2);
    }

    private void agregarVentaConTotalALista(List<Venta> lista, Double total) {
        Venta venta = new Venta();
        venta.setTotal(total);
        lista.add(venta);
    }

    private void entoncesObtengoElMapa(Map<Long, Double> ventasPorEmpleado) {
        assertThat(ventasPorEmpleado.get(1L)).isEqualTo(181.12);
        assertThat(ventasPorEmpleado.get(2L)).isEqualTo(415.58);
    }
}
