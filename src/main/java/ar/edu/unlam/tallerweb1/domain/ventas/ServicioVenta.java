package ar.edu.unlam.tallerweb1.domain.ventas;

import java.io.File;

public interface ServicioVenta {

    boolean addVenta(Venta venta) throws CantidadInsuficienteException, IdEmpleadoNoValidoException;

    boolean modifyVenta(Venta venta);

    boolean deleteVenta(Venta venta);

    Venta buscarVenta(Venta venta);

    File createFactura(Venta venta);
}
