package himanshu.text.pdf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import amdocs.jf.common.utils.StringUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

public class SplitPdf {

	public static void main(String[] args) {    
		try {        
			SplitPdf.copyPages(new FileInputStream("C:\\Himanshu\\Personel_docs\\MyDocuments\\LIC.pdf"), 
					new FileOutputStream("C:\\Himanshu\\Personel_docs\\MyDocuments\\LIC2.pdf"), 2, 2);           
		} catch (Exception e) {        
			e.printStackTrace();    
		}
	}
	
	public static void copyPages(InputStream inputStream, OutputStream outputStream, int fromPage, int toPage){ 
		try {            
			PdfReader reader = new PdfReader(inputStream);            
			Document document = new Document();            
			PdfCopy copy = new PdfCopy(document, outputStream);            
			int n = reader.getNumberOfPages();  
			if(toPage==-1) {
				toPage = n;
			}
			document.open();           
			for(int i = fromPage;i<=toPage;i++){                    
				copy.addPage(copy.getImportedPage(reader,i));                
			}           
			document.close();        
		} catch (IOException e) {            
			e.printStackTrace();          
		} catch (DocumentException e) {            
			e.printStackTrace();          
		}    
	}
}
