package ar.edu.unlam.tallerweb1.domain.envios;

import ar.edu.unlam.tallerweb1.delivery.forms.FormEnvio;

import java.util.List;

public interface ServicioEnvio {
    Envio concretarEnvio(FormEnvio formEnvio);

    void siguienteEtapaEnvio(Long id);

    void anteriorEtapaEnvio(Long id);

    List<Envio> obtenerEnvios();

    void devolverEnvio(Long idEnvio);
}
