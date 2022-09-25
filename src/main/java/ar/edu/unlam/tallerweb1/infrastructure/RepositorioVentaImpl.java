package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.ventas.RepositorioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioVenta")
public class RepositorioVentaImpl implements RepositorioVenta {


    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioVentaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addVenta(Venta venta) {
        final Session session = sessionFactory.getCurrentSession();

        return (boolean) session.save(venta);
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
