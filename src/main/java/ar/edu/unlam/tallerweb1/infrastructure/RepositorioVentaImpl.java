package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.ventas.RepositorioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioVenta")
public class RepositorioVentaImpl implements RepositorioVenta {


    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioVentaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addVenta(Venta venta) {
        final Session session = sessionFactory.getCurrentSession();

        session.save(venta);
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

    @Override
    public List<Venta> ventasDeUnEmpleado(int idEmpleado) {
        return sessionFactory.getCurrentSession().createCriteria(Venta.class)
                .add(Restrictions.eq("idEmpleado", idEmpleado)).list();
    }

    @Override
    public List<Venta> buscarTodasLasVentas() {
        return sessionFactory.getCurrentSession().createCriteria(Venta.class).list();
    }
}
