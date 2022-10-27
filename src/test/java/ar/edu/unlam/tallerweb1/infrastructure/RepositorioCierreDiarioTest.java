package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.RepositorioCierreDiario;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioCierreDiarioTest extends SpringTest {

    @Autowired
    private RepositorioCierreDiario repositorioCierreDiario;

    @Test
    @Rollback
    @Transactional
    public void debeDevolverTodosLosCierresSiHayCargados(){

        dadoQueHayCierres();
        List<CierreDiario> listaCierres = cuandoConsultoPorElHistorialDeLosCierres();
        entoncesObtengoLaCantidadCorrecta(listaCierres, 1);
    }

    @Test
    @Rollback
    @Transactional
    public void alBuscarCierrePorFechaLoEncuentra(){

        dadoQueHayCierres();
        LocalDate fechaBuscada = LocalDate.of(2022, 10, 27);
        CierreDiario cierreBuscado = cuandoBuscoCierrePorFecha(fechaBuscada);
        entoncesEncuentro(cierreBuscado);
    }

    private void entoncesEncuentro(CierreDiario cd) {
        assertThat(cd).isNotNull();
    }

    private CierreDiario cuandoBuscoCierrePorFecha(LocalDate date) {
        return repositorioCierreDiario.buscarCierrePorFecha(date);
    }

    /*@Test
    @Rollback
    @Transactional
    public void debeGuardarExitosamenteUnCierre(){

        CierreDiario cierre = dadoQueHayUnCierre();
        boolean exito = cuandoIntentoGuardarlo(cierre);
        entoncesFueUnExito(exito);
    }*/

    private void entoncesFueUnExito(boolean exito) {
        assertThat(exito).isTrue();
    }

    private boolean cuandoIntentoGuardarlo(CierreDiario c) {
        return true;//repositorioCierreDiario.guardarCierreDelDia(c);
    }

    private CierreDiario dadoQueHayUnCierre() {
        CierreDiario cd = new CierreDiario();
        cd.setIdEmpleado(4l);
        cd.setTotal(600000.0);
        return cd;
    }

    private void entoncesObtengoLaCantidadCorrecta(List<CierreDiario> listaCierres, int cantidad){
        assertThat(listaCierres).hasSize(cantidad);
    }

    private List<CierreDiario> cuandoConsultoPorElHistorialDeLosCierres() {
        return repositorioCierreDiario.getHistorialCierreD();
    }

    private void dadoQueHayCierres() {
        CierreDiario cd = new CierreDiario();
        cd.setIdEmpleado(2l);
        cd.setTotal(500000.0);

        Venta v1 = new Venta();
        v1.setId(1l);
        v1.setTotal(5000.0);
        Venta v2 = new Venta();
        v1.setId(2l);
        v1.setTotal(12000.0);
        cd.getVentas().add(v1);
        cd.getVentas().add(v2);


        CierreDiario cd2 = new CierreDiario();
        cd.setIdEmpleado(3l);
        cd.setTotal(100000.0);

        Venta v3 = new Venta();
        v1.setId(2l);
        v1.setTotal(5000.0);
        Venta v4 = new Venta();
        v1.setId(4l);
        v1.setTotal(12000.0);
        cd2.getVentas().add(v3);
        cd2.getVentas().add(v4);
        cd2.setFecha(LocalDate.of(2022,10,21));

        session().save(cd);
        //session().save(cd2);

    }


}
