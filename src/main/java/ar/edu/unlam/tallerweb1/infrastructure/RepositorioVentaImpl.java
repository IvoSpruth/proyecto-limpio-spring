package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.ventas.RepositorioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
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

    public List<Venta> buscarVentaPorFecha(LocalDate fecha) {
        return sessionFactory.getCurrentSession().createCriteria(Venta.class)
                .add(Restrictions.eq("fecha", fecha)).list();
    }

    @Override
    public List<Venta> listarVentasPorEmpleadoYFechas(Integer idEmpleado, LocalDate fechaInicial, LocalDate fechaFinal) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Venta.class);
        cr.add(Restrictions.eq("idEmpleado", idEmpleado));
        if(fechaInicial != null && fechaFinal != null){
            cr.add(Restrictions.between("fecha", fechaInicial, fechaFinal));
        }
        else{
            if(fechaInicial != null){
                cr.add(Restrictions.ge("fecha", fechaInicial));
            }

            if(fechaFinal != null){
                cr.add(Restrictions.le("fecha", fechaFinal));
            }
        }

        return cr.list();
    }

    @Override
    public Venta buscarVenta(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Venta.class, id);
    }
}
