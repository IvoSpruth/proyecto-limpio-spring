package ar.edu.unlam.tallerweb1.domain.cierreDiario;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.utils.PdfManager;
import ar.edu.unlam.tallerweb1.domain.ventas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ServicioCierreDiarioImpl implements ServicioCierreDiario {

   private RepositorioCierreDiario repositorioCierreDiario;


    @Autowired
    public ServicioCierreDiarioImpl(RepositorioCierreDiario repositorioCierre){
        this.repositorioCierreDiario = repositorioCierre;
    }


    @Override
    @Transactional
    public boolean ejecutarCierre() throws CierreDiarioYaEfectuadoException{
        CierreDiario cd = repositorioCierreDiario.buscarCierrePorFecha(LocalDate.now());
        List<Producto> cantProductos;
        Producto masVendido;
        if(cd != null){
            if(cd.isCerrado()){
                throw new CierreDiarioYaEfectuadoException();
            }
            cd.setCerrado(true);
            cd.setTotal(calculateTotal(cd.getVentas()));
            cantProductos = getCantProductosVendidos(cd.getVentas());
            masVendido = getProductoMasVendido(cantProductos);
            cd.setCerrado(true);
            repositorioCierreDiario.guardarCierreDelDia(cd);
            return true;
        } else {
            cd = new CierreDiario();
            cd.setCerrado(true);
            repositorioCierreDiario.guardarCierreDelDia(cd);
            return true;
        }
    }

    private List<Producto>  getCantProductosVendidos(List<Venta> ventas) {
        ArrayList<Producto> cantProductos = new ArrayList<>();
        ArrayList<Long> idsProd = new ArrayList<>();
        for(Venta v : ventas){
            if(idsProd.contains((long)v.getIdProducto()) && idsProd.contains((long)v.getIdProducto2())){
                for(Producto p : cantProductos){
                    if(p.getId()==(long)v.getIdProducto()){
                        p.setCantidad(p.getCantidad()+v.getCantidadProducto());
                    }
                    if(p.getId()==(long)v.getIdProducto2()){
                        p.setCantidad(p.getCantidad()+v.getCantidadProducto2());
                    }
                }
            } else if(idsProd.contains((long)v.getIdProducto()) && !idsProd.contains((long)v.getIdProducto2())){
                for(Producto p : cantProductos){
                    if(p.getId()==(long)(v.getIdProducto())){
                        p.setCantidad(p.getCantidad()+v.getCantidadProducto());
                    }
                }
            } else if(!idsProd.contains((long)v.getIdProducto()) && idsProd.contains((long)v.getIdProducto2())){
                for(Producto p : cantProductos){
                    if(p.getId()==(long)v.getIdProducto2()){
                        p.setCantidad(p.getCantidad()+v.getCantidadProducto2());
                    }
                }
            } else {
                Producto p1 = new Producto();
                p1.setId((long)v.getIdProducto());
                p1.setCantidad(v.getCantidadProducto());

                Producto p2 = new Producto();
                p2.setId((long)v.getIdProducto2());
                p2.setCantidad(v.getCantidadProducto2());

                cantProductos.add(p1);
                cantProductos.add(p2);
            }
            for(Producto ps : cantProductos){
                if(!idsProd.contains((long)ps.getId())){
                    idsProd.add(ps.getId());
                }
            }
        }
        return cantProductos;
    }

    private Producto getProductoMasVendido(List<Producto> cantProds) {
        if(cantProds.size()==0){return new Producto();}

        Producto masVendido = cantProds.get(0);
        for(Producto p : cantProds){
            if(p.getCantidad()>=masVendido.getCantidad()){
                masVendido = p;
            }
        }

        return masVendido;
    }

    private double calculateTotal(List<Venta> ventas) {
        double total = 0.0;
        for(Venta v : ventas){
            total+=v.getTotal();
        }
        return total;
    }

    @Override
    @Transactional
    public List<CierreDiario> historialCierres() {
        return repositorioCierreDiario.getHistorialCierreD();
    }

    @Override
    public CierreDiario validarCierreDelDia() {
        CierreDiario cd = repositorioCierreDiario.buscarCierrePorFecha(LocalDate.now());
        if(cd == null){
            cd = new CierreDiario();
            repositorioCierreDiario.guardarCierreDelDia(cd);
        }
        return cd;
    }



}
