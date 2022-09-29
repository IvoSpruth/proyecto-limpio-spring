package ar.edu.unlam.tallerweb1.domain.ventas;

public class IdEmpleadoNoValidoException extends Exception {

    public IdEmpleadoNoValidoException(int idEmpleado){
        super ("El id ingresado : "+ idEmpleado +" no corresponde al de ningun empleado registrado.");
    }

    public IdEmpleadoNoValidoException(String mensaje){
        super(mensaje);
    }


}
