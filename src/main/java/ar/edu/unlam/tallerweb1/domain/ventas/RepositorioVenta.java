package ar.edu.unlam.tallerweb1.domain.ventas;

public interface RepositorioVenta {

    void addVenta(Venta venta);

    boolean modifyVenta(Venta venta);

    boolean deleteVenta(Venta venta);

    Venta buscarVenta(Venta venta);
}
