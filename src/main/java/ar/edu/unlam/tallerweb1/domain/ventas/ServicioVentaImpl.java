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
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioVentaImpl  implements   ServicioVenta{

    private RepositorioVenta repositorioVenta;
    private ServicioProducto servicioProducto;

    private ServicioEmpleado servicioEmpleado;

    private ServicioCierreDiario servicioCierreDiario;
    private double subtotalProducto1= 0.0;
    private double subtotalProducto2= 0.0;
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
    public boolean addVenta(Venta venta) throws CantidadInsuficienteException, IdEmpleadoNoValidoException{
        try {
            validarEmpleado(venta);
            validarVenta(venta);
            fillTotal(venta);
            venta.setCierre(adjuntarCierre());
            repositorioVenta.addVenta(venta);
            actualizarCantidadesStock(venta);
            createFactura(venta);
            return true;
        }catch(CantidadInsuficienteException cie){
            throw new CantidadInsuficienteException(cie.getMessage());
        }catch (IdEmpleadoNoValidoException ienve){
            servicioEmpleado.listarEmpleados();
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

        for(Producto p : productos){
            if(venta.getIdProducto()==p.getId()){
                subtotalProducto1=p.getCosto()* venta.getCantidadProducto();
            }
            if(venta.getIdProducto2()==p.getId()){
                subtotalProducto2= p.getCosto()* venta.getCantidadProducto2();
            }
        }
        subtotalProductos = subtotalProducto1+subtotalProducto2;
        venta.setTotal(subtotalProductos);

        return subtotalProductos;
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

    @Override
    public String buscarNombreEmpleado(int idEmpleado) {
        ArrayList<Empleado> empleados = (ArrayList) servicioEmpleado.listarEmpleados();
        for (Empleado e : empleados) {
            if (idEmpleado == e.getId()) {
                return e.getName();
            }
        }
        return null;
    }

    @Override
    public String buscarNombreProducto(int idProducto) {
        ArrayList<Producto> productos = (ArrayList) servicioProducto.buscarProductos();
        for (Producto p : productos) {
            if (idProducto == p.getId()) {
                String nombreProductoUno = p.getNombre();
                return nombreProductoUno;
            }
            if (idProducto == p.getId()) {
                String nombreProductoDos = p.getNombre();
                return nombreProductoDos;
            }
        }
        return null;
    }

    @Override
    public double buscarCostoProducto(int idProducto) {
        ArrayList<Producto> productos = (ArrayList) servicioProducto.buscarProductos();
        for (Producto p : productos) {
            if (idProducto == p.getId()) {
                double costoProductoUno = p.getCosto();
                return costoProductoUno;
            }
            if (idProducto == p.getId()) {
                double costoProductoDos = p.getCosto();
                return costoProductoDos;
            }
        }
        return 0.0;
    }

    public double calcularComisionEmpleado(double sumaTotal){
        return sumaTotal * 0.05;
    }

    public double getSubtotalProducto1() {
        return subtotalProducto1;
    }

    public double getSubtotalProducto2() {
        return subtotalProducto2;
    }

    public double getSubtotalProductos() {
        return subtotalProductos;
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
        ArrayList<Empleado> empleados = (ArrayList)servicioEmpleado.listarEmpleados();
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
    @Transactional
    public List<Venta> buscarTodasLasVentas(){
        return (List)repositorioVenta.buscarTodasLasVentas();
    }

    @Transactional
    public List<Venta> buscarVentasPorFecha(LocalDate fecha){
        return (List)repositorioVenta.buscarVentaPorFecha(fecha);
    }

    @Override
    public List<Venta> listarPorEmpleado(Long idEmpleado) {
        return repositorioVenta.ventasDeUnEmpleado(Math.toIntExact(idEmpleado));
    }
}
