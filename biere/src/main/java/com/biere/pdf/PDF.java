package com.biere.pdf;

import com.biere.entities.Biere;
import com.qkyrie.markdown2pdf.Markdown2PdfConverter;
import com.qkyrie.markdown2pdf.internal.reading.Markdown2PdfReader;
import com.qkyrie.markdown2pdf.internal.writing.SimpleFileMarkdown2PdfWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class PDF {
    private String filePath;

    public PDF(Biere biere) {
        createMarkdown(biere);
        generatePDFFromMarkdown();
    }

    public String getFilePath() {
        return filePath;
    }

    private void createMarkdown(Biere biere) {
        Date date = new Date();
        this.filePath = "/public/biere_" + biere.getName() +  "_" + date.getTime() + ".pdf";

        // create markdown file
        File file = new File(this.filePath);
        try {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // write markdown file
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write("# " + biere.getName() + "\n");
            fileWriter.write("## Description\n");
            fileWriter.write(biere.getDesc() + "\n");
            fileWriter.write("## " + biere.getDegree() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generatePDFFromMarkdown() {
        Markdown2PdfConverter
                .newConverter()
                .writeTo(new SimpleFileMarkdown2PdfWriter(new File(this.filePath)));
    }
}
