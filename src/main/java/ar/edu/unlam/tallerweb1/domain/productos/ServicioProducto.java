package ar.edu.unlam.tallerweb1.domain.productos;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface ServicioProducto {
    boolean addProducto(Producto producto);

    boolean updateProductos(List<Producto> productos);

    List<Producto> buscarProductos();

    Producto buscarProductoPorID(Long ID);

    void importarSCV(MultipartFile file);

    InputStream exportarCSV();
}
