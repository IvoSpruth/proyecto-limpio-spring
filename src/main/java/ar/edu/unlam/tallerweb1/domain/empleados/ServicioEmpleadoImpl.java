package ar.edu.unlam.tallerweb1.domain.empleados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Empleado buscarEmpleado(Empleado empleado) {
        return empleadoDao.buscarEmpleado(empleado);
    }

    @Override
    @Transactional
    public List<Empleado> listarEmpleados() {
        return empleadoDao.traemeTodosLosEmpleados();
    }

    @Override
    public String listaDeIdsDeTodosLosEmpleados() {
        String mensaje = "";
        List<Empleado> listaEmpleados = listarEmpleados();
        for (Empleado e: listaEmpleados) {
            mensaje +=" - " + e.getId() ;
        }
        return mensaje + ".";
    }
}
