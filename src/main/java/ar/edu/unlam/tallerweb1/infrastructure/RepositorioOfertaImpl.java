package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.ofertas.Oferta;
import ar.edu.unlam.tallerweb1.domain.ofertas.RepositorioOferta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("repositorioCliente")
public class RepositorioOfertaImpl implements RepositorioOferta {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioOfertaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void agregarOferta(Oferta oferta) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(oferta);
    }

    @Override
    public List<Oferta> traerOfertas() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Oferta>) session.createCriteria(Oferta.class).list();
    }
}
