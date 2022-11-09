package ar.edu.unlam.tallerweb1.domain.informe;

import ar.edu.unlam.tallerweb1.delivery.DataEmpleado;

import java.time.LocalDate;
import java.util.List;

public interface ServicioInforme {
    List<DataEmpleado> obtenerVentasPorEmpleadoYPorFecha(LocalDate fechaInicio, LocalDate fechaFinal);
}
