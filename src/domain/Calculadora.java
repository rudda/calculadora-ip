package domain;

import java.util.ArrayDeque;
import java.util.List;

public class Calculadora {

	private String mascara;
	private String ip;
	private String broadcast;
	private String rede;
	private List<String> ipsValidos;
	private String classe;
	
	
	
	
	
	public String getClasse() {
		return classe;
	}


	
	public String splitIP(String ip){
		
		String total= "";
		for (int i=0; i<ip.length(); i++ ){
			
			char w = ip.charAt(i);
			if(w== '.'){
				total+="##";
			}else{
				
				total += w;
				
			}
		}
		
		return total;
		
	}
	
	public void setClasse() {
		String total= splitIP(this.ip);
		
		String octeto = total.split("##")[0];
		int aux = Integer.parseInt(octeto);
		
		if(aux>=1 && aux <=126 ){
			
			this.classe = "A";
		}
		else if( aux >= 128 && aux <= 191){
			
			this.classe = "B";
		}
		else if( aux>= 192 && aux <=223){
			this.classe = "C";
		}
				
		


	}

	
	public Calculadora(String mascara, String ip) {
		this.mascara = mascara;
		this.ip = ip;
	}

	public String ip2Bin(String ip){
		int ip2 = Integer.parseInt(ip);
		int values[] = new int[8];
		int aux=0;
		String result = "";
		values[0]=128;
		values[1] = 64;
		values[2]= 32;
		values[3]= 16;
		values[4]= 8;
		values[5]= 4;
		values[6] = 2;
		values[7]= 1;
	
		int bin[] = new int[8];
		int index = 0;
		
		for(int q=0; q<8; q++){
			
			bin[q] = 0;
		}
		
		for(int a : values){
		
			if(ip2 >= a){
				
				ip2 = ip2 - a;
				bin[index] = 1;
				
			}
			index++;
			
		}
		
		for(int a : bin){
			
			result += a;
		}
		
		return result;
		
		
		
	}
	
	public String dec2Bin(String dec){
		
		String aux = splitIP(dec);
		String octetos[] = aux.split("#");
		String result = "";
		
		for(int a =0; a< octetos.length; a++){
			String ip = octetos[a];
			
			if(ip.length()>0){
			
				result += ip2Bin(ip);
				
			}
			
		}
		String ok="";
		for(int i=0; i<result.length(); i++){
			
			if(i%8==0 && i>0){
				
				ok +="."; 
				
			}
			ok+= result.charAt(i);
			
		}
		return ok;
	}
		
	public String getMascara() {
				
		
		return mascara;
	}
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}
	public String getIp() {
			
		
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBroadcast() {
	
		String mask = getMascara();
		mask = dec2Bin(mask);
		String ip = getIp();
		ip= splitIP(ip);
		String split[] = ip.split("##");
		int count =0;
		int index =0;
		String realIP="";
		
		for(int i=0; i<mask.length(); i++){
			
			if(mask.charAt(i)== '1'){
				count++;
				if(count ==8){
					
					realIP += ""+split[index]+".";
					index++;
					count =0;
				}
			}
			
		}
		
		
		
		
		mask = getMascara();
		mask = dec2Bin(mask);
		mask = splitIP(mask);
		mask= mask.split("##")[3];
		int valor=128;
		int v=0;
		
		
		for(int i=0; i<mask.length(); i++){
			
			
			if(mask.charAt(i)== '0'){
				
				v+= valor ;
				
				
			} 
				
				valor = valor /2;
			 
			
						
		}
		
		
		broadcast = realIP+ (String.valueOf(v));

		
		return broadcast;
	}
	
	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}
	
	
	 
		
	public int getCIRD(){
		
		String mask = getMascara();
		mask = dec2Bin(getMascara());
		
		int count =0;
		for(int i=0; i<mask.length(); i++){
			
			if(mask.charAt(i) == '1'){
				
				count++;
			}
			
		}
		
		return count;
		
		
	}
	
	public int getHost(){
		
		String mask = getMascara();
		mask = dec2Bin(mask);
		int count = 0;
		for(int a=0; a<mask.length(); a++){
			
			if(mask.charAt(a) == '0'){
				
				count++;
			}
			
		}
		
		 
		return (int) (Math.pow(  2, count) -2);

		
		
	}
		
	public String getFirstIP(){
		
		String mask = getMascara();
		mask = dec2Bin(mask);
		String ip = getIp();
		ip= splitIP(ip);
		String split[] = ip.split("##");
		int count =0;
		int index =0;
		String realIP="";
		for(int i=0; i<mask.length(); i++){
			
			if(mask.charAt(i)== '1'){
				count++;
				if(count ==8){
					
					realIP += ""+split[index]+".";
					index++;
					count =0;
				}
			}
			
		}
		realIP+= "0";
		
		return realIP;
	}
	public String getLastIP(){
		
		String ip = getBroadcast();
		ip = splitIP(ip);
		int aux = Integer.parseInt(ip.split("##")[3]);
		String a[] = ip.split("##");
		String realIP = "";
		String b;
		for(int i=0; i<a.length -1; i++){
			b = a[i];	
			realIP+=b+".";
			
		}
		
		realIP+= ""+(aux -1);
		
		
		
		return realIP;
				
	}
	
	
	public int getSubNet(){
		String mask = getMascara();
		mask = dec2Bin(mask);

		System.out.println(mask);
		mask = splitIP(mask);
		String octetos[] = mask.split("##");
		int countOcteto=0;
		int count=0;
		
		
		
		for(int i=0; i<octetos.length; i++){
			
			for(int j=0; j<octetos[i].length(); j++){
				if(octetos[i].charAt(j)== '1'){
					countOcteto++;
				}
				
			}
			
			if(countOcteto <8){
				
				for(int k=0; k< octetos[i].length(); k++){
					
					if(octetos[i].charAt(k)== '1'){
						
						count++;
					}
					
				}
				
			}
			
			countOcteto = 0;
		}
		
		System.out.println(count);
		
		return (int) Math.pow(2, count);
		
				
	}
	
	
}
