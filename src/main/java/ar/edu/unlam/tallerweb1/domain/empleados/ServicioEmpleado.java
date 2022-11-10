package ar.edu.unlam.tallerweb1.domain.empleados;

import java.util.List;

public interface ServicioEmpleado {
    boolean addEmpleado(Empleado empleado);

    Empleado buscarEmpleado(Empleado empleado);

    List<Empleado> listarEmpleados();

    String listaDeIdsDeTodosLosEmpleados();

    /*String buscarNombreDeEmpleadoPorId(int idEmpleado);*/
}
