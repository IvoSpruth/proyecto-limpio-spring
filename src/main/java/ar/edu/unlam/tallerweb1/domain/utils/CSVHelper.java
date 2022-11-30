package ar.edu.unlam.tallerweb1.domain.utils;


import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Producto> csv2Productos(MultipartFile archivo) {

        // https://www.bezkoder.com/spring-boot-upload-csv-file/

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(archivo.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Producto> ProductosRecuperados = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            String columnaId = "id";
            Producto producto;

            //Pregunto una sola vez si tengo id y despues hago un for por cada opcion para no preguntar todas las veces
            if (csvParser.getHeaderNames().contains(columnaId)) {
                for (CSVRecord csvRecord : csvRecords) {
                    producto = new Producto(
                            Long.parseLong(csvRecord.get("id")),
                            csvRecord.get("nombre"),
                            Integer.parseInt(csvRecord.get("cantidad")),
                            Double.parseDouble(csvRecord.get("costo")),
                            Integer.parseInt(csvRecord.get("stockMaximo"))
                    );
                    ProductosRecuperados.add(producto);
                }
            } else {
                for (CSVRecord csvRecord : csvRecords) {
                    producto = new Producto(
                            csvRecord.get("nombre"),
                            Integer.parseInt(csvRecord.get("cantidad")),
                            Double.parseDouble(csvRecord.get("costo")),
                            Integer.parseInt(csvRecord.get("stockMaximo"))
                    );
                    ProductosRecuperados.add(producto);
                }
            }


            return ProductosRecuperados;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream productos2CSV(List<Producto> productos) {
        // https://www.bezkoder.com/spring-boot-download-csv-file/
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            csvPrinter.printRecord("id", "nombre", "cantidad", "costo","stockMaximo", "idProveedor");
            for (Producto producto : productos) {
                List<String> data = Arrays.asList(
                        String.valueOf(producto.getId()),
                        producto.getNombre(),
                        String.valueOf(producto.getCantidad()),
                        String.valueOf(producto.getCosto()),
                        String.valueOf(producto.getStockMaximo())
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}