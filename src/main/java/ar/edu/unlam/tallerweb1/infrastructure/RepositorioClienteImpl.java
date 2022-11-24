package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.cliente.Direccion;
import ar.edu.unlam.tallerweb1.domain.cliente.RepositorioCliente;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RepositorioClienteImpl implements RepositorioCliente {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioClienteImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Cliente buscarCliente(Cliente cliente) {
        final Session session = sessionFactory.getCurrentSession();
        return (Cliente) session.createCriteria(Cliente.class)
                .add(Restrictions.eq("id", cliente.getId()))
                .uniqueResult();
    }

    @Override
    public List<Cliente> buscarClientesSuscriptos() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Cliente>) session.createCriteria(Cliente.class)
                .add(Restrictions.eq("notifEnable", true)).list();
    }

    @Override
    public List<Cliente> buscarTodosLosClientes() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Cliente.class).list();
    }

    @Override
    public Cliente buscarCliente(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Cliente.class, id);
    }

    @Override
    public Direccion obtenerDireccion(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Direccion.class, id);
    }

    @Override
    public List<Direccion> obtenerDireccionCliente(Long idCliente) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Direccion.class);
        //cr.add(Restrictions.eq("cliente_id", idCliente));
        return cr.list();
    }
}
