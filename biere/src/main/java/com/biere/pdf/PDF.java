package com.biere.pdf;

import com.biere.entities.Biere;
import com.qkyrie.markdown2pdf.Markdown2PdfConverter;
import com.qkyrie.markdown2pdf.internal.exceptions.ConversionException;
import com.qkyrie.markdown2pdf.internal.exceptions.Markdown2PdfLogicException;
import com.qkyrie.markdown2pdf.internal.reading.Markdown2PdfReader;
import com.qkyrie.markdown2pdf.internal.reading.SimpleStringMarkdown2PdfReader;
import com.qkyrie.markdown2pdf.internal.writing.SimpleFileMarkdown2PdfWriter;

import java.io.*;
import java.util.Date;

public class PDF {
    private String filePathMD;
    private String filePathPDF;

    public PDF(Biere biere) {
        createMarkdown(biere);
        generatePDFFromMarkdown();
    }

    public String getFilePath() {
        return filePathPDF;
    }

    private void createMarkdown(Biere biere) {
        Date date = new Date();
        String filePath = "./biere/src/main/resources/public/biere_" + biere.getName() +  "_" + date.getTime();
        this.filePathMD = filePath + ".md";
        this.filePathPDF = filePath + ".pdf";

        // create markdown file
        File file = new File(this.filePathMD);
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
            FileWriter fileWriter = new FileWriter(this.filePathMD);
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
                .readFrom(new SimpleStringMarkdown2PdfReader((new File(this.filePathMD)).toString()))
                .writeTo(new SimpleFileMarkdown2PdfWriter(new File(this.filePathPDF)));
    }
}
