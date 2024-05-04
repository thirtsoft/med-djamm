package com.meddjamm.sn.reportpdfexcel.services.Impl;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.meddjamm.sn.dossiermedical.assembler.PDFExportAssembler;
import com.meddjamm.sn.dossiermedical.remote.model.PatientMinDs;
import com.meddjamm.sn.reportpdfexcel.services.ReportPdfService;
import com.meddjamm.sn.utils.ReportAbstract;
import jakarta.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.lang.String.valueOf;

@Service
public class ReportPdfServiceImpl extends ReportAbstract implements ReportPdfService {
    @Override
    public void exportToPDF(HttpServletResponse response, Object data) throws IOException {

        // init response
        response = initResponseForExportPdf(response, "Patients");

        // define paper size
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        // start document
        document.open();

        // title
        Paragraph title = new Paragraph("La liste des Patients", getFontTitle());
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        // subtitel
        Paragraph subtitel = new Paragraph("Report Date : " + DateTime.now().toLocalDate(), getFontSubtitle());
        subtitel.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(subtitel);

        enterSpace(document);

        // table header
//        String[] headers = new String[]{PDFExportAssembler.headers()};
        PdfPTable tableHeader = new PdfPTable(PDFExportAssembler.headers().size());
        writeTableHeaderPdf(tableHeader, PDFExportAssembler.headers());
        document.add(tableHeader);

        // table content
        PdfPTable tableData = new PdfPTable(PDFExportAssembler.headers().size());
        writeTableData(tableData, data);
        document.add(tableData);

        document.close();
    }

    @Override
    public void writeTableData(PdfPTable table, Object data) {
        List<PatientMinDs> list = (List<PatientMinDs>) data;

        // for auto wide by paper  size
        table.setWidthPercentage(100);
        // cell
        PdfPCell cell = new PdfPCell();
//        int number = 0;
        for (PatientMinDs item : list) {
//            number += 1;
//            cell.setPhrase(new Phrase(valueOf(number), getFontContent()));
//            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getCode(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(valueOf(item.getDateAdmission()), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getPrenom(), getFontContent()));
            table.addCell(cell);

//            String active = item.getActive() == 1 ? "Active" : "Non Active";
            cell.setPhrase(new Phrase(item.getNom(), getFontContent()));
            table.addCell(cell);

//            String blocked = item.getBlocked() == 1 ? "Blocked" : "Non Blocked";
            cell.setPhrase(new Phrase(valueOf(item.getDateNaissance()), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(valueOf(item.getTelephone()), getFontContent()));
            table.addCell(cell);
        }

    }
}