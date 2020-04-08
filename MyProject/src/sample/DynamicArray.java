package sample;

import java.util.ArrayList;

public class DynamicArray {
  
  
  public static void main(String[] args) {
	 
	 ArrayList<String[]> myList = new ArrayList<String[]>();
	  
	  String child [] = new String []{   "1",   "2",   "3",   "4"   };
	  myList.add(child);
	 
	  child = new String []{   "1",   "2",   "3"   };
	  myList.add(child);
	  
	  child = new String []{   "1",   "2",   "3", "4", "5" };
	  myList.add(child);
	  
	   String finalArr [][] = new String[myList.size()][];
	   for (int i = 0; i < finalArr.length; i++) {
		   finalArr[i] = (String [])myList.get(i);
	   }
	  
	  for (int i = 0; i < finalArr.length; i++) {
		  String tempArr [] = finalArr[i];
		for (int j = 0; j < tempArr.length; j++) {
			System.out.print(tempArr[j]+" ");
		}
		System.out.println();
	}
}
}
