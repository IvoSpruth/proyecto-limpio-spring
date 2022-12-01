package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import ar.edu.unlam.tallerweb1.domain.ofertas.Oferta;
import ar.edu.unlam.tallerweb1.domain.ofertas.RepositorioOferta;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioOfertaTest extends SpringTest {

    @Autowired
    private RepositorioOferta repositorioOferta;

    @Test
    @Rollback
    @Transactional
    public void debeDevolverTodasLasOfertas(){

        dadoQueHayOfertas();
        List<Oferta> listaOfertas = cuandoConsultoPorLasOfertas();
        entoncesObtengoLaCantidadCorrecta(listaOfertas, 4);
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

    private void entoncesObtengoLaCantidadCorrecta(List<Oferta> listaOferta, int cantidad){
        assertThat(listaOferta).hasSize(cantidad);
    }

    private List<Oferta> cuandoConsultoPorLasOfertas() {
        return repositorioOferta.traerOfertas();
    }

    private void dadoQueHayOfertas() {
        Oferta o1, o2;

        o1 = new Oferta();
        o1.setFecha(LocalDate.now().plusDays(1));
        o1.setMensaje("test");
        o1.setIdEmpleado(1l);
        o1.setValida(true);

        o2 = new Oferta();
        o2.setFecha(LocalDate.now());
        o2.setMensaje("test2");
        o2.setIdEmpleado(1l);
        o2.setValida(true);



        session().save(o1);
        session().save(o2);

    }


}
