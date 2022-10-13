package ar.edu.unlam.tallerweb1.domain.ventas;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.ServicioCierreDiario;
import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.utils.PdfManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ServicioVentaImpl  implements   ServicioVenta{

    private RepositorioVenta repositorioVenta;
    private ServicioProducto servicioProducto;

    private ServicioEmpleado servicioEmpleado;

    private ServicioCierreDiario servicioCierreDiario;



    @Autowired
    public ServicioVentaImpl(RepositorioVenta repositorioVenta, ServicioProducto servicioProducto, ServicioEmpleado servicioEmpleado, ServicioCierreDiario servicioCierre){
        this.repositorioVenta = repositorioVenta;
        this.servicioProducto = servicioProducto;
        this.servicioEmpleado = servicioEmpleado;
        this.servicioCierreDiario = servicioCierre;
    }


    @Override
    @Transactional
    public boolean addVenta(Venta venta) throws CantidadInsuficienteException, IdEmpleadoNoValidoException{
        try {
            validarEmpleado(venta);
            validarVenta(venta);
            fillTotal(venta);
            venta.setCierre(adjuntarCierre());
            repositorioVenta.addVenta(venta);
            actualizarCantidadesStock(venta);
            return true;
        }catch(CantidadInsuficienteException cie){
            throw new CantidadInsuficienteException(cie.getMessage());
        }catch (IdEmpleadoNoValidoException ienve){
            servicioEmpleado.traemeTodosLosEmpleados();
            String mensaje = "El id ingresado no es valido. Los ids de los empleados registrados son : " + servicioEmpleado.listaDeIdsDeTodosLosEmpleados();
            throw new IdEmpleadoNoValidoException(mensaje);
        } catch (Exception ex){
            return false;
        }
    }

    private CierreDiario adjuntarCierre() {
        return servicioCierreDiario.validarCierreDelDia();
    }


    private void fillTotal(Venta venta) {
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();
        double subtotalProducto1= 0.0;
        double subtotalProducto2= 0.0;

        for(Producto p : productos){
            if(venta.getIdProducto()==p.getId()){
                subtotalProducto1=p.getCosto()* venta.getCantidadProducto();
            }
            if(venta.getIdProducto2()==p.getId()){
                subtotalProducto2= p.getCosto()* venta.getCantidadProducto2();
            }
        }

        venta.setTotal(subtotalProducto1+subtotalProducto2);
    }

    @Transactional
    private void actualizarCantidadesStock(Venta venta) {
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();
        for(Producto p : productos){
            if(venta.getIdProducto()==p.getId()){
                p.setCantidad(p.getCantidad() - venta.getCantidadProducto());
            }
            if(venta.getIdProducto2()==p.getId()){
                p.setCantidad(p.getCantidad() - venta.getCantidadProducto2());
            }
        }
        servicioProducto.updateProductos(productos);
    }

    @Override
    public boolean modifyVenta(Venta venta) {
        return false;
    }

    @Override
    public boolean deleteVenta(Venta venta) {
        return false;
    }

    @Override
    public Venta buscarVenta(Venta venta) {
        return null;
    }


    private void validarVenta(Venta v) throws CantidadInsuficienteException{
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();

        for(Producto p : productos){
            if(v.getIdProducto()==p.getId()){
                if(v.getCantidadProducto() > p.getCantidad()){
                    throw new CantidadInsuficienteException(p.getNombre(), v.getCantidadProducto(), p.getCantidad());
                }
            }
            if(v.getIdProducto2()==p.getId()){
                if(v.getCantidadProducto2() > p.getCantidad()){
                    throw new CantidadInsuficienteException(p.getNombre(), v.getCantidadProducto2(), p.getCantidad());
                }
            }
        }

    }

    private void validarEmpleado(Venta v) throws IdEmpleadoNoValidoException{
        Long idEmpleadoVenta = Long.valueOf(v.getIdEmpleado());
        Empleado empleadoBuscado = new Empleado();
        empleadoBuscado.setId(idEmpleadoVenta);
        if (servicioEmpleado.buscarEmpleado(empleadoBuscado) == null){
            throw new IdEmpleadoNoValidoException(v.getIdEmpleado());
        }
    }

    public File createFactura(Venta venta) {
        PdfManager pdfM = new PdfManager();
        File pdf;
        String pipe = "|";
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();
        String linea;
        HashMap<Integer, ArrayList<String>> lineas = new HashMap<>();
        int indice = 0;

        for(Producto p : productos){
            if(venta.getIdProducto()==p.getId()){
                ArrayList<String> columnas = new ArrayList<String>();
                columnas.add(p.getNombre());
                columnas.add(String.valueOf(venta.getCantidadProducto()));
                columnas.add(String.valueOf(p.getCosto()));
                columnas.add(String.valueOf(venta.getCantidadProducto()*p.getCosto()));
                lineas.put(indice, columnas);
                indice++;
            }
            if(venta.getIdProducto2()==p.getId()){
                ArrayList<String> columnas = new ArrayList<String>();
                columnas.add(p.getNombre());
                columnas.add(String.valueOf(venta.getCantidadProducto2()));
                columnas.add(String.valueOf(p.getCosto()));
                columnas.add(String.valueOf(venta.getCantidadProducto2()*p.getCosto()));
                lineas.put(indice, columnas);
                indice++;
            }
        }

        try{
            pdf = pdfM.createPDF(lineas, venta);
            return pdf;
        } catch(Exception e){

        }
        return new File("fail.pdf");
    }

}
