package com.meddjamm.sn.utils;


import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import jakarta.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class ReportAbstract {

    // ----------------------
    // REPORT PDF
    // ----------------------
    public HttpServletResponse initResponseForExportPdf(HttpServletResponse response, String fileName) {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + fileName + "_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        return response;
    }

    public void writeTableHeaderPdf(PdfPTable table, List<String> headers) {

        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell();
        //  En-tête
        for (String header : headers) {
            cell.setPhrase(new Phrase(header, getFontContent()));
            table.addCell(cell);
        }
    }

    public Font getFontTitle() {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        return font;
    }

    public Font getFontSubtitle() {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);
        return font;
    }

    public Font getFontContent() {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(10);
        return font;
    }

    public void enterSpace(Document document) {
        Paragraph space = new Paragraph(" ", getFontSubtitle());
        space.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(space);
    }
}