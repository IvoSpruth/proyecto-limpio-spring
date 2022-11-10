package ar.edu.unlam.tallerweb1.domain.productos;

import java.util.List;

public interface RepositorioProducto {
    Producto buscarProducto(Producto producto);
    void agregarProducto(Producto producto);

    List<Producto> buscarTodosLosProductos();

    void updateProducto(Producto producto);

    Producto buscarProductoPorID(Long ID);
}
