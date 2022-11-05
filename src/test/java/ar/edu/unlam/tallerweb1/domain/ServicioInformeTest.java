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
        Map<Long, Integer> ventasPorEmpleado = cuandoObtengoVentasPorEmpleado();
        entoncesObtengoElMapa(ventasPorEmpleado);
    }

    private Map<Long, Integer> cuandoObtengoVentasPorEmpleado() {
        return servicioInforme.obtenerVentasPorEmpleado();
    }

    private void dadoQueTengoEmpleados() { //para mockear los empleados
        Empleado e1 = new Empleado();
        e1.setId(1L);
        Empleado e2 = new Empleado();
        e2.setId(2L);

        List<Empleado> listaEmpleados = new ArrayList<>();
        listaEmpleados.add(e1);
        listaEmpleados.add(e2);

        when(servicioEmpleadoMock.listarEmpleados()).thenReturn(listaEmpleados);
    }

    private void dadoQueTengoVentas() { //para mockear las ventas
        Venta venta = new Venta();

        List<Venta> listaVentasEmp1 = new ArrayList<>();
        listaVentasEmp1.add(venta);
        listaVentasEmp1.add(venta);
        listaVentasEmp1.add(venta);
        listaVentasEmp1.add(venta);

        List<Venta> listaVentasEmp2 = new ArrayList<>();
        listaVentasEmp2.add(venta);
        listaVentasEmp2.add(venta);

        when(servicioVentaMock.listarPorEmpleado(1L)).thenReturn(listaVentasEmp1);
        when(servicioVentaMock.listarPorEmpleado(2L)).thenReturn(listaVentasEmp2);
    }

    private void entoncesObtengoElMapa(Map<Long, Integer> ventasPorEmpleado) {
        assertThat(ventasPorEmpleado.get(1L)).isEqualTo(4);
        assertThat(ventasPorEmpleado.get(2L)).isEqualTo(2);
    }
}
