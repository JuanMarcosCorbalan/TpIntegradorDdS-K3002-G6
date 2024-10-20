package org.example.MigracionCsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObtencionLineasCsv {

    public List<String[]> leerCsv(String archivoCsv) throws IOException, CsvException {
        List<String[]> lineasLimpias = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(archivoCsv), StandardCharsets.UTF_8))) {
            List<String[]> lineas = reader.readAll();
            for (String[] linea : lineas) {
                // Limpiar espacios en blanco y caracteres invisibles de cada celda
                String[] lineaLimpia = new String[linea.length];
                for (int i = 0; i < linea.length; i++) {
                    lineaLimpia[i] = limpiarString(linea[i]); // Llamar a la función de limpieza
                }
                lineasLimpias.add(lineaLimpia);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            throw e;
        } catch (CsvException e) {
            System.err.println("Error en el formato del CSV: " + e.getMessage());
            throw e;
        }

        return lineasLimpias;
    }

    // Método para limpiar la cadena
    private String limpiarString(String input) {
        if (input == null) return null;
        return input.replaceAll("[^\\x20-\\x7E]", "").trim(); // Eliminar caracteres no imprimibles y recortar
    }
}

