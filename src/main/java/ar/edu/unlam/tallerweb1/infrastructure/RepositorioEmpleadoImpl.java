package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.RepositorioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.RepositorioProducto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("repositorioEmpleado")
public class RepositorioEmpleadoImpl implements RepositorioEmpleado {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEmpleadoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Empleado buscarEmpleado(Empleado empleado) {
        final Session session = sessionFactory.getCurrentSession();
        return (Empleado) session.createCriteria(Empleado.class)
                .add(Restrictions.eq("id", empleado.getId()))
                .uniqueResult();
    }

    @Override
    public void agregarEmpleado(Empleado empleado) {
        sessionFactory.getCurrentSession().save(empleado);
    }

    @Override
    public List<Empleado> traemeTodosLosEmpleados() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Empleado>) session.createCriteria(Empleado.class).list();
    }

/*    @Override
    public String buscarNombreDeEmpleadoPorId(int idEmpleado) {
        final Session session = sessionFactory.getCurrentSession();
        return (String) session.createCriteria(Empleado.class)
                .add(Restrictions.eq("id", idEmpleado))
                .uniqueResult();
    }*/


}
