package ar.edu.unlam.tallerweb1.domain.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioVentaImpl  implements   ServicioVenta{

    private RepositorioVenta repositorioVenta;

    @Autowired
    public ServicioVentaImpl(RepositorioVenta repositorioVenta){
        this.repositorioVenta = repositorioVenta;
    }


    @Override
    @Transactional
    public boolean addVenta(Venta venta) {
        try {
            repositorioVenta.addVenta(venta);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean modifyVenta(Venta venta) {
        return false;
    }

    @Override
    public boolean deleteVenta(Venta venta) {
        return false;
    }

    @Override
    public Venta buscarVenta(Venta venta) {
        return null;
    }


}
