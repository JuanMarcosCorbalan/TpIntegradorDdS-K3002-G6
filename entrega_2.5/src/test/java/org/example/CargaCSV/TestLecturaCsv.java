package org.example.CargaCSV;

import com.opencsv.exceptions.CsvException;
import org.example.MigracionCsv.ObtencionLineasCsv;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestLecturaCsv {

    private ObtencionLineasCsv lector = new ObtencionLineasCsv();

    @Test
    public void testLeerCsv() throws IOException, CsvException {
        // Leer las líneas del archivo CSV
        List<String[]> lineas = lector.leerCsv("csvs\\csvColaboradores.csv");

        // Verificar que se leyeron las 5 líneas esperadas
        assertEquals(5, lineas.size(), "El número de líneas leídas no coincide con el esperado.");
        System.out.println("Éxito: Se leyeron las 5 líneas esperadas.");
    }

    @Test
    public void testPrimeraLinea() throws IOException, CsvException {
        // Leer las líneas del archivo CSV
        List<String[]> lineas = lector.leerCsv("csvs\\csvColaboradores.csv");

        // Verificar el contenido de la primera línea
        String[] primeraLinea = lineas.get(0);
        assertArrayEquals(new String[]{"DNI", "43871010", "Juan", "Corbalan",
                "juanmarcoscorbalan@hotmail.com", "11/12/2023",
                "DONACION_VIANDAS", "4"}, primeraLinea);
        System.out.println("Éxito: La primera línea coincide con los valores esperados.");
    }

    @Test
    public void testUltimaLinea() throws IOException, CsvException {
        // Leer las líneas del archivo CSV
        List<String[]> lineas = lector.leerCsv("csvs\\csvColaboradores.csv");

        // Verificar el contenido de la última línea
        String[] ultimaLinea = lineas.get(4);
        assertArrayEquals(new String[]{"DNI", "88275662", "Penelope", "Lavalle",
                "penelopelavalle@yahoo.com", "19/09/2023",
                "ENTREGA_TARJETAS", "5"}, ultimaLinea);
        System.out.println("Éxito: La última línea coincide con los valores esperados.");
    }

}
