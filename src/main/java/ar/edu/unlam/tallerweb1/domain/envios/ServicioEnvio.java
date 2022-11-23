package ar.edu.unlam.tallerweb1.domain.envios;

import ar.edu.unlam.tallerweb1.delivery.forms.FormEnvio;

public interface ServicioEnvio {
    Envio concretarEnvio(FormEnvio formEnvio);
}
