package ar.edu.unlam.tallerweb1.domain.productos;

public interface RepositorioProducto {
    Producto buscarProducto(Producto producto);
    void agregarProducto(Producto producto);

}
