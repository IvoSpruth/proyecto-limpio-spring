package ar.edu.unlam.tallerweb1.domain.ventas;

public interface ServicioVenta {

    boolean addVenta(Venta venta);

    boolean modifyVenta(Venta venta);

    boolean deleteVenta(Venta venta);

    Venta buscarVenta(Venta venta);
}
