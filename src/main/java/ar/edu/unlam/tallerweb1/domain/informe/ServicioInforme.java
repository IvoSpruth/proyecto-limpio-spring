package ar.edu.unlam.tallerweb1.domain.informe;

import ar.edu.unlam.tallerweb1.delivery.DataChart;

import java.time.LocalDate;
import java.util.List;

public interface ServicioInforme {
    List<DataChart<Double>> obtenerVentasPorEmpleadoYPorFecha(LocalDate fechaInicio, LocalDate fechaFinal);

    List<DataChart<Integer>> obtenerVentasPorHoraSegunDia(LocalDate fechaFiltro);

    String generarTituloChart(LocalDate fechaInicial, LocalDate fechaFinal);
}
