package Processamento;

import java.util.ArrayList;

public class Excursao {
	// Attributes
	private int codigo;
	private double precoExcursao;
	private int maxReservas;
	private ArrayList<String> listaReserva = new ArrayList<>();
	
	// Constructors
	public Excursao(int codigo, double preçoExcursao, int maxReservas) {
		this.codigo = codigo;
		this.precoExcursao = preçoExcursao;
		this.maxReservas = maxReservas;
		listaReserva = new ArrayList<>();
	}
	
	public Excursao(int codigo) {
		this.codigo = codigo;
		listaReserva = new ArrayList<>();
	}

	// Getters and Setters
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getPrecoExcursao() {
		return precoExcursao;
	}
	public void setPrecoExcursao(double precoExcursao) {
		this.precoExcursao = precoExcursao;
	}
	public int getMaxReservas() {
		return maxReservas;
	}
	public void setMaxReservas(int maxReservas) {
		this.maxReservas = maxReservas;
	}

	public ArrayList<String> getListaReserva() {
		return listaReserva;
	}

	public void setListaReserva(ArrayList<String> listaReserva) {
		this.listaReserva = listaReserva;
	}
	
	// Methods
	
	public void criarReserva(String cpf,String nome)throws Exception {
	// Adiciona uma reserva “cpf/nome”
	// Verificar se existe um nome igual na lista
		if(listaReserva.size()< maxReservas) {
			for (int i = 0; i < listaReserva.size(); i++ ) {
				String temp = listaReserva.get(i);
				String [] separacao = temp.split("/"); // ["cpf","nome"] 
				if(separacao[1].equals(nome)) {
					throw new Exception("Nome já está cadastrado");
				}
			}
			
			listaReserva.add(cpf + "/" + nome);
			
		}
		else{
			throw new Exception("Lista de Reserva está preenchida");
		}
	}
//	public void cancelarReserva(String cpf, String nome) {
//	// Remove uma reserva “cpf/nome”
//	
//	}
//	
//	public void cancelarReserva(String cpf) {
//	// Remove todas as reservas do cpf
//	}
//	
//	
//	
//	public double calcularValorTotal() {
//	// Calcular valor total da excursão = preço * qde de reservas
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
