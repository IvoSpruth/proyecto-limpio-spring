package ar.edu.unlam.tallerweb1.domain.ventas;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.envios.Envio;
import org.hibernate.annotations.*;

import java.time.LocalTime;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.*;

@Entity(name = "Venta")
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private int idEmpleado;

    @ManyToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @NotFound(action = NotFoundAction.IGNORE)
    private CierreDiario cierre;

    @CreationTimestamp
    @Column(name= "fecha", nullable = false)
    private LocalDate fecha;

    private LocalTime hora;

    private double total;


    public Venta(){

    }

    @OneToMany(
            mappedBy = "venta",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<VentaProducto> productos = new ArrayList<>();


    @OneToMany(mappedBy = "venta")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Envio> envios;

    private String pathFactura;

    public String getPathFactura() {
        return pathFactura;
    }

    public void setPathFactura(String pathFactura) {
        this.pathFactura = pathFactura;
    }

    public Long getId() {
        return id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<VentaProducto> getVentaProductos() {
        return productos;
    }

    public void setProductos(List<VentaProducto> productos) {
        this.productos = productos;
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

    public CierreDiario getCierre() {
        return cierre;
    }

    public void setCierre(CierreDiario cierre) {
        this.cierre = cierre;
    }

    public void addProducto(Producto producto) {
        VentaProducto ventaProducto = new VentaProducto(this, producto, producto.getCantidad());
        productos.add(ventaProducto);
    }

    public List<Producto> getProductos(){
        List<Producto> productosV = new ArrayList<>();
        for(VentaProducto vp : this.productos){
            vp.getProducto().setCantidad(vp.getCantidad());
            productosV.add(vp.getProducto());
        }
        return productosV;
    }

    public void borrarProducto(Producto producto) {
        for (Iterator<VentaProducto> iterator = productos.iterator();
             iterator.hasNext(); ) {
            VentaProducto ventaProducto = iterator.next();

            if (ventaProducto.getVenta().equals(this) &&
                    ventaProducto.getProducto().equals(producto)) {
                iterator.remove();
                ventaProducto.setVenta(null);
                ventaProducto.setProducto(null);
            }
        }
    }

//    public ModelMap getModelMapProductos(){
//        ModelMap productos = new ModelMap();
//        for(Producto p : this.productos){
//            productos.addAttribute(p, p.getCosto()*p.getCantidad());
//        }
//        return
//    }
    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void agregarEnvio(Envio envio) {
        this.total = this.total + envio.getCosto();
        this.envios.add(envio);
    }

    public List<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(List<Envio> envios) {
        this.envios = envios;
    }
}
