package ar.edu.unlam.tallerweb1.domain.empleados;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;

import java.util.List;

public interface RepositorioEmpleado {
    Empleado buscarEmpleado(Empleado empleado);
    void agregarEmpleado( Empleado empleado);

    List<Empleado> traemeTodosLosEmpleados();


    //String buscarNombreDeEmpleadoPorId(int idEmpleado);
}
