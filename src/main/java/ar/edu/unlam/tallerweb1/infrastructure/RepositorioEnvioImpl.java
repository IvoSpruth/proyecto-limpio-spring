package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.envios.Envio;
import ar.edu.unlam.tallerweb1.domain.envios.RepositorioEnvio;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioEnvio")
public class RepositorioEnvioImpl implements RepositorioEnvio {

    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioEnvioImpl(SessionFactory sessionFactory){
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
}
