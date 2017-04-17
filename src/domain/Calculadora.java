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
		return broadcast;
	}
	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}
	public String getRede() {
		return rede;
	}
	public void setRede(String rede) {
		this.rede = rede;
	}
	public List<String> getIpsValidos() {
		return ipsValidos;
	}
	public void setIpsValidos(List<String> ipsValidos) {
		this.ipsValidos = ipsValidos;
	}
		
	
	
	
}
