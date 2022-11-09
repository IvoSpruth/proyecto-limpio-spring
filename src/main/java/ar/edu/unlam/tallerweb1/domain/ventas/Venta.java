package ar.edu.unlam.tallerweb1.domain.ventas;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.ui.ModelMap;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private int idEmpleado;


//    @JoinTable(name = "ventaProducto",
//            joinColumns = @JoinColumn(name = "venta_id"),
//            inverseJoinColumns = @JoinColumn(name = "producto_id"))
//
//    private List<Producto> productos;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private CierreDiario cierre;

    @CreationTimestamp
    @Column(name= "fecha", nullable = false)
    private LocalDate fecha;

    private double total;


//    @ManyToMany
//    @JoinTable(name = "Venta_producto",
//            joinColumns = @JoinColumn(name = "venta_id", referencedColumnName = "producto_id"))

    @ManyToMany(cascade = CascadeType.PERSIST)
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Producto> productos = new LinkedHashSet<>();

    public Venta(){
        productos = new LinkedHashSet<>();
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @NotFound(action = NotFoundAction.IGNORE)
    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
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

//    public ModelMap getModelMapProductos(){
//        ModelMap productos = new ModelMap();
//        for(Producto p : this.productos){
//            productos.addAttribute(p, p.getCosto()*p.getCantidad());
//        }
//        return
//    }
}
