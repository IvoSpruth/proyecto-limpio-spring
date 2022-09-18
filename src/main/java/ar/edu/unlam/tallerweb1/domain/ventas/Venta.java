package ar.edu.unlam.tallerweb1.domain.ventas;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private int idProducto;

    private int cantidad;



}
