package com.biere.pdf;

import com.biere.entities.Biere;
import com.qkyrie.markdown2pdf.Markdown2PdfConverter;
import com.qkyrie.markdown2pdf.internal.reading.SimpleStringMarkdown2PdfReader;
import com.qkyrie.markdown2pdf.internal.writing.SimpleFileMarkdown2PdfWriter;

import java.io.File;
import java.util.Date;

public class PDF {
    private String filePath;

    public PDF(Biere biere) {
        System.out.println(biere);
        createPDF(biere);
    }

    public String getFilePath() {
        return filePath;
    }

    private void createPDF(Biere biere) {
        Date date = new Date();
        this.filePath = "./biere/src/main/resources/public/biere_" + biere.getName() +  "_" + date.getTime() + ".pdf";

        // Write markdown file
        String stringMD = "";
        stringMD += ("# " + biere.getName() + "\n");
        stringMD += ("## Description\n");
        stringMD += (biere.getDesc() + "\n");
        stringMD += ("## " + biere.getDegree() + "\n");

        File pdf = new File(filePath);

        System.out.println(pdf.getAbsolutePath());

        // Convert markdown to PDF
        try {
            Markdown2PdfConverter
                    .newConverter()
                    .readFrom(new SimpleStringMarkdown2PdfReader(stringMD))
                    .writeTo(new SimpleFileMarkdown2PdfWriter(pdf));

            boolean pdfExists = pdf.createNewFile();
            if (pdfExists) {
                System.out.println("PDF created");
            } else {
                System.out.println("File not found");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
