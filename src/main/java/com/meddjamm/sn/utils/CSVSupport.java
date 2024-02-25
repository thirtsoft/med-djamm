package com.meddjamm.sn.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.apache.commons.csv.CSVFormat.EXCEL;

public class CSVSupport {
    public static final String DELIMITER = ";";

    public static InputStreamResource generate(String[] csvHeader, List<List<String>> csvBody) {
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVFormat csvFormat = CSVFormat.Builder.create(EXCEL).setHeader(csvHeader).setDelimiter(DELIMITER).build();

        try (CSVPrinter csvPrinter = getCsvPrinter(out, csvFormat)) {
            byteArrayInputStream = printBody(csvBody, out, csvPrinter);
        } catch (IOException e) {
            throw new RuntimeException("Invalid CSV document", e);
        }

        return new InputStreamResource(byteArrayInputStream);
    }

    protected static CSVPrinter getCsvPrinter(ByteArrayOutputStream out, CSVFormat csvFormat) throws IOException {
        return new CSVPrinter(new PrintWriter(out), csvFormat);
    }

    protected static ByteArrayInputStream printBody(List<List<String>> csvBody, ByteArrayOutputStream out, CSVPrinter csvPrinter)
            throws IOException {
        for (List<String> csvRecord : csvBody) {
            csvPrinter.printRecord(csvRecord);
        }
        csvPrinter.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }
}