package sample;

import java.io.File;
import java.util.Date;

public class Test {
		
	public static void main(String[] args) {
		/*//Integer a = new Integer(10);
		Test t = new Test();
		int[] a = {10,20};
		InnerTest iner = t.new InnerTest();
		iner.checkData(a);
		t.printData(a);	
		//a= null;
		//printData(a);
		
		String s = "ONE";
		
		switch (s) {
			case "ONE":
				System.out.println("This is 1");
			case "TWO":
				System.out.println("This is 2");
			case "THREE":
				System.out.println("This is 3");
			default:
				break;
		}*/
		//Test t = new Test();
		
		//Date myDate = new Date();
		//t.printDate(myDate);
		String t1= "2150400";
		//System.out.println(t1.substring(0));
		System.out.println(Long.parseLong(t1.substring(0)));
		
		//System.out.println(File.separator);
	}

	private void printData(int [] a) {
		System.out.println(a);
		System.out.println(a[0]);
		System.out.println(a[1]);
	}

	private void printDate(String d) {
		
		System.out.println(d);
		
	}
	
	class InnerTest {
		private void checkData(int [] a) {
			
			a = new int[]{10,30};
		}
	}

}
