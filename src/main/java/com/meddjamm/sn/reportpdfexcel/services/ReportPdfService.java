package com.meddjamm.sn.reportpdfexcel.services;

import com.lowagie.text.pdf.PdfPTable;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ReportPdfService {
    void exportToPDF(HttpServletResponse response, Object data) throws IOException;


    void writeTableData(PdfPTable table, Object data);
}