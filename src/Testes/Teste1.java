package Testes;

import Processamento.Excursao;

/**
 * TSI - POO - Prof. Fausto projeto1
 */

public class Teste1 {
	public static void main(String[] args)throws Exception {
		try {
			Excursao excursao = new Excursao(1234, 100.0, 20); // ok
			System.out.println("excursao:" + excursao); // ok

			excursao.criarReserva("111", "joao");
			excursao.criarReserva("222", "maria");
			excursao.criarReserva("333", "jose");
			excursao.criarReserva("333", "paulo");
			excursao.criarReserva("333", "ana");
			excursao.criarReserva("555", "antonio");
			excursao.criarReserva("555", "joana");

			System.out.println("\nlistar todas as reservas");
			System.out.println(excursao.listarReservasPorCpf(""));  //ok
			System.out.println("\nlistar as reservas por cpf");
			System.out.println(excursao.listarReservasPorCpf("3")); //ok
			System.out.println("\nlistar as reservas por nome");
			System.out.println(excursao.listarReservasPorNome("jo")); //ok 

			//excursao.cancelarReserva("555", "claudia");
			excursao.cancelarReserva("333"); //ok

			System.out.println("\nlistar todas as reservas");
			System.out.println(excursao.listarReservasPorCpf(""));
			System.out.println("\nexcursao:" + excursao);
			System.out.println("\ntotal=" + excursao.calcularValorTotal());
		} 
		catch (Exception erro) {
			System.out.println("-->" + erro.getMessage());
		}

	}
}