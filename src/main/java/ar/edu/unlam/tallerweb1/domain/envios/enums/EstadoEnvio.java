package ar.edu.unlam.tallerweb1.domain.envios.enums;

public enum EstadoEnvio {
    EN_PREPARACION("En preparacion"),
    EN_CAMINO("En camino"),
    ENTREGADO("Entregado"),
    DEVUELTO("Devuelto");

    private String nombreDisplay;

    EstadoEnvio(String nombreDisplay) {
        this.nombreDisplay = nombreDisplay;
    }

    @Override
    public String toString(){
        return this.nombreDisplay;
    }
}
