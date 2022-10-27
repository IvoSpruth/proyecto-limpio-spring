package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.cierreDiario.*;
import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.ofertas.RepositorioOferta;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.utils.MailManager;
import ar.edu.unlam.tallerweb1.domain.ventas.CantidadInsuficienteException;
import ar.edu.unlam.tallerweb1.domain.ventas.IdEmpleadoNoValidoException;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ServicioOfertaTest extends SpringTest {

  private RepositorioOferta repositorioOferta;
  private MailManager mm;


    @Before
    public void init(){
        this.repositorioOferta= mock(RepositorioOferta.class);
        this.mm = mock(MailManager.class);
    }


    @Rollback
    @Test
    public void queMePermitaEnviarLasNotificaciones() throws CierreDiarioYaEfectuadoException{
//        cuandoEnvioNotificaciones();
//        entoncesSaleTodoBien();
    }

    private void cuandoEnvioNotificaciones() {
//        doThrow().when(this.mm.sendEmail("",""));
    }



}