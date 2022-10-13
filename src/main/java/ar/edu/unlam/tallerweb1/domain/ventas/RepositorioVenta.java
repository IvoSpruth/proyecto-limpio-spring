package ar.edu.unlam.tallerweb1.domain.ventas;

import java.util.List;

public interface RepositorioVenta {

    void addVenta(Venta venta);

    boolean modifyVenta(Venta venta);

    boolean deleteVenta(Venta venta);

    Venta buscarVenta(Venta venta);

    List<Venta> ventasDeUnEmpleado(int idEmpleado);

    List<Venta> buscarTodasLasVentas();
}
