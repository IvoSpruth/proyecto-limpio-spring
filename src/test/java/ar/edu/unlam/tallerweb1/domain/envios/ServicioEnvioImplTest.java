package ar.edu.unlam.tallerweb1.domain.envios;


import ar.edu.unlam.tallerweb1.domain.cliente.ServicioCliente;
import ar.edu.unlam.tallerweb1.domain.ventas.ServicioVenta;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class ServicioEnvioImplTest {

    private ServicioEnvio servicioEnvio;


    @Mock private ServicioCliente servicioClienteMock;
    @Mock private ServicioVenta servicioVentaMock;
    @Mock private RepositorioEnvio repositorioEnvioMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        servicioEnvio = new ServicioEnvioImpl(servicioClienteMock, servicioVentaMock, repositorioEnvioMock);
    }

    @Test
    @Transactional
    @Rollback
    public void testConcretarEnvio() {

    }
}