package ar.edu.unlam.tallerweb1.domain.cierreDiario;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RepositorioCierreDiario {

   boolean guardarCierreDelDia(CierreDiario cd);

   List<CierreDiario> getHistorialCierreD();

   CierreDiario buscarCierrePorFecha(LocalDate fecha);
}
