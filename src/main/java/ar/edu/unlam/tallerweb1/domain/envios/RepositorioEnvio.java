package ar.edu.unlam.tallerweb1.domain.envios;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;

import java.util.List;

public interface RepositorioEnvio {
    Envio guardarEnvio(Envio envio);

    Envio obtenerEnvio(Long id);

    void actualizarEnvio(Envio envio);

    List<Envio> obtenerTodosLosEnvios();

    List<Envio> obtenerEnviosValidos(Venta venta);
}
