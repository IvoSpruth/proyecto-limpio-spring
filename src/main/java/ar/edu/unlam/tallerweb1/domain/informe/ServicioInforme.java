package ar.edu.unlam.tallerweb1.domain.informe;

import java.time.LocalDate;
import java.util.Map;

public interface ServicioInforme {
    Map<Long, Double> obtenerVentasPorEmpleadoYPorFecha(LocalDate fechaInicio, LocalDate fechaFinal);
}
