package ar.edu.unlam.tallerweb1.domain.ofertas;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioOferta {

   void agregarOferta(Oferta oferta);

   List<Oferta> traerOfertas();

}
