package ar.edu.unlam.tallerweb1.domain.envios;

import ar.edu.unlam.tallerweb1.delivery.forms.FormEnvio;

import java.util.List;

public interface ServicioEnvio {
    Envio concretarEnvio(FormEnvio formEnvio);

    void cambiarEstadoEnvio(Long id);

    List<Envio> obtenerEnvios();
}
