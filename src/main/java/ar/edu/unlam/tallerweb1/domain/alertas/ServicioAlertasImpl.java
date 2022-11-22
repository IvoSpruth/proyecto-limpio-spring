package ar.edu.unlam.tallerweb1.domain.alertas;

import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.RepositorioProducto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service("servicioAlertas")
@Transactional
public class ServicioAlertasImpl implements ServicioAlertas {
    private Gson gson = new Gson();
    private RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioAlertasImpl(RepositorioProducto repositorioProducto){
        this.repositorioProducto = repositorioProducto;
    }

    public String obtenerAlertasInventario(){

        List<Producto> productos = repositorioProducto.buscarTodosLosProductos();
        List<Alerta> alertas = new ArrayList<>();

        for (var producto : productos){
            if(!producto.getEstado().equals("OK")){
                alertas.add(new Alerta(producto.getEstado()));
            }
        }

        Type tipoListaAlertas = new TypeToken<List<Alerta>>(){}.getType();
        String json = gson.toJson(alertas, tipoListaAlertas);

        return json;
    }

    public List<Alerta> obtenerAlertasCaja(){
        return null;
    }

}
