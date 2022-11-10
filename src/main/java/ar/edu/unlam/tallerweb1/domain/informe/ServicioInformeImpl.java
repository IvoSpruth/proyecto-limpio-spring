package ar.edu.unlam.tallerweb1.domain.informe;

import ar.edu.unlam.tallerweb1.delivery.DataChart;
import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
    public List<DataChart<Double>> obtenerVentasPorEmpleadoYPorFecha(LocalDate fechaInicio, LocalDate fechaFinal){
        List<DataChart<Double>> listaDataChart = new ArrayList<>();
        List<Empleado> listaEmpleados = servicioEmpleado.listarEmpleados();

        for (var empleado : listaEmpleados){
            Double total = 0D;

            List<Venta> listaVentas = servicioVenta.listarPorEmpleadoYPorFecha(empleado.getId(), fechaInicio, fechaFinal);

            for(var venta : listaVentas){
                total += venta.getTotal();
            }

            listaDataChart.add(new DataChart<>(empleado.getName(), total));
        }

        return listaDataChart;
    }

    @Override
    public List<DataChart<Integer>> obtenerVentasPorHoraSegunDia(LocalDate fechaFiltro) {
        List<DataChart<Integer>> listaDataChart = new ArrayList<>();

        List<Venta> listaVentas = servicioVenta.buscarVentasPorFecha(fechaFiltro);

        Integer[] ventasPorHora = new Integer[24];
        Arrays.fill(ventasPorHora, 0);

        for(var venta : listaVentas){
            Integer nroHora = venta.getHora().getHour();
            ventasPorHora[nroHora]++;
        }

        for(var hora : ventasPorHora){
            listaDataChart.add(hora, new DataChart<>(String.valueOf(hora), ventasPorHora[hora]));
        }

        return listaDataChart;
    }

    public String generarTituloChart(LocalDate fechaInicial, LocalDate fechaFinal) {
        String titulo = "Dinero generado de todas las ventas por empleado";

        if (fechaInicial != null)
            titulo = titulo.concat(" desde " + fechaInicial);

        if(fechaFinal != null)
            titulo = titulo.concat(" hasta " + fechaFinal);

        return titulo;
    }
}
