package Processamento;

public class Main {

	public static void main(String[] args)  {
		
		Excursao teste = new Excursao(1337,18000.0, 7);
		try {
			teste.criarReserva("123", "Filipin do Grau");
			teste.criarReserva("1898", "Filipin do Grau");
		}catch(Exception f){
			System.out.println("Erro ao criar reserva: " + f.getMessage());
		}
		

	}

}
