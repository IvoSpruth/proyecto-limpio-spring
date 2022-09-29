package ar.edu.unlam.tallerweb1.domain.empleados;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;

import java.util.List;

public interface ServicioEmpleado {
    boolean addEmpleado(Empleado empleado);

    Empleado buscarEmpleado(Empleado empleado);

    List<Empleado> traemeTodosLosEmpleados();

    String listaDeIdsDeTodosLosEmpleados();
}
