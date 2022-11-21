package ar.edu.unlam.tallerweb1.domain.alertas;

import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("servicioAlertas")
@Transactional
public class ServicioAlertasImpl implements ServicioAlertas {

    private RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioAlertasImpl(RepositorioProducto repositorioProducto){
        this.repositorioProducto = repositorioProducto;
    }

    public List<Alerta> obtenerAlertasInventario(){

        List<Producto> productos = repositorioProducto.buscarTodosLosProductos();
        List<Alerta> alertas = new ArrayList<>();

        for (var producto : productos){
            if(!producto.getEstado().equals("OK")){
                alertas.add(new Alerta(producto.getEstado()));
            }
        }

        return alertas;
    }

    public List<Alerta> obtenerAlertasCaja(){
        return null;
    }

}
