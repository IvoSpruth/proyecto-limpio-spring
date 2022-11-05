package ar.edu.unlam.tallerweb1.domain.informe;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ServicioInformeImpl implements ServicioInforme{

    private ServicioEmpleado servicioEmpleado;
    private ServicioVenta servicioVenta;

    @Autowired
    public ServicioInformeImpl(ServicioEmpleado servicioEmpleado, ServicioVenta servicioVenta){
        this.servicioEmpleado = servicioEmpleado;
        this.servicioVenta = servicioVenta;
    }

    @Override
    public Map<Long, Integer> obtenerVentasPorEmpleado(){
        Map<Long, Integer> ventasPorEmpleado = new HashMap<>();
        List<Empleado> listaEmpleados = servicioEmpleado.listarEmpleados();

        for (var empleado : listaEmpleados){
            List<Venta> listaVentas = servicioVenta.listarPorEmpleado(empleado.getId());
            ventasPorEmpleado.put(empleado.getId(), listaVentas.size());
        }

        return ventasPorEmpleado;
    }
}
