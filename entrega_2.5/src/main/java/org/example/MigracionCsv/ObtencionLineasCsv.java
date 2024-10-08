package org.example.MigracionCsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ObtencionLineasCsv {

    public List<String[]> leerCsv(String archivoCsv) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(archivoCsv));
        return reader.readAll();
    }
}
