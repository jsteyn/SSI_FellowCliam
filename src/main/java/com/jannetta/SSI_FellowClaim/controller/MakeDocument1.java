package com.jannetta.SSI_FellowClaim.controller;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;

public class MakeDocument1 {

    public static final String UE = "UniversityOfEdinburgh.png";

    public static void print(String signature, String dest) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(13, 11, 24, 15);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        Image ue = new Image(ImageDataFactory.create(UE));
        Paragraph p1 = new Paragraph().setFont(bold).setTextAlignment(TextAlignment.CENTER).
                setFontSize(18).
                setHorizontalAlignment(HorizontalAlignment.CENTER).add("Payment of Non-Staff Expenses");
        //table.addCell(ue.scale(0.5f,.5f));
        table.addCell(p1);
        Paragraph p2 = new Paragraph().setFontSize(10).
                setFont(bold).
                setHorizontalAlignment(HorizontalAlignment.LEFT).
                add("Guidance");
        table.addCell(p2);
        Paragraph p3 = new Paragraph().setFontSize(10).
                setFont(font).
                setHorizontalAlignment(HorizontalAlignment.LEFT).
                add("Before completing this form, please refer to the conditions on pages 4-5.\n\nThe Finance Department will review claims for compliance with these conditions. The responsibility of the claimant and the authoriser is to ensure that, to the\n" +
                        "best of their knowledge, the claim is compliant with the conditions listed. Any unusual or significant items will be referred by Finance to Internal Audit for\n" +
                        "investigation.\n\nFor advice on the conditions of claiming expenses, please contact finance.helpline@ed.ac.uk.");
        table.addCell(p3);
        document.add(table);
        document.close();
    }
}
