package ar.edu.unlam.tallerweb1.domain.informe;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public Map<Long, Double> obtenerVentasPorEmpleadoYPorFecha(LocalDate fechaInicio, LocalDate fechaFinal){
        Map<Long, Double> ventasPorEmpleado = new HashMap<>();
        List<Empleado> listaEmpleados = servicioEmpleado.listarEmpleados();

        for (var empleado : listaEmpleados){
            Double total = 0D;
            List<Venta> listaVentas = servicioVenta.listarPorEmpleadoYPorFecha(empleado.getId(), fechaInicio, fechaFinal);
            for(var venta : listaVentas){
                total += venta.getTotal();
            }
            ventasPorEmpleado.put(empleado.getId(), total);
        }

        return ventasPorEmpleado;
    }
}
