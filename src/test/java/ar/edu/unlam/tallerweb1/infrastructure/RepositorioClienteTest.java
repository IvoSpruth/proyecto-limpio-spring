package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.Cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.Cliente.RepositorioCliente;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioClienteTest extends SpringTest {

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Test
    @Rollback
    @Transactional
    public void debeDevolverTodosLosCierresSiHayCargados(){

        dadoQueHayClientes();
        List<Cliente> listaClientes = cuandoConsultoPorLosClientes();
        entoncesObtengoLaCantidadCorrecta(listaClientes, 2);
    }

    @Test
    @Rollback
    @Transactional
    public void debeDevolverSoloLosClientesSuscriptos(){

        dadoQueHayClientes();
        List<Cliente> listaClientes = cuandoConsultoPorLosClientesSuscriptos();
        entoncesObtengoLaCantidadCorrecta(listaClientes, 1);
    }

    private void entoncesEncuentro(CierreDiario cd) {
        assertThat(cd).isNotNull();
    }


    private void entoncesFueUnExito(boolean exito) {
        assertThat(exito).isTrue();
    }

    private boolean cuandoIntentoGuardarlo(CierreDiario c) {
        return true;
    }

    private CierreDiario dadoQueHayUnCierre() {
        CierreDiario cd = new CierreDiario();
        cd.setIdEmpleado(4l);
        cd.setTotal(600000.0);
        return cd;
    }

    private void entoncesObtengoLaCantidadCorrecta(List<Cliente> listaClientes, int cantidad){
        assertThat(listaClientes).hasSize(cantidad);
    }

    private List<Cliente> cuandoConsultoPorLosClientes() {
        return repositorioCliente.buscarTodosLosClientes();
    }

    private List<Cliente> cuandoConsultoPorLosClientesSuscriptos() {
        return repositorioCliente.buscarClientesSuscriptos();
    }

    private void dadoQueHayClientes() {
        Cliente c1, c2;

        c1 = new Cliente();
        c1.setNombre("julio");
        c1.setFechaIngreso(LocalDate.now());
        c1.setMail("julio@gmail.com");
        c1.setNotifEnable(true);


        c2 = new Cliente();
        c2.setNombre("lucas");
        c2.setFechaIngreso(LocalDate.now());
        c2.setMail("lucas@gmail.com");
        c2.setNotifEnable(false);



        session().save(c1);
        session().save(c2);

    }


}
