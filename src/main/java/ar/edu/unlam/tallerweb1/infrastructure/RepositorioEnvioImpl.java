package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.envios.Envio;
import ar.edu.unlam.tallerweb1.domain.envios.RepositorioEnvio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("repositorioEnvio")
public class RepositorioEnvioImpl implements RepositorioEnvio {

    private SessionFactory sessionFactory;
    @Override
    public Envio guardarEnvio(Envio envio) {
        Session session = sessionFactory.getCurrentSession();
        session.save(envio);
        return envio;
    }
}
