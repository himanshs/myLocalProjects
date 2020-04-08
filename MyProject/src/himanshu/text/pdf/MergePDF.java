package himanshu.text.pdf;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class MergePDF {

	/*public static void main(String[] args) {
		try {
			List<InputStream> pdfs = new ArrayList<InputStream>();
			pdfs.add(new FileInputStream("C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page1.pdf"));
			pdfs.add(new FileInputStream("C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page2.pdf"));
			OutputStream output = new FileOutputStream("c:\\merge.pdf");
			MergePDF.concatPDFs(pdfs, output, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	 public static void main(String[] args) throws IOException, DocumentException, SQLException {
     // using previous examples to create PDFs
 	 
		 String[] files = {"C:\\Himanshu\\Personel_docs\\MedicalClaim\\MedicalBill_1.pdf"
				 		 , "C:\\Himanshu\\Personel_docs\\MedicalClaim\\MedicalBill_2.pdf"};
     /*String[] files = { "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page1.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page2.pdf" 
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page3.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page4.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page5.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page6.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page7.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page8.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page9.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page10.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page11.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page12.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page13.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page14.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page15.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page16.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page17.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page18.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page19.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page20.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page21.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page22.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page23.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page24.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page25.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page26.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page27.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page28.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page29.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page30.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page31.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page32.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page33.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page34.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page35.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page36.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page37.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page38.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page39.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page40.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page41.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page42.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page43.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page44.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page45.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page46.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page47.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page48.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page49.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page50.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page51.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page52.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page53.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page54.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page55.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page56.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page57.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page58.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page59.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page60.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page61.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page62.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page63.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page64.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page65.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page66.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page67.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page68.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page69.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page70.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page71.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page72.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page73.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page74.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page75.pdf"
    		 		, "C:\\Himanshu\\Personel docs\\Flat\\Agreement\\Page76.pdf"
    		 		}; */
     // step 1
     Document document = new Document();
     // step 2
     PdfCopy copy = new PdfCopy(document, new FileOutputStream("C:\\Himanshu\\Personel_docs\\MedicalClaim\\MedicalBill.pdf"));
     // step 3
     document.open();
     // step 4
     PdfReader reader;
     int n;
     // loop over the documents you want to concatenate
     for (int i = 0; i < files.length; i++) {
         reader = new PdfReader(files[i]);
         // loop over the pages in that document
         n = reader.getNumberOfPages();
         for (int page = 0; page < n; ) {
             copy.addPage(copy.getImportedPage(reader, ++page));
         }
         copy.freeReader(reader);
         reader.close();
     }
     // step 5
     document.close();
 }


	/*public static void concatPDFs(List<InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate) {         
		Document document = new Document();        
	try {            
		List<InputStream> pdfs = streamOfPDFFiles;            
		List<PdfReader> readers = new ArrayList<PdfReader>();            
		int totalPages = 0;            
		Iterator<InputStream> iteratorPDFs = pdfs.iterator();             
		// Create Readers for the pdfs           
		while (iteratorPDFs.hasNext()) {                
			InputStream pdf = iteratorPDFs.next();                
			PdfReader pdfReader = new PdfReader(pdf);                
			readers.add(pdfReader);                
			totalPages += pdfReader.getNumberOfPages();            
		}            
		// Create a writer for the outputstream            
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);             
		document.open();            
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252, BaseFont.NOT_EMBEDDED);            
		PdfContentByte cb = writer.getDirectContent(); 
		// Holds the PDF            
		// data             
		PdfImportedPage page;            
		int currentPageNumber = 0;            
		int pageOfCurrentReaderPDF = 0;            
		Iterator<PdfReader> iteratorPDFReader = readers.iterator(); 
		
		// Loop through the PDF files and add to the output.            
		while (iteratorPDFReader.hasNext()) {                
			PdfReader pdfReader = iteratorPDFReader.next(); 
			// Create a new page in the target for each source page.                
			while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {                    
				document.newPage();                    
				pageOfCurrentReaderPDF++;                    
				currentPageNumber++;                    
				document.setPageSize(pdfReader.getPageSize(pageOfCurrentReaderPDF));
				
				page = writer.getImportedPage(pdfReader,pageOfCurrentReaderPDF);
				cb.addTemplate(page,10, 0);                     
				// Code for pagination.                    
				if (paginate) {                        
					cb.beginText();                        
					cb.setFontAndSize(bf, 9);                        
					cb.showTextAligned(PdfContentByte.ALIGN_CENTER, ""  + currentPageNumber + " of " + totalPages, 520, 5, 0); 
					cb.endText();       
				}                
			}                
			pageOfCurrentReaderPDF = 0;        
		}            
		outputStream.flush();            
		document.close();            
		outputStream.close();        
		} catch (Exception e) {            
			e.printStackTrace();       
		} finally {           
			if (document.isOpen())                
				document.close();           
			try {                
				if (outputStream != null)     
					outputStream.close();        
				} catch (IOException ioe) {            
					ioe.printStackTrace();         
				}       
			}   
		}*/
}
