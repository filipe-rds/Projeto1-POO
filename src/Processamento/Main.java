package Processamento;

public class Main {

	public static void main(String[] args)  {
		
		Excursao teste = new Excursao(1337,18000.0, 10);
		try {
			teste.criarReserva("123","Felipe");
			teste.criarReserva("123","Felip");
			teste.criarReserva("123","Feli");
			;
		}catch(Exception f){
			
			System.out.println("Erro ao criar reserva: " + f.getMessage());
		}
		
		try {
			teste.cancelarReserva("123");
		}
		
		catch(Exception e) {
			System.out.println("Erro cancelar reserva: "+e.getMessage());
		}
		
		try {
			teste.criarReserva("123","Felipe");
			teste.criarReserva("123","Felip");
			teste.criarReserva("123","Feli");
			
			;
		}catch(Exception f){
			
			System.out.println("Erro ao criar reserva: " + f.getMessage());
		}
		
		try {
			teste.cancelarReserva("123");
		}
		
		catch(Exception e) {
			System.out.println("Erro cancelar reserva: "+e.getMessage());
		}
		
		try {
			teste.cancelarReserva("123");
		}
		
		catch(Exception e) {
			System.out.println("Erro cancelar reserva: "+e.getMessage());
		}
		
	}
	
	

}
