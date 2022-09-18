package ar.edu.unlam.tallerweb1.domain.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioProductoImpl implements ServicioProducto {

    private RepositorioProducto productoDao;

    @Autowired
    public ServicioProductoImpl(RepositorioProducto productoDao){
        this.productoDao = productoDao;
    }

    @Override
    @Transactional
    public boolean addProducto(Producto producto) {
        try {
            Producto productoExistente = productoDao.buscarProducto(producto);
            if(productoExistente != null) {
                productoExistente.setCantidad(productoExistente.getCantidad()+producto.getCantidad());
                productoExistente.setCosto(producto.getCosto());
                productoExistente.setNombre(producto.getNombre());
                productoDao.agregarProducto(productoExistente);
            }else {
                productoDao.agregarProducto(producto);
            }
            return true;
        }catch(Exception ex) {
            return false;
        }
    }

}
