package domain;

public class testaCalculadora {

	public static void main (String args[]){
		
		Calculadora c  = new Calculadora("255.0.0.0", "192.0.0.1");
		c.setClasse();
		System.out.println(c.dec2Bin("192.168.0.10"));
		
		
	}
	
}
