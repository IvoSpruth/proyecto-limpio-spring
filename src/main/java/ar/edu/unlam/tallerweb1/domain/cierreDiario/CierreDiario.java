package ar.edu.unlam.tallerweb1.domain.cierreDiario;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class CierreDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(mappedBy = "cierre", cascade = CascadeType.ALL)
    List<Venta> ventas;

    Long idEmpleado;


    @Column(name= "fecha", nullable = false, updatable = false, unique = true)
    private LocalDate fecha;

    private double total;

    private boolean cerrado;

    private String productoMasVendido;
    private int cantProdsTotalVendida;
    private String cantidadDeProductosVendida;

    private double totalGanancia;

    private int cantProductos;

    public CierreDiario(){
        ventas = new ArrayList<Venta>();
        cerrado = false;
        fecha = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public double getTotalGanancia() {
        return totalGanancia;
    }

    public void setTotalGanancia(double totalGanancia) {
        this.totalGanancia = totalGanancia;
    }

    public String getProductoMasVendido() {
        return productoMasVendido;
    }

    public void setProductoMasVendido(String productoMasVendido) {
        this.productoMasVendido = productoMasVendido;
    }

    public int getCantProdsTotalVendida() {
        return cantProdsTotalVendida;
    }

    public void setCantProdsTotalVendida(int cantProdsTotalVendida) {
        this.cantProdsTotalVendida = cantProdsTotalVendida;
    }

    public String getCantidadDeProductosVendida() {
        return cantidadDeProductosVendida;
    }

    public void setCantidadDeProductosVendida(String cantidadDeProductosVendida) {
        this.cantidadDeProductosVendida = cantidadDeProductosVendida;
    }

    public int getCantProductos() {
        return cantProductos;
    }

    public void setCantProductos(int cantProductos) {
        this.cantProductos = cantProductos;
    }
}
