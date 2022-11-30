package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.envios.Envio;
import ar.edu.unlam.tallerweb1.domain.envios.RepositorioEnvio;
import ar.edu.unlam.tallerweb1.domain.envios.enums.EstadoEnvio;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioEnvio")
public class RepositorioEnvioImpl implements RepositorioEnvio {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEnvioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Envio guardarEnvio(Envio envio) {
        Session session = sessionFactory.getCurrentSession();
        session.save(envio);
        return envio;
    }

    @Override
    public Envio obtenerEnvio(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Envio.class, id);
    }

    @Override
    public void actualizarEnvio(Envio envio) {
        Session session = sessionFactory.getCurrentSession();
        session.update(envio);
    }

    @Override
    public List<Envio> obtenerTodosLosEnvios() {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Envio.class);
        return cr.list();
    }

    @Override
    public List<Envio> obtenerEnviosValidos(Venta venta) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Envio.class);
        cr.add(Restrictions.or(
                    Restrictions.eq("estadoEnvio", EstadoEnvio.EN_PREPARACION),
                    Restrictions.eq("estadoEnvio", EstadoEnvio.EN_CAMINO),
                    Restrictions.eq("estadoEnvio", EstadoEnvio.ENTREGADO)
                )
        );

        cr.add(Restrictions.eq("venta", venta));

        List criteriaList = cr.list();

        if(criteriaList.size() == 0){
            return null;
        }

        return criteriaList;
    }

    @Override
    public List<Envio> obtenerEnviosDevueltos(Venta venta) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Envio.class);
        cr.add(Restrictions.or(
                        Restrictions.eq("estadoEnvio", EstadoEnvio.DEVUELTO)
                )
        );

        cr.add(Restrictions.eq("venta", venta));

        List criteriaList = cr.list();

        if(criteriaList.size() == 0){
            return null;
        }

        return criteriaList;
    }
}
