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
	    int codcpf = 0;
	    int codnome = 0;
	    int codconfirmacao = 0;
	    if (listaReserva.size() > 0) {
	        for (int i = 0; i < listaReserva.size(); i++) {
	            String temp = listaReserva.get(i);
	            String[] separacao = temp.split("/");
	            codnome = 0;
	            codcpf = 0;
	            
	            if (separacao[0].equals(cpf)) {
	                codconfirmacao = 5; // Existe o CPF OK
	                codcpf = 0;
	            }if(!separacao[0].equals(cpf)) {
	                codcpf = 5; // Não existe o cpf
	            }

	            if (!separacao[1].equals(nome)) {
	                
	            }
	            if (separacao[1].equals(nome) || codconfirmacao == 5) {
	                listaReserva.remove(i);
	            }
	        }
	        if (codcpf == 5 && codnome == 10) {
	            throw new Exception("Não existe esse cpf, e esse nome");
	        }
	        if (codcpf == 5 && codnome == 0) {
	            throw new Exception("Não existe esse cpf");
	        }
	        if (codcpf == 0 && codnome == 10) {
	        	
	            throw new Exception("Não existe esse nome");
	        }
	    }else
			throw new Exception("Não é possível remover, pois a lista está vazia");
	}
	
	public void cancelarReserva(String cpf) throws Exception {
	    int i = 0;
	    while (i < listaReserva.size()) {
	        String temp = listaReserva.get(i);
	        String[] separacao = temp.split("/");
	        if (separacao[0].equals(cpf)) {
	            listaReserva.remove(i);
	            System.out.println(listaReserva.size());
	        } else {
	            i++; 
	        }
	        if (listaReserva.size()<=0) {
	        	throw new Exception("Não é possível remover, pois a lista está vaziaaa");
	        }
		
	}
	

		
	}}
		
		
