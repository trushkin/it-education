package by.bsuir.client.service;

import by.pojo.SchedulePrint;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;


public class BuildReport {
    private static final String reportsFolder = "";

    public static void createReport(ArrayList<SchedulePrint> schedulePrintArrayList, LocalDate localDate) throws Exception {

        String filePath = reportsFolder + "Schedule " + localDate.getDayOfMonth() + localDate.getMonthValue() + localDate.getYear()+ ".pdf";
        Document document = new Document(PageSize.A4);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        BaseFont baseFont = BaseFont.createFont("Font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);


        var title = new Paragraph("Записи на " + localDate.getDayOfMonth() +"."+ localDate.getMonthValue()+"." + localDate.getYear(), new Font(baseFont, 18));
        title.setAlignment(Element.ALIGN_CENTER);

        float columnWidth = 520F;
        float[] columns = {columnWidth, columnWidth};

        PdfPTable table = new PdfPTable(columns);
        table.setSpacingBefore(40F);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        var plateNum = new Paragraph("Номер машины", new Font(baseFont, 14));
        plateNum.setAlignment(Element.ALIGN_CENTER);
        var plateNumCell = new PdfPCell(plateNum);

        var details = new Paragraph("Информация о записи", new Font(baseFont, 14));
        details.setAlignment(Element.ALIGN_CENTER);
        var detailsCell = new PdfPCell(details);

        table.addCell(plateNumCell);
        table.addCell(detailsCell);


        for (SchedulePrint temp : schedulePrintArrayList) {

            if (localDate.getYear() == temp.getStartDate().getYear() && localDate.getMonthValue() == temp.getStartDate().getMonthValue() && localDate.getDayOfMonth() == temp.getStartDate().getDayOfMonth()){
                table.addCell(new Paragraph(temp.getStateNum(), new Font(baseFont, 12)));
                table.addCell(new Paragraph(temp.getComment(),new Font(baseFont, 12)));
            }

        }

        document.open();

        document.add(title);
        document.add(table);

        document.close();
        pdfWriter.close();

    }


}
