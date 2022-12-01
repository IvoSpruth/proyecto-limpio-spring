package ar.edu.unlam.tallerweb1.domain.ventas;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.ServicioCierreDiario;
import ar.edu.unlam.tallerweb1.domain.cobros.ServicioMercadoPago;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicioVentaImpl implements ServicioVenta {

    private RepositorioVenta repositorioVenta;
    private ServicioProducto servicioProducto;
    private ServicioEmpleado servicioEmpleado;
    private ServicioCierreDiario servicioCierreDiario;
    private ServicioMercadoPago servicioMercadoPago;

    @Autowired
    public ServicioVentaImpl(RepositorioVenta repositorioVenta, ServicioProducto servicioProducto, ServicioEmpleado servicioEmpleado, ServicioCierreDiario servicioCierre, ServicioMercadoPago servicioMercadoPago) {
        this.repositorioVenta = repositorioVenta;
        this.servicioProducto = servicioProducto;
        this.servicioEmpleado = servicioEmpleado;
        this.servicioCierreDiario = servicioCierre;
        this.servicioMercadoPago = servicioMercadoPago;
    }


    @Override
    @Transactional
    public boolean addVenta(Venta venta) throws CantidadInsuficienteException, IdEmpleadoNoValidoException {
        try {
            validarEmpleado(venta);
            validarVenta(venta);
            fillTotal(venta);
            venta.setCierre(adjuntarCierre());
            repositorioVenta.addVenta(venta);
            actualizarCantidadesStock(venta);
            servicioMercadoPago.crearLinkDePago(venta);
            createFactura(venta);
            return true;
        } catch (CantidadInsuficienteException cie) {
            throw new CantidadInsuficienteException(cie.getMessage());
        }catch (IdEmpleadoNoValidoException ienve){
            servicioEmpleado.listarEmpleados();
            String mensaje = "El id ingresado no es valido. Los ids de los empleados registrados son : " + servicioEmpleado.listaDeIdsDeTodosLosEmpleados();
            throw new IdEmpleadoNoValidoException(mensaje);
        } catch (Exception ex) {
            return false;
        }
    }


    private CierreDiario adjuntarCierre() {
        return servicioCierreDiario.validarCierreDelDia();
    }

    public double fillTotal(Venta venta) {
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();
        double subtotalProductos = 0.0;

        for(Producto pStock : productos){
            for(Producto p : venta.getProductos()){
                if(pStock.getId() == p.getId()){
                    subtotalProductos += p.getCantidad()*pStock.getCosto();
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

    public double calcularComisionEmpleado(double sumaTotal) {
        return sumaTotal * 0.05;
    }

//    public double getSubtotalProductos() {
//        return subtotalProductos;
//    }

    private void validarVenta(Venta v) throws CantidadInsuficienteException {
        ArrayList<Producto> productos = (ArrayList) servicioProducto.buscarProductos();

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
        ArrayList<Empleado> empleados = (ArrayList)servicioEmpleado.listarEmpleados();
        boolean empleadoEncontrado = false;
        for (Empleado e : empleados) {
            if (e.getId() == v.getIdEmpleado()) {
                empleadoEncontrado = true;
            }
        }

        if (empleadoEncontrado == false) {
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

        for(Producto p : venta.getProductos()){
            for(Producto ps : productos) {
                if(p.getId()==ps.getId()){
                    ArrayList<String> columnas = new ArrayList<String>();
                    columnas.add(ps.getNombre());
                    columnas.add(String.valueOf(p.getCantidad()));
                    columnas.add(String.valueOf(ps.getCosto()));
                    columnas.add(String.valueOf(p.getCantidad() * ps.getCosto()));
                    lineas.put(indice, columnas);
                    indice++;
                }
            }
        }

        try {
            pdf = pdfM.createPDF(lineas, venta);
            return pdf;
        } catch (Exception e) {

        }
        return new File("fail.pdf");
    }

    @Transactional
    public List<Venta> buscarTodasLasVentas() {
        return (List) repositorioVenta.buscarTodasLasVentas();
    }

    @Transactional
    public List<Venta> buscarVentasPorFecha(LocalDate fecha) {
        return (List) repositorioVenta.buscarVentaPorFecha(fecha);
    }



    public Map<String, Integer> getSubTotalProducto(Venta venta){
        HashMap<String, Integer> subtotales = new HashMap<String, Integer>();
        for(Producto p : venta.getProductos()){
            subtotales.put(p.getNombre(), (int)(p.getCantidad()*p.getCosto()));
        }
        return subtotales;
    }

    @Override
    public List<Venta> listarPorEmpleadoYPorFecha(Long idEmpleado, LocalDate fechaInicial, LocalDate fechaFinal) {
        return repositorioVenta.listarVentasPorEmpleadoYFechas(Math.toIntExact(idEmpleado), fechaInicial, fechaFinal);
    }

    @Override
    public Venta buscarVenta(Long id) {
        return repositorioVenta.buscarVenta(id);
    }

    @Override
    public List<Producto> getProductos(Venta venta) {
        fillTotal(venta);
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();
        ArrayList<Producto> productosFinal = new ArrayList<>();
        for(Producto p: productos){
            for(Producto pv : venta.getProductos()){
                if(p.getId() == pv.getId()){
                    pv.setCosto(p.getCosto());
                    pv.setNombre(p.getNombre());
                    pv.setCantidad(p.getCantidad());

                    productosFinal.add(pv);
                }
            }
        }
        return productosFinal;
    }

    @Override
    public void actualizarVenta(Venta venta) {
        repositorioVenta.actualizarVenta(venta);
    }
}
