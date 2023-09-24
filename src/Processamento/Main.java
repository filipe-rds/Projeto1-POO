package Processamento;

public class Main {

	public static void main(String[] args)  throws Exception{
		
		Excursao teste = new Excursao(1337,18000.0, 10);
		try {
			teste.criarReserva("123","Felipe");
			teste.cancelarReserva("123","Felipe"); 
			
			;
		}catch(Exception f){
			
			System.out.println("Erro ao criar reserva: " + f.getMessage());
		}
		
		
		try {
			
			teste.criarReserva("123","Felipe"); 
			teste.cancelarReserva("124"); 
			   
			 
		}
		
		catch(Exception e) {
			System.out.println("Erro cancelar reserva: " + e.getMessage());
		}
		
		
		
	}
	
	

}