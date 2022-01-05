package com.biere.pdf;

import com.biere.entities.Biere;
import com.qkyrie.markdown2pdf.Markdown2PdfConverter;
import com.qkyrie.markdown2pdf.internal.reading.SimpleStringMarkdown2PdfReader;
import com.qkyrie.markdown2pdf.internal.writing.SimpleFileMarkdown2PdfWriter;

import java.io.File;
import java.util.Date;

public class PDF {
    private String fileName;

    public PDF(Biere biere) {
        createPDF(biere);
    }

    public String getFileName() {
        return fileName;
    }

    private void createPDF(Biere biere) {
        Date date = new Date();
        this.fileName = "biere_" + biere.getName() +  "_" + date.getTime();
        String filePath = "./biere/src/main/resources/public/" + this.fileName + ".pdf";

        // Write markdown file
        String stringMD = "";
        stringMD += ("# " + biere.getName() + "\n");
        stringMD += ("## Description\n");
        stringMD += (biere.getDesc() + "\n");
        stringMD += ("## " + biere.getDegree() + "\n");

        File pdf = new File(filePath);

        // Convert markdown to PDF
        try {
            Markdown2PdfConverter
                    .newConverter()
                    .readFrom(new SimpleStringMarkdown2PdfReader(stringMD))
                    .writeTo(new SimpleFileMarkdown2PdfWriter(pdf))
                    .doIt();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
