package org.example.CargaCSV;

import com.opencsv.exceptions.CsvException;
import org.example.Colaborador.Colaborador;
import org.example.MigracionCsv.CargaDatosCsv;
import org.example.MigracionCsv.DatosColaboracion;
import org.example.MigracionCsv.ExtraccionDatosLinea;
import org.example.MigracionCsv.ObtencionLineasCsv;
import org.example.Persona.Documento_identidad;
import org.example.Persona.Persona;
import org.example.Persona.Persona_fisica;
import org.example.Persona.Tipo_documento;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestCargaCsv {
    @Test
    void testExtraerDatosLinea() {
        String[] linea = {"DNI", "12345678", "Juan", "Perez", "juan@example.com", "2023-01-01", "DINERO", "100"};
        DatosColaboracion datos = new DatosColaboracion();
        ExtraccionDatosLinea extractor = new ExtraccionDatosLinea();

        extractor.extraerDatosLinea(linea, datos);

        assertEquals("DNI", datos.getTipoDoc());
        assertEquals(Integer.valueOf(12345678), datos.getNumeroDocumento());
        assertEquals("Juan", datos.getNombre());
        assertEquals("Perez", datos.getApellido());
        assertEquals("juan@example.com", datos.getMail());
        assertEquals("2023-01-01", datos.getFechaColaboracionString());
        assertEquals("DINERO", datos.getFormaColaboracion());
        assertEquals(Integer.valueOf(100), datos.getCantidad());
    }
}
class CargaDatosCsvTest {

    @Test
    void testCargarDatos() throws IOException {
        List<Colaborador> colaboradoresExistentes = new ArrayList<>();
        Map<String, Colaborador> colaboradoresExistentesMap = new HashMap<>();
        Map<String, Persona> personasFisicasExistentesMap = new HashMap<>();

        CargaDatosCsv cargaDatos = new CargaDatosCsv();
        cargaDatos.cargarDatos(colaboradoresExistentes, colaboradoresExistentesMap,
                personasFisicasExistentesMap, "Juan", "Perez", "12345678",
                Tipo_documento.DNI, "juan@example.com", "DINERO", 100, LocalDate.of(2023, 1, 1));

        assertFalse(colaboradoresExistentes.isEmpty());
        assertTrue(colaboradoresExistentesMap.containsKey("12345678"));
        assertNotNull(colaboradoresExistentesMap.get("12345678").getContribuciones());
    }
}



class CreacionObjetosTest {

    @Test
    void testCrearDocumento() {
        CargaDatosCsv cargaDatos = new CargaDatosCsv();
        Documento_identidad documento = cargaDatos.crearDocumento("12345678", Tipo_documento.DNI);

        assertEquals("12345678", documento.getNumeroDocumento());
        assertEquals(Tipo_documento.DNI, documento.getTipo());
    }

    @Test
    void testCrearPersonaFisica() {
        Documento_identidad documento = new Documento_identidad("12345678", Tipo_documento.DNI);
        CargaDatosCsv cargaDatos = new CargaDatosCsv();
        Persona_fisica persona = cargaDatos.crearPersonaFisica("Juan", "Perez", documento);

        assertEquals("Juan", persona.getNombre());
        assertEquals("Perez", persona.getApellido());
        assertEquals(documento, persona.getDocumento_identidad());
    }
}

