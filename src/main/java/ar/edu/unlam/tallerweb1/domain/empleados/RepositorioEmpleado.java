package ar.edu.unlam.tallerweb1.domain.empleados;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;

public interface RepositorioEmpleado {
    Empleado buscarEmpleado(Empleado producto);
    void agregarEmpleado( Empleado empleado);

}
