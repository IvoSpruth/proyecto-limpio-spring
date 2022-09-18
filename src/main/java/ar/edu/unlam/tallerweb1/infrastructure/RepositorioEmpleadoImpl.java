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

@Repository("repositorioEmpleado")
public class RepositorioEmpleadoImpl implements RepositorioEmpleado {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEmpleadoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Empleado buscarEmpleado(Empleado producto) {
        final Session session = sessionFactory.getCurrentSession();
        return (Empleado) session.createCriteria(Producto.class)
                .add(Restrictions.eq("id", producto.getId()))
                .uniqueResult();
    }

    @Override
    public void agregarEmpleado(Empleado empleado) {
        sessionFactory.getCurrentSession().save(empleado);
    }
}
