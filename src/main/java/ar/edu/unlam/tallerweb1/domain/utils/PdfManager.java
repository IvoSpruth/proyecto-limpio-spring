package ar.edu.unlam.tallerweb1.domain.utils;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class PdfManager {

    private int cantidadRegistros;
    private PdfReader archivo_actual;
    private String path1;
    private String path2;
    private int linea_actual;
    private int pagina_actual;
    
    List<String> outputNames = new ArrayList<String>();
    
    private float EMIONES_SIZE = 9.5f;
    private float TOTAL_SUB_SIZE = 11.0f;
    private int emisiones_Y_linea_1 = 335;    
    private int subtotal_Y = 245;
    private int total_Y = 220;
    
    private int lastEmisionesLine;
    
    @Value(value="${inputPathPDF}")
    private String inputPath ;
    @Value(value="${outputPathPDF}")
    private String outputPath ;
    private PdfReader reader;
    private PdfStamper stamper; // output PDF - SI ES SOLAMENTE 1
    private List<PdfContentByte> pdfs;
    private BaseFont bf;
    private String _extension;


    public PdfManager(){
        try {
//            reader = new PdfReader(inputPath);
//            stamper = new PdfStamper(reader, new FileOutputStream(outputPath));
            bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            pagina_actual = 1;
            pdfs = new ArrayList<PdfContentByte>();
            _extension =".pdf";

        } catch (IOException ex) {
            Logger.getLogger(PdfManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PdfManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public File createPDF(HashMap<Integer, ArrayList<String>> lineas, Venta venta) throws DocumentException, FileNotFoundException, IOException{
        List<File> filesToMerge = new ArrayList<File>();

        reader = new PdfReader(inputPath);


        outputPath += String.valueOf(new Random().nextInt()*10)+_extension;

        for (int i=1; i<=reader.getNumberOfPages(); i++){
            stamper = new PdfStamper(reader, new FileOutputStream(outputPath));
            PdfContentByte over = stamper.getOverContent(i);

            createFirstInfo(over);
            createEmisionesLines(over, lineas, venta.getTotal());

            stamper.close();

        }
        return new File(outputPath);
         //return new File("fail.pdf");
    }

    
    private void createLine(PdfContentByte over,BaseFont bf, float size, int x, int y, String value){
        over.beginText();
        over.setFontAndSize(bf, size);    
        over.setTextMatrix(x, y);  
        over.showText(value);  
        over.endText();
    }
    
    private void createFirstInfo(PdfContentByte over){
        createLine(over, bf, 11, 800, 566, Integer.toString(1));    //numero pagina
        createLine(over, bf, 12, 140, 482, "Mario Luis Lopez");                          //id volumen
        createLine(over, bf, 12, 140, 452, new Date().toString());                                    //id cd
        //createLine(over, bf, 12, 140, 437, "test");                        //generado el
        //createLine(over, bf, 12, 95, 400, "test");                            //catalogo
        //createLine(over, bf, 12, 95, 385, "test");                              //nombre
    }
    
    public void createEmisionesLines(PdfContentByte over, HashMap<Integer, ArrayList<String>>lineas, double total){
        String separator = "-";
        int sumatoriaCoordenada_Y = emisiones_Y_linea_1;
        for(int y = 0; y < lineas.size(); y++){
            if(y != 0){
                sumatoriaCoordenada_Y -= 13;    
            }

            ArrayList<String> linea = lineas.get(y);

//          Producto
            createLine(over, bf, EMIONES_SIZE, 27, sumatoriaCoordenada_Y, linea.get(0));

//          Cantidad
            createLine(over, bf, EMIONES_SIZE, 272, sumatoriaCoordenada_Y,  linea.get(1));
//            

//          Precio Unitario
            createLine(over, bf, EMIONES_SIZE, 468, sumatoriaCoordenada_Y,  linea.get(2));


//          Contenedor 
            //createLine(over, bf, EMIONES_SIZE, 475, sumatoriaCoordenada_Y, jo.get("contenedor").getAsString());

//          Total Producto
            createLine(over, bf, EMIONES_SIZE, 700, sumatoriaCoordenada_Y,  linea.get(3));

        }
        lastEmisionesLine = sumatoriaCoordenada_Y-100;
        createLine(over, bf, EMIONES_SIZE, 27, lastEmisionesLine, "TOTAL");
        createLine(over, bf, EMIONES_SIZE, 700, lastEmisionesLine, String.valueOf(total));
    }
    
    

    
    private void switchSecondPage(){
//        try {
//            reader2 = new PdfReader(inputPath);
//            stamper2 = new PdfStamper(reader2, new FileOutputStream(outputPath2));
//        } catch (IOException | DocumentException ex) {
//            Logger.getLogger(PdfManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private String generateOutput() {
        String name = "C:\\Users\\IvoSpruth\\Documents\\WorkZone\\Test_PDF\\temp";
        name += ".pdf";
        return name;
    }
    
    /*private File merge(List<File> files){
        
        PDFMergerUtility pdfMerger = new PDFMergerUtility();  
        
        String destination = ("/C:\\Users\\IvoSpruth\\Documents\\WorkZone\\Test_PDF\\ApacheTest.pdf");
        
        new File(destination).delete();
        
        pdfMerger.setDestinationFileName(destination);
        
        try {
            for(File f: files){
                pdfMerger.addSource(f);
            }
            pdfMerger.mergeDocuments(null);
            
            return new File(destination);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PdfMerger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PdfManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return new File("fail.pdf");
    
    }*/
    
    
}
