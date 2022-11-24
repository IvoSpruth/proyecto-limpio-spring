package ar.edu.unlam.tallerweb1.domain.cierreDiario.delivery.forms;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;

public class FormEnvio {

    private Long idCliente;

    private Long idDireccion;

    private Long idVenta;

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }


    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }
}
