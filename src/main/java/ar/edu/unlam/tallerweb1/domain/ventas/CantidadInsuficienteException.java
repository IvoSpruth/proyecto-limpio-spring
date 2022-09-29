package ar.edu.unlam.tallerweb1.domain.ventas;

public class CantidadInsuficienteException extends Exception {
    public CantidadInsuficienteException(String nombreProd, int cantPedida, int cantStock){
        super("Se pide " + cantPedida + " para el pruducto " + nombreProd + " pero solo tenenos " + cantStock);
    }

    public CantidadInsuficienteException(String mensaje){
        super(mensaje);
    }
}
