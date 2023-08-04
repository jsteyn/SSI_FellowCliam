package com.jannetta.SSI_FellowClaim.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.jannetta.SSI_FellowClaim.model.AllSections;
import com.jannetta.SSI_FellowClaim.model.Expense;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MakeDocument {
    public static String INPUT = "data/document.html";
    public static String OUTPUT = "data/output.pdf";

    /**
     * Create the PDF
     * @param signature
     * @param dest
     * @param allSections
     * @throws IOException
     */
    public static void print(String signature, String dest, AllSections allSections) throws IOException {

        // PAGES 1 TO 2
        ConverterProperties properties = new ConverterProperties();

        PdfWriter writer = new PdfWriter(dest, new WriterProperties().setFullCompressionMode(true));
        PdfDocument pdf = new PdfDocument(writer);
        pdf.setDefaultPageSize(PageSize.A4.rotate());

        Path filePath = Path.of(INPUT);
        String html_content = Files.readString(filePath);

        // REPLACE ALL VARIABLES IN HTML (&varname&)
        html_content = html_content.replaceAll("&fullName&", allSections.getPersonalDetails().getFullName());
        html_content = html_content.replaceAll("&address&", allSections.getPersonalDetails().getAddress());
        html_content = html_content.replaceAll("&number&", allSections.getPersonalDetails().getNumber());
        html_content = html_content.replaceAll("&bacs_email&", allSections.getPersonalDetails().getBacs_email());

        html_content = html_content.replaceAll("&bank&", allSections.getBankDetails().getBank());
        html_content = html_content.replaceAll("&branch&", allSections.getBankDetails().getBranch());
        html_content = html_content.replaceAll("&account&", allSections.getBankDetails().getAccount());
        html_content = html_content.replaceAll("&iban&", allSections.getBankDetails().getIban());
        html_content = html_content.replaceAll("&sortCode&", allSections.getBankDetails().getSortCode());
        html_content = html_content.replaceAll("&swift&", allSections.getBankDetails().getSwift());

        html_content = html_content.replaceAll("&travelTotal&", String.format("%12.2f",allSections.getExpenses().getTravelTotal()));
        html_content = html_content.replaceAll("&mileageTotal&", String.format("%12.2f",allSections.getExpenseSummary().getMileageTotal()));
        html_content = html_content.replaceAll("&subsistence&", String.format("%12.2f",(allSections.getExpenses().getSubsistenceTotal() + allSections.getExpenses().getOtherTotal())));
        html_content = html_content.replaceAll("&total&",
                String.format("%12.2f",(allSections.getExpenses().getGrandTotal())));
        html_content = html_content.replaceAll("&currency&", allSections.getExpenseSummary().getCurrency());
        html_content = html_content.replaceAll("&visit&", allSections.getExpenseSummary().getVisit());
        html_content = html_content.replaceAll("&signature&", allSections.getExpenseSummary().getSignatureFile());
        html_content = html_content.replaceAll("&date&", allSections.getExpenseSummary().getDate());
        html_content = html_content.replaceAll("&purpose&", allSections.getExpenseSummary().getPurpose());
        html_content = html_content.replaceAll("&signedDate&", allSections.getExpenseSummary().getSignedDate());
        html_content = html_content.replaceAll("&perMile&", String.format("%12.2f",allSections.getExpenseSummary().getPerMile()));
        HtmlConverter.convertToPdf(html_content, pdf, properties);
        System.out.println(String.format("%12.2f",(allSections.getExpenses().getTravelTotal() + allSections.getExpenses().getSubsistenceTotal())));

        // PAGE 6
        String[] tableTitleList = {"Description of Expenditure (also identify here if payment is done in cash)",
                "Expense type Travel, Subsistence, Other", "amount (& currency)", "Claimed Amount in GBP", "Receipt #"};
        makeTablePages(allSections.getExpenses(), allSections.getExpenses().getGrandTotal(), tableTitleList,
                "data/expenseTable1.pdf", "List expenses in this table if expenses are to be claimed in a GBP account");

        String[] tableTitleList2 = {"Description of Expenditure (also identify here if payment is done in cash)",
                "Expense type Travel, Subsistence, Other", "Claimed\n" +
                "Amount (&\n" +
                "currency)", "Amount in\n" +
                "GBP (if\n" +
                "known)", "Receipt #"};
        makeTablePages(new ArrayList<Expense>(), 0, tableTitleList2, "data/expenseTable2.pdf",
                "List expenses in this table if expenses are to be claimed in a Non-GBP account");

        // PAGES 3 TO 5
        String OUTPUT_FOLDER = "data/";
        PdfDocument pdfDocument1 = new PdfDocument(new PdfReader("data/output.pdf"),
                new PdfWriter(OUTPUT_FOLDER + "merged.pdf"));
        PdfDocument pdfDocument2 = new PdfDocument(new PdfReader("data/p3_to_p5.pdf"));
        PdfDocument pdfDocument3 = new PdfDocument((new PdfReader("data/expenseTable1.pdf")));
        PdfDocument pdfDocument4 = new PdfDocument((new PdfReader("data/expenseTable2.pdf")));
        PdfMerger merger = new PdfMerger(pdfDocument1);
        merger.merge(pdfDocument2, 1, pdfDocument2.getNumberOfPages());
        merger.merge(pdfDocument3, 1, pdfDocument3.getNumberOfPages());
        merger.merge(pdfDocument4, 1, pdfDocument4.getNumberOfPages());
        pdfDocument2.close();
        pdfDocument1.close();
        pdfDocument3.close();
        pdfDocument4.close();

        //Create a PdfDocument object
        com.spire.pdf.PdfDocument doc = new com.spire.pdf.PdfDocument();
        //Load the sample PDF file
        doc.loadFromFile("data/merged.pdf");
        doc.saveToFile("data/merged.docx", com.spire.pdf.FileFormat.DOCX);
        doc.close();
    }

    /**
     *
     * @param arrayList An arraylist of values to be printed in the table
     * @param grandTotal The total of the column containing the amount to be cliamed
     * @param tableTitleList The title of the page
     * @param filename
     * @param tableHeading
     * @throws IOException
     */
    private static void makeTablePages(ArrayList arrayList, double grandTotal, String[] tableTitleList, String filename, String tableHeading) throws IOException {
        // PAGE 6
        DeviceRgb lighterGray = new DeviceRgb(230,230,230);
        DeviceRgb darkerGray = new DeviceRgb(200,200,200);
        DeviceRgb yellowGray = new DeviceRgb(237, 235, 224);

        String expenseTable = filename;
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        File file = new File(expenseTable);
        PdfDocument pdf = new PdfDocument(new PdfWriter(expenseTable));
        Document doc = new Document(pdf);
        Table heading1 = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        Paragraph heading1par = new Paragraph().setFont(bold).setTextAlignment(TextAlignment.CENTER).
                setFontSize(14).
                setBackgroundColor(darkerGray).
                setHorizontalAlignment(HorizontalAlignment.CENTER).add("List of expenditure - Non staff/student\n expense claim");
        heading1.addCell(heading1par).setBackgroundColor(com.itextpdf.kernel.colors.Color.convertRgbToCmyk(darkerGray)).
                setBackgroundColor(darkerGray);
        Paragraph spacer = new Paragraph().add("\n");
        Table heading2 = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        Paragraph heading2par = new Paragraph().setFont(bold).setTextAlignment(TextAlignment.CENTER).
                setFontSize(14).
                setHorizontalAlignment(HorizontalAlignment.CENTER).add(tableHeading);
        heading2.addCell(heading2par).setBackgroundColor(lighterGray);
        Table tableGBPHeadings = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
        DeviceRgb c;
        for ( int s = 0; s < tableTitleList.length; s++) {

            if (s==3) c = yellowGray;
            else c = lighterGray;
            tableGBPHeadings.addCell(new Cell().add(new Paragraph(tableTitleList[s])).setFont(bold).
                    setBackgroundColor(com.itextpdf.kernel.colors.Color.
                            convertRgbToCmyk(c)));
        }
        Table tableGBP = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
        ArrayList<Expense> expenses = arrayList;
        expenses.forEach(expense -> {
            tableGBP.addCell(new Cell().add(new Paragraph(expense.getDescription())));
            tableGBP.addCell(new Cell().add(new Paragraph(expense.getExpenseType())));
            tableGBP.addCell(new Cell().add(new Paragraph(String.format("%12.2f",expense.getAmount_currency()))));
            tableGBP.addCell(new Cell().add(new Paragraph(String.format("%12.2f",expense.getAmount_gbp()))).
                    setBackgroundColor(com.itextpdf.kernel.colors.Color.
                            convertRgbToCmyk(yellowGray)));
            tableGBP.addCell(new Cell().add(new Paragraph(String.valueOf(expense.getReceipt_number()))));
        });
        if (expenses.size() < 15) {
            for (int i = expenses.size(); i < 15; i++) {
                for (int j = 0; j < 4; j++) {
                    if (j == 3)
                        tableGBP.addCell(new Cell().add(new Paragraph("")).
                                setBackgroundColor(com.itextpdf.kernel.colors.Color.
                                        convertRgbToCmyk(yellowGray)));
                    else
                        tableGBP.addCell(new Cell().add(new Paragraph("")));
                }
                tableGBP.addCell((new Cell().add(new Paragraph(String.valueOf(i + 1)))));
            }
        }
        tableGBP.addCell((new Cell(1,2).add(new Paragraph("Total"))));
        tableGBP.addCell((new Cell().add(new Paragraph(""))));
        tableGBP.addCell((new Cell().add(new Paragraph(String.format("%12.2f",grandTotal))).
                setBackgroundColor(com.itextpdf.kernel.colors.Color.
                        convertRgbToCmyk(yellowGray))));
        tableGBP.addCell((new Cell().add(new Paragraph(""))));

        doc.add(heading1par);
        doc.add(spacer);
        doc.add(heading2);
        doc.add(tableGBPHeadings);
        doc.add(tableGBP);
        doc.add(spacer);
        doc.close();

    }
}
