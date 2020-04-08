package sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.helpers.ISO8601DateFormat;

public class MyJacksonDateFormat {
	
	public static void main(String[] args) {
		Date myDate = new Date();
		
		DateFormat jdf = new ISO8601DateFormat();
		System.out.println(jdf.format(myDate));

		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS", Locale.getDefault());		
		System.out.println(df2.format(myDate));
		
	}
	

}
