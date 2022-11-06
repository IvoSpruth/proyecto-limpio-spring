package ar.edu.unlam.tallerweb1.domain.productos;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean updateProductos(List<Producto> productos) {
        for(Producto p: productos){
            productoDao.updateProducto(p);
        }
        return true;
    }

    @Override
    @Transactional
    public List<Producto> buscarProductos() {
        return productoDao.buscarTodosLosProductos();
    }

    @Override
    public Producto buscarProductoPorID(Long ID) {
        return productoDao.buscarProductoPorID(ID);
    }

    public void restarProductos(Venta v){
        ArrayList<Producto> stockProductos = (ArrayList)productoDao.buscarTodosLosProductos();
        for(Producto p : stockProductos){
            if(p.getId()==v.getIdProducto()){

            }
            if(p.getId()==v.getIdProducto2()){

            }
        }
    }

}
