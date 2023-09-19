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
			throw new Exception("Limite excedido");
		}
	}
	// Methods

	 public void cancelarReserva(String cpf, String nome) throws Exception {
	        boolean encontrou = false;
	        boolean cpfInvalido = true;
	        boolean nomeInvalido = true;

	        for (int i = 0; i < listaReserva.size(); i++) {
	            String reserva = listaReserva.get(i);
	            String[] separacao = reserva.split("/");

	            if (separacao[0].equals(cpf) && separacao[1].equals(nome)) {
	                listaReserva.remove(i);
	                encontrou = true;
	                break;
	            } else if (separacao[0].equals(cpf)) {
	                cpfInvalido = false;
	            } else if (separacao[1].equals(nome)) {
	                nomeInvalido = false;
	            }
	        }

	        if (!encontrou) {
	            if (!cpfInvalido && !nomeInvalido) {
	                throw new Exception("Não existe reserva com esse CPF e nome.");
	            } else if (!cpfInvalido) {
	                throw new Exception("Não existe esse Nome");
	            } else if (!nomeInvalido) {
	                throw new Exception("Não existe esse CPF");
	            } else {
	                throw new Exception("Não existe reserva com esse CPF e nome.");
	            }
	        }
	    }
	
	public void cancelarReserva(String cpf) throws Exception {
	    boolean entrou = false;
	    
	    if (listaReserva.size() <=0 ) {
	    	throw new Exception("Lista vazia");
	    }

	    for (int i = listaReserva.size() - 1; i >= 0; i--) {
	        String temp = listaReserva.get(i);
	        String[] separacao = temp.split("/");

	        if (separacao[0].equals(cpf)) {
	            listaReserva.remove(i);
	            entrou = true;
	        }
	    }

	    if (!entrou) {
	        throw new Exception("Não existe esse cpf");
	    }
	    
	    
	}
	
	


		
	}
		
		