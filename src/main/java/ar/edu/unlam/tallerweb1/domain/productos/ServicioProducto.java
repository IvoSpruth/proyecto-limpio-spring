package ar.edu.unlam.tallerweb1.domain.productos;

import java.util.List;

public interface ServicioProducto {
    boolean addProducto(Producto producto);

    boolean updateProductos(List<Producto> productos);

    List<Producto> buscarProductos();


}
