package ar.edu.unlam.tallerweb1.domain.utils;

import ar.edu.unlam.tallerweb1.domain.ofertas.Oferta;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class MailManager {

    private Properties prop;
    Session session;

    public MailManager(){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.office365.com");
        prop.put("mail.smtp.port", "587");
        session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ivospruth@hotmail.com", "juancito41705481");
            }
        });
    }

    public void sendEmail(String mail, String mensaje){

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ivospruth@hotmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject("Oferta en productos");
            String msg = mensaje;
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e){

        }
    }

    public void sendEmailToManyRecipients(List<String> mails, List<String> mensajes){

        String mensajeFinal = prepareMensajeFinal(mensajes);

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ivospruth@hotmail.com"));
            for(String m: mails){
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(m));
            }
            message.setSubject("Oferta en productos");
            String msg = mensajeFinal;
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e){

        }
    }

//    private InternetAddress[] prepareArray(List<String> mails) {
//        ArrayList<InternetAddress> ia = new ArrayList<InternetAddress>();
//        for(String m: mails){
//            try {
//                ia.add(InternetAddress.parseparse(m));
//            } catch (AddressException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    private String prepareMensajeFinal(List<String> mensajes) {
        String mensaje = "Estas con las Ofertas semanales: \n";
        for(String m: mensajes){
            mensaje += "# " + m + "\n";
        }
        return mensaje;
    }
}
