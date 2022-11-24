package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Repository("repositorioCierres")
public class RepositorioCierresImpl implements RepositorioCierreDiario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCierresImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void guardarCierreDelDia(CierreDiario cd) {
        sessionFactory.getCurrentSession().save(cd);
    }

    @Override
    public List<CierreDiario> getHistorialCierreD() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<CierreDiario>) session.createCriteria(CierreDiario.class).list();
    }

    @Override
    public CierreDiario buscarCierrePorFecha(LocalDate fecha) {
        final Session session = sessionFactory.getCurrentSession();
        return (CierreDiario) session.createCriteria(CierreDiario.class)
                .add(Restrictions.eq("fecha", fecha))
                .uniqueResult();
    }
}
