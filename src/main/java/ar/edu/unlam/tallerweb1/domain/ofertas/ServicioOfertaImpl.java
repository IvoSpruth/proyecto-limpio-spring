package ar.edu.unlam.tallerweb1.domain.ofertas;

import ar.edu.unlam.tallerweb1.domain.Cliente.Cliente;
import ar.edu.unlam.tallerweb1.domain.Cliente.RepositorioCliente;
import ar.edu.unlam.tallerweb1.domain.Cliente.ServicioCliente;
import ar.edu.unlam.tallerweb1.domain.utils.MailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioOfertaImpl implements ServicioOferta {

   private RepositorioOferta repositorioOferta;
   private ServicioCliente servicioCliente;

   private MailManager mailManager;

    @Autowired
    public ServicioOfertaImpl(RepositorioOferta repositorioOferta, ServicioCliente servicioCliente){
        this.repositorioOferta = repositorioOferta;
        this.servicioCliente = servicioCliente;
        mailManager = new MailManager();
    }

    @Override
    public void agregarOferta(Oferta oferta) {

    }

    @Override
    @Transactional
    public void enviarNotificaciones() {
      try{
       List<Cliente> clientesANotificar = servicioCliente.traerClientesSuscriptos();
       List<Oferta> ofertas = repositorioOferta.traerOfertas();

       ArrayList<String> mails = getMails(clientesANotificar);
       ArrayList<String> mensajes = getMensajes(ofertas);

//       if(mails.size()<1){
//           //nada
//       } else if (mails.size()==1){
//           mailManager.sendEmail(mails.get(0), mensajes.get(0));
//       } else {
//           mailManager.sendEmailToManyRecipients(mails, mensajes);
//       }

      } catch (Exception e){

      }
    }

    private ArrayList<String> getMensajes(List<Oferta> ofertas) {
        ArrayList<String> mensajes = new ArrayList();
        for(Oferta o : ofertas){
            mensajes.add(o.getMensaje());
        }
        return mensajes;
    }

    private ArrayList<String> getMails(List<Cliente> clientesANotificar) {
     ArrayList mails = new ArrayList();
     if(clientesANotificar==null || clientesANotificar.size()==0){
       return mails;
     }
     for(Cliente c : clientesANotificar){
       mails.add(c.getMail());
     }
     return mails;
 }

 @Override
    @Transactional
    public List<Oferta> traerOfertasCargadas() {
      return repositorioOferta.traerOfertas();
    }
}
