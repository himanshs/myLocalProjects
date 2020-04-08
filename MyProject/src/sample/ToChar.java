package sample;

public class ToChar {
public static void main(String[] args) {
	byte temp = 67;
	
	System.out.println(Byte.valueOf(temp).toString());
	System.out.println(String.valueOf((char)temp));
}
}
