import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EncodingTest {
	public static void main(String[] args) throws ParseException {
		String name = "林錦誼";
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date date1 = new Date(82994796000483L);
		Date date2 = new Date(82994796000483L);
		//Date date = sf.parse("46000101 03:30:00");
		//Date date1 = sf.parse("46000101 03:00:00");
		//Date date2 = sf.parse("46000101 03:00:00");
		//Date date2 = sf.parse(sf.format(date));
		//System.out.println(date);
		System.out.println(date1);
		System.out.println(date2);
		
		if(date1.equals(date2)) {
			//System.out.println(date.getTime());
			System.out.println(date1.getTime());
			System.out.println(date2.getTime());
			System.out.println("dates are equal");
		}
		else {
			System.out.println("dates are not equal");
			//System.out.println(date.getTime());
			System.out.println(date1.getTime());
			System.out.println(date2.getTime());
			
			
		}
/*System.out.println("************************************");		
		if(date1.equals(date2)) {
			System.out.println(date1.getTime());
			System.out.println(date2.getTime());
			System.out.println("dates are equal");
		}
		else {
			System.out.println("dates are not equal");
			System.out.println(date1.getTime());
			System.out.println(date2.getTime());
			
			
		}
		
 */
	}	
	
}
