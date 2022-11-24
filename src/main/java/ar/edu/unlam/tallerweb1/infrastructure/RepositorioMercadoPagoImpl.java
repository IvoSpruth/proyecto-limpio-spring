package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cobros.MercadoPago;
import ar.edu.unlam.tallerweb1.domain.cobros.RepositorioMercadoPago;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("repositorioMercadoPago")
public class RepositorioMercadoPagoImpl implements RepositorioMercadoPago {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMercadoPagoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(MercadoPago preferencia) {
        sessionFactory.getCurrentSession().save(preferencia);
    }

    @Override
    public MercadoPago obtener(MercadoPago preferencia) {
        Session session = sessionFactory.getCurrentSession();
        return (MercadoPago) session.createCriteria(MercadoPago.class)
                .add(Restrictions.eq("id", preferencia.getId()))
                .uniqueResult();
    }

    @Override
    public MercadoPago obtener(Venta venta) {
        Session session = sessionFactory.getCurrentSession();
        return (MercadoPago) session.createCriteria(MercadoPago.class)
                .add(Restrictions.eq("venta", venta))
                .uniqueResult();
    }

    @Override
    public MercadoPago obtener(String preference_id) {
        Session session = sessionFactory.getCurrentSession();
        return (MercadoPago) session.createCriteria(MercadoPago.class)
                .add(Restrictions.eq("id_preferencia", preference_id))
                .uniqueResult();
    }

    @Override
    public void actualizar(MercadoPago preferencia) {
        sessionFactory.getCurrentSession().update(preferencia);
    }

    @Override
    public List<MercadoPago> obtenerLinksSegunVentas(List<Venta> ventas) {
        Session session = sessionFactory.getCurrentSession();
        if(ventas.size() == 0){
            return null;
        }

        return (List<MercadoPago>) session.createCriteria(MercadoPago.class)
                .add(Restrictions.in("venta",ventas))
                .list();
    }
}
