package ar.edu.unlam.tallerweb1.domain.ventas;

import java.io.File;
import java.util.List;

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

    double getSubtotalProducto1();

    double getSubtotalProducto2();

    double getSubtotalProductos();

    double calcularComisionEmpleado(double sumaTotal);

    //

}
