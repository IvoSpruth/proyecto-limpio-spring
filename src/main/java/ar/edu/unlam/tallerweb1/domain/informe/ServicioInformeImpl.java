package ar.edu.unlam.tallerweb1.domain.informe;

import ar.edu.unlam.tallerweb1.delivery.DataEmpleado;
import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public List<DataEmpleado> obtenerVentasPorEmpleadoYPorFecha(LocalDate fechaInicio, LocalDate fechaFinal){
        List<DataEmpleado> listaDataEmpleado = new ArrayList<>();
        List<Empleado> listaEmpleados = servicioEmpleado.listarEmpleados();

        for (var empleado : listaEmpleados){
            Double total = 0D;

            List<Venta> listaVentas = servicioVenta.listarPorEmpleadoYPorFecha(empleado.getId(), fechaInicio, fechaFinal);

            for(var venta : listaVentas){
                total += venta.getTotal();
            }

            listaDataEmpleado.add(new DataEmpleado(empleado.getName(), total));
        }

        return listaDataEmpleado;
    }
}
