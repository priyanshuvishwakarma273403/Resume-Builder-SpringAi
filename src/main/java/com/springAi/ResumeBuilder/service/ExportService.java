package com.springAi.ResumeBuilder.service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ExportService {

    public byte[] generatePdf(String content) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font bodyFont = new Font(Font.HELVETICA, 11, Font.NORMAL);

            document.add(new Paragraph("AI Resume Export", titleFont));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(content, bodyFont));
            document.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF");
        }
    }

    public byte[] generateDocx(String content) {
        try (XWPFDocument document = new XWPFDocument();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            XWPFParagraph title = document.createParagraph();
            XWPFRun titleRun = title.createRun();
            titleRun.setText("AI Resume Export");
            titleRun.setBold(true);
            titleRun.setFontSize(18);

            XWPFParagraph body = document.createParagraph();
            XWPFRun bodyRun = body.createRun();
            bodyRun.setText(content);

            document.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate DOCX");
        }

    }

}



