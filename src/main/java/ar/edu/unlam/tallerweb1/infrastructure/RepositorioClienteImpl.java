package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.Cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.Cliente.RepositorioCliente;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.RepositorioCierreDiario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
        return (List<Cliente>) session.createCriteria(Cliente.class).list();
    }
}
