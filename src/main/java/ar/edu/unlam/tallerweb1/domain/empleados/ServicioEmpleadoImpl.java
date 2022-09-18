package ar.edu.unlam.tallerweb1.domain.empleados;

import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.RepositorioProducto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioEmpleadoImpl implements ServicioEmpleado {

    private RepositorioEmpleado empleadoDao;

    @Autowired
    public ServicioEmpleadoImpl(RepositorioEmpleado empleadoDao){
        this.empleadoDao = empleadoDao;
    }

    @Override
    @Transactional
    public boolean addEmpleado(Empleado empleado){
        try {
            Empleado empleadoExistente = empleadoDao.buscarEmpleado(empleado);
            if(empleadoExistente != null) {
                empleadoExistente.setName(empleado.getName());
                empleadoExistente.setRol(empleado.getRol());
                empleadoExistente.setSueldo(empleado.getSueldo());

                empleadoDao.agregarEmpleado(empleadoExistente);
            }else {
                empleadoDao.agregarEmpleado(empleado);
            }
            return true;
        }catch(Exception ex) {
            return false;
        }
    }


}
