package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.RepositorioProducto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioProducto")
public class RepositorioProductoImpl implements RepositorioProducto {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioProductoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Producto buscarProducto(Producto producto) {
        final Session session = sessionFactory.getCurrentSession();
        return (Producto) session.createCriteria(Producto.class)
                .add(Restrictions.eq("id", producto.getId()))
                .uniqueResult();
    }

    @Override
    public void agregarProducto(Producto producto) {
        sessionFactory.getCurrentSession().save(producto);
    }

    @Override
    public List<Producto> buscarTodosLosProductos() {
        final Session session = sessionFactory.getCurrentSession();
        return (List<Producto>) session.createCriteria(Producto.class).list();
    }

    @Override
    public void updateProducto(Producto producto) {
        sessionFactory.getCurrentSession().update(producto);
    }


}
