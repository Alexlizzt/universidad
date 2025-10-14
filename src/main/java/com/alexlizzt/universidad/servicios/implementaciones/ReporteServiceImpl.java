package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.modelo.entidades.Alumno;
import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.modelo.entidades.Pabellon;
import com.alexlizzt.universidad.modelo.entidades.Profesor;
import com.alexlizzt.universidad.servicios.contratos.*;
import org.openpdf.text.*;
import org.openpdf.text.Font;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private AlumnoDAO alumnoDAO;

    @Autowired
    private ProfesorDAO profesorDAO;

    @Autowired
    private CarreraDAO carreraDAO;

    @Autowired
    private PabellonDAO pabellonDAO;

    @Override
    public ByteArrayInputStream generarReporteAlumnosPorCarrera(String nombreCarrera) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{
            PdfWriter.getInstance(document, out);
            document.open();

            // Titulo
            Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 24);
            Paragraph paragraph = new Paragraph("Alumnos de la carrera: " + nombreCarrera, font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);

            // Reporte de Alumnos
            PdfPTable alumnosTable = new PdfPTable(2);
            addTableHeader(alumnosTable, new String[]{"Id","Nombre"});
            List<Alumno> alumnos = (List<Alumno>) alumnoDAO.buscarAlumnosPorNombreCarrera(nombreCarrera);
            for (Alumno alumno : alumnos) {
                alumnosTable.addCell(String.valueOf(alumno.getId()));
                alumnosTable.addCell(alumno.getNombre() + " " + alumno.getApellido());
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addTableHeader(PdfPTable table, String[] headers) {
        Stream.of(headers)
                .forEach(columTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(Color.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columTitle));
                    table.addCell(header);
                });
    }

    @Override
    public ByteArrayInputStream generarReporteGeneral() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Titulo
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
            Paragraph para = new Paragraph("Reporte General de la Universidad", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            // Reporte de Alumnos
            document.add(new Paragraph("Alumnos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(Chunk.NEWLINE);
            PdfPTable alumnosTable = new PdfPTable(3);
            addTableHeader(alumnosTable, new String[]{"ID", "Nombre", "Carrera"});
            List<Alumno> alumnos = StreamSupport.stream(alumnoDAO.findAll().spliterator(), false)
                    .filter(p -> p instanceof Alumno)
                    .map(p -> (Alumno) p)
                    .collect(Collectors.toList());
            for (Alumno alumno : alumnos) {
                alumnosTable.addCell(String.valueOf(alumno.getId()));
                alumnosTable.addCell(alumno.getNombre() + " " + alumno.getApellido());
                alumnosTable.addCell(alumno.getCarrera() != null ? alumno.getCarrera().getNombre() : "N/A");
            }
            document.add(alumnosTable);
            document.add(Chunk.NEWLINE);

            // Reporte de Profesores
            document.add(new Paragraph("Profesores", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(Chunk.NEWLINE);
            PdfPTable profesoresTable = new PdfPTable(3);
            addTableHeader(profesoresTable, new String[]{"ID", "Nombre", "Sueldo"});
            List<Profesor> profesors = StreamSupport.stream(profesorDAO.findAll().spliterator(), false)
                    .filter(p -> p instanceof Profesor)
                    .map(p -> (Profesor) p)
                    .collect(Collectors.toList());
            for (Profesor profesor : profesors) {
                profesoresTable.addCell(String.valueOf(profesor.getId()));
                profesoresTable.addCell(profesor.getNombre() + " " + profesor.getApellido());
                profesoresTable.addCell(profesor.getSueldo().toString());
            }
            document.add(profesoresTable);
            document.add(Chunk.NEWLINE);

            // Reporte de Carreras
            document.add(new Paragraph("Carreras", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(Chunk.NEWLINE);
            PdfPTable carrerasTable = new PdfPTable(3);
            addTableHeader(carrerasTable, new String[]{"ID", "Nombre", "Años"});
            List<Carrera> carreras = (List<Carrera>) carreraDAO.findAll();
            for (Carrera carrera : carreras) {
                carrerasTable.addCell(String.valueOf(carrera.getId()));
                carrerasTable.addCell(carrera.getNombre());
                carrerasTable.addCell(String.valueOf(carrera.getCantidadAnios()));
            }
            document.add(carrerasTable);
            document.add(Chunk.NEWLINE);

            // Reporte de Pabellones
            document.add(new Paragraph("Pabellones", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(Chunk.NEWLINE);
            PdfPTable pabellonesTable = new PdfPTable(2);
            addTableHeader(pabellonesTable, new String[]{"ID", "Nombre"});
            List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.findAll();
            for (Pabellon pabellon : pabellones) {
                pabellonesTable.addCell(String.valueOf(pabellon.getId()));
                pabellonesTable.addCell(pabellon.getNombre());
            }
            document.add(pabellonesTable);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }


}
