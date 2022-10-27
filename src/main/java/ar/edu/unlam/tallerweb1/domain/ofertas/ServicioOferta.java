package ar.edu.unlam.tallerweb1.domain.ofertas;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiarioYaEfectuadoException;

import java.util.List;

public interface ServicioOferta {

   void agregarOferta(Oferta oferta);

   void enviarNotificaciones();

   List<Oferta> traerOfertasCargadas();

}
