package ar.edu.unlam.tallerweb1.domain.envios;

import ar.edu.unlam.tallerweb1.domain.cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.cliente.Direccion;
import ar.edu.unlam.tallerweb1.domain.envios.enums.EstadoEnvio;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "envio")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //owning side of the relationship
    @JoinColumn //foreign key column of envio table referencing cliente table
    private Cliente cliente; //cliente_id

    @ManyToOne //owning side of the relationship
    @JoinColumn //venta_id
    private Venta venta;

    @ManyToOne //owning side of the relationship
    @JoinColumn //direccionEnvio_id
    private Direccion direccionEnvio;

    private LocalDateTime fechaSalida;

    private LocalDateTime fechaLlegada;

    private Double costo;

    @Enumerated
    private EstadoEnvio estadoEnvio = EstadoEnvio.EN_PREPARACION;

    public Envio(){}

    public Envio(Cliente cliente, Direccion direccion, Venta venta){
        this.cliente = cliente;
        this.direccionEnvio = direccion;
        this.venta = venta;
        this.costo = calcularCosto(this.venta.getTotal());
        this.setEstadoEnvio(EstadoEnvio.EN_PREPARACION);
        this.setFechaSalida(LocalDateTime.now());
        this.setFechaLlegada(LocalDateTime.now().plusDays(2));
    }

    private Double calcularCosto(double total) {
        if(total < 1000){
            return 200D;
        }
        else{
            return total * 0.2;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Direccion getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(Direccion direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public EstadoEnvio getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public String getFechaSalida(String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(this.fechaSalida);
    }

    public String getFechaLlegada(String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(this.fechaLlegada);
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }
}
