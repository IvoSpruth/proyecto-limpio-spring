package ar.edu.unlam.tallerweb1.domain.productos;

import java.util.List;

public interface ServicioProducto {
    boolean addProducto(Producto producto);

    List<Producto> buscarProductos();
}
