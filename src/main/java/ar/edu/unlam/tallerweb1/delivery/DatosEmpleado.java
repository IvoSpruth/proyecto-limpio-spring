package ar.edu.unlam.tallerweb1.delivery;

import java.io.Serializable;
import java.util.List;

public class DatosEmpleado{
    private Integer id;
    private String nombre;
    private List<String> productos;

    public DatosEmpleado(Integer id, String nombre, List<String> productos){
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
    }
}
