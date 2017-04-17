package domain;

public class testaCalculadora {

	public static void main (String args[]){
		
		Calculadora c  = new Calculadora("255.255.255.192", "192.168.12.1");
		c.setClasse();
		System.out.println("1 ip "+c.getLastIP());
		
		
	}
	
}
