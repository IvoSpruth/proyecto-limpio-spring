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
import java.time.LocalDate;
import java.util.*;

@Service
public class ServicioVentaImpl  implements   ServicioVenta{

    private RepositorioVenta repositorioVenta;
    private ServicioProducto servicioProducto;
    private ServicioEmpleado servicioEmpleado;
    private ServicioCierreDiario servicioCierreDiario;
    private double subtotalProductos= 0.0;

    @Autowired
    public ServicioVentaImpl(RepositorioVenta repositorioVenta, ServicioProducto servicioProducto, ServicioEmpleado servicioEmpleado, ServicioCierreDiario servicioCierre){
        this.repositorioVenta = repositorioVenta;
        this.servicioProducto = servicioProducto;
        this.servicioEmpleado = servicioEmpleado;
        this.servicioCierreDiario = servicioCierre;
    }


    @Override
    @Transactional
    public boolean  addVenta(Venta venta) throws CantidadInsuficienteException, IdEmpleadoNoValidoException{
        try {
            validarEmpleado(venta);
            validarVenta(venta);
            fillTotal(venta);
            venta.setCierre(adjuntarCierre());
            repositorioVenta.addVenta(venta);
            actualizarCantidadesStock(venta);
            //createFactura(venta);
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

    public double fillTotal(Venta venta) {
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();
        double subtotalProductos = 0.0;

        for(Producto p : productos){
            for(Producto pv: venta.getProductos()){
                if(p.getId()==pv.getId()){
                    subtotalProductos += pv.getCantidad()*p.getCosto();
                }
            }
        }

        venta.setTotal(subtotalProductos);
        return subtotalProductos;
    }

    @Transactional
    private void actualizarCantidadesStock(Venta venta) {
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();
        for(Producto p : productos){
            for(Producto pv: venta.getProductos()){
                if(p.getId()==pv.getId()){
                    p.setCantidad(p.getCantidad()-pv.getCantidad());
                }
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

    @Override
    public String buscarNombreEmpleado(int idEmpleado) {
        ArrayList<Empleado> empleados = (ArrayList) servicioEmpleado.traemeTodosLosEmpleados();
        for (Empleado e : empleados) {
            if (idEmpleado == e.getId()) {
                return e.getName();
            }
        }
        return null;
    }

/*    @Override
    public String buscarNombreDeEmpleadoPorId(int idEmpleado) {
        return servicioEmpleado.buscarNombreDeEmpleadoPorId(idEmpleado);
    }*/

    @Override
    public String buscarNombreProducto(int idProducto) {
        ArrayList<Producto> productos = (ArrayList) servicioProducto.buscarProductos();
        for (Producto p : productos) {
            if (idProducto == p.getId()) {
                return  p.getNombre();
            }
        }
        return null;
    }

    @Override
    public double buscarCostoProducto(int idProducto) {
        ArrayList<Producto> productos = (ArrayList) servicioProducto.buscarProductos();
        for (Producto p : productos) {
            if (idProducto == p.getId()) {
                return p.getCosto();
            }
        }
        return 0.0;
    }

    public double calcularComisionEmpleado(double sumaTotal){
        return sumaTotal * 0.05;
    }

//    public double getSubtotalProductos() {
//        return subtotalProductos;
//    }

    private void validarVenta(Venta v) throws CantidadInsuficienteException{
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();

        for(Producto p : productos){
            for(Producto pV: v.getProductos()){
                if(p.getId()==pV.getId()){
                    if(pV.getCantidad()>p.getCantidad()){
                        throw new CantidadInsuficienteException(p.getNombre(), pV.getCantidad(), p.getCantidad());
                    }
                }
            }
        }

    }

    private void validarEmpleado(Venta v) throws IdEmpleadoNoValidoException{
        ArrayList<Empleado> empleados = (ArrayList)servicioEmpleado.traemeTodosLosEmpleados();
        boolean empleadoEncontrado = false;
        for(Empleado e : empleados){
            if (e.getId() == v.getIdEmpleado()){
                empleadoEncontrado = true;
            }
        }

        if (empleadoEncontrado == false){
            throw new IdEmpleadoNoValidoException(v.getIdEmpleado());
        }
    }

    public File createFactura(Venta venta) {
//        PdfManager pdfM = new PdfManager();
//        File pdf;
//        String pipe = "|";
//        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();
//        String linea;
//        HashMap<Integer, ArrayList<String>> lineas = new HashMap<>();
//        int indice = 0;
//
//        for(Producto p : productos){
//            if(venta.getIdProducto()==p.getId()){
//                ArrayList<String> columnas = new ArrayList<String>();
//                columnas.add(p.getNombre());
//                columnas.add(String.valueOf(venta.getCantidadProducto()));
//                columnas.add(String.valueOf(p.getCosto()));
//                columnas.add(String.valueOf(venta.getCantidadProducto()*p.getCosto()));
//                lineas.put(indice, columnas);
//                indice++;
//            }
//            if(venta.getIdProducto2()==p.getId()){
//                ArrayList<String> columnas = new ArrayList<String>();
//                columnas.add(p.getNombre());
//                columnas.add(String.valueOf(venta.getCantidadProducto2()));
//                columnas.add(String.valueOf(p.getCosto()));
//                columnas.add(String.valueOf(venta.getCantidadProducto2()*p.getCosto()));
//                lineas.put(indice, columnas);
//                indice++;
//            }
//        }
//
//        try{
//            pdf = pdfM.createPDF(lineas, venta);
//            return pdf;
//        } catch(Exception e){
//
//        }
        return new File("fail.pdf");
    }
    @Transactional
    public List<Venta> buscarTodasLasVentas(){
        return (List)repositorioVenta.buscarTodasLasVentas();
    }

    @Transactional
    public List<Venta> buscarVentasPorFecha(LocalDate fecha){
        return (List)repositorioVenta.buscarVentaPorFecha(fecha);
    }

    public Map<String, Integer> getSubTotalProducto(Venta venta){
        HashMap<String, Integer> subtotales = new HashMap<String, Integer>();
        for(Producto p : venta.getProductos()){
            subtotales.put(p.getNombre(), (int)(p.getCantidad()*p.getCosto()));
        }
        return subtotales;
    }

}
