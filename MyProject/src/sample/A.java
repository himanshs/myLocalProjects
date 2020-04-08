package sample;

public class A {
public static void main(String[] args) {
	/*C c = new B();
	c.add();

	B b = new B();
	b.add();
	C b1 = new C();
	b1.add();
	
	float f = (float)99.9;*/
	
	StringBuffer  sb = new StringBuffer("A");
	String s1 = new String("A");
	
	if(s1.equals(sb)) {
		System.out.println(" s1 true");
	} else {
		System.out.println("s1 false");
	}
	
	if(sb.toString().equals(s1)) {
		System.out.println(" sb true");
	}else {
		System.out.println(" sb false");
	}
	
}
}

class B extends C {
	B()
	{
		System.out.println("B");
	}
	void add()
	{
		System.out.println("In B");
	}
}

class C  {
	
	C(){
		System.out.println("C");
	}

	void add()
	{
		System.out.println("In C");
	}
}
