package ar.edu.unlam.tallerweb1.domain.cobros;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import com.mercadopago.resources.preference.Preference;


import javax.persistence.*;

@Entity
public class MercadoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    Venta venta;

    @Column(unique = true)
    private String id_preferencia;
    private String linkDePago;
    @Column(unique = true)
    private String idPago;

    private String estado;

    public MercadoPago() {
    }

    public MercadoPago(Venta venta, String id_preferencia, String linkDePago) {
        this.venta = venta;
        this.id_preferencia = id_preferencia;
        this.linkDePago = linkDePago;
    }

    public MercadoPago(Venta venta, Preference MLPreference) {
        this.venta = venta;
        this.id_preferencia = MLPreference.getId();
        this.linkDePago = MLPreference.getInitPoint();
    }


    public String getId_preferencia() {
        return id_preferencia;
    }

    public void setId_preferencia(String id_preferencia) {
        this.id_preferencia = id_preferencia;
    }

    public String getLinkDePago() {
        return linkDePago;
    }

    public void setLinkDePago(String linkDePago) {
        this.linkDePago = linkDePago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venta getVenta() {
        return venta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }
}
