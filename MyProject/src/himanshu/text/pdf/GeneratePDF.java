package himanshu.text.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePDF {
	public static void main(String[] args) {
		try {
			OutputStream file = new FileOutputStream(new File("C:\\Himanshu\\Personel_docs\\MyDocuments\\Passport\\VISA.pdf"));
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();
			//document.add(new Paragraph("Hello Himanshu"));
			//document.add(new Paragraph(new Date().toString()));
			//document.add(new Paragraph("Booklet Pic"));
			Image img1 = Image.getInstance("C:\\Himanshu\\Personel_docs\\MyDocuments\\Passport\\Manila.jpg");
			img1.scaleAbsolute(400, 300);
			document.add(img1);
			
			/*document.add(new Paragraph("Received Item Pic"));
			Image img2 = Image.getInstance("C:\\ReceivedItem.jpg");
			img2.scaleAbsolute(500, 800);
			document.add(img2);
			
			document.add(new Paragraph("Reciept"));
			Image img3 = Image.getInstance("C:\\Reciept.jpg");
			img3.scaleAbsolute(500, 800);
			document.add(img3);*/
			document.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
