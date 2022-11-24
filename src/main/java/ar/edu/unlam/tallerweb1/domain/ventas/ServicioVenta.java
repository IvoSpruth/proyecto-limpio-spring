package ar.edu.unlam.tallerweb1.domain.ventas;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ServicioVenta {

    boolean addVenta(Venta venta) throws CantidadInsuficienteException, IdEmpleadoNoValidoException;

    boolean modifyVenta(Venta venta);

    boolean deleteVenta(Venta venta);

    Venta buscarVenta(Venta venta);

    File createFactura(Venta venta);
    
    String buscarNombreEmpleado(int idEmpleado);

    String buscarNombreProducto(int idProducto);

    double buscarCostoProducto(int idProducto);

    double fillTotal(Venta venta);

    //Map<String, Integer> getSubtotalProductos();

    double calcularComisionEmpleado(double sumaTotal);

    List<Venta> buscarTodasLasVentas();

    List<Venta> buscarVentasPorFecha(LocalDate fecha);

    List<Venta> listarPorEmpleadoYPorFecha(Long idEmpleado, LocalDate fechaInicial, LocalDate fechaFinal);

    Venta buscarVenta(Long idVenta);
}
