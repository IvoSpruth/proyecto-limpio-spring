package ar.edu.unlam.tallerweb1.domain.cierreDiario;

import ar.edu.unlam.tallerweb1.domain.ventas.CantidadInsuficienteException;
import ar.edu.unlam.tallerweb1.domain.ventas.IdEmpleadoNoValidoException;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;

import java.io.File;
import java.util.List;

public interface ServicioCierreDiario {

   boolean ejecutarCierre() throws CierreDiarioYaEfectuadoException;

   List<CierreDiario> historialCierres();

   CierreDiario validarCierreDelDia();

}
