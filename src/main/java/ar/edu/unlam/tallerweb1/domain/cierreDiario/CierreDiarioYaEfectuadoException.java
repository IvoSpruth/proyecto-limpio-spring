package ar.edu.unlam.tallerweb1.domain.cierreDiario;

import java.time.LocalDate;

public class CierreDiarioYaEfectuadoException extends Exception {
    public CierreDiarioYaEfectuadoException(String date){
        super("El cierre de la fecha " + date + " ya fue realizado!");
    }

    public CierreDiarioYaEfectuadoException(){
        super("El cierre de la fecha " + LocalDate.now().toString() + " ya fue realizado!");
    }
}
