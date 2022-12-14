package ar.edu.unlam.tallerweb1.domain.ventas;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioVenta {

    void addVenta(Venta venta);

    boolean modifyVenta(Venta venta);

    boolean deleteVenta(Venta venta);

    Venta buscarVenta(Venta venta);

    List<Venta> ventasDeUnEmpleado(int idEmpleado);

    List<Venta> buscarTodasLasVentas();

    List<Venta> buscarVentaPorFecha(LocalDate fecha);

    List<Venta> listarVentasPorEmpleadoYFechas(Integer idEmpleado, LocalDate fechaInicial, LocalDate fechaFinal);

    Venta buscarVenta(Long id);

    void actualizarVenta(Venta venta);
}
