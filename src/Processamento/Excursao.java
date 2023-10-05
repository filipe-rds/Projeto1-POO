package Processamento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Excursao {
	// Attributes
	private int codigo;
	private double preco;
	private int maxReservas;
	private ArrayList<String> listaReserva = new ArrayList<>();

	// Constructors

	/*
	 Construtor que recebe como parâmetro o código, preço e número máximo de reservas da excursão e verifica se já existe uma excursão com o código informado, caso não haja convergência, ele cria uma nova excursão com os dados informados. 
	*/
	public Excursao(int codigo, double preco, int maxReservas) throws Exception {
		boolean codigoInvalido = false;
		boolean precoInvalido = false;
		boolean maxReservasInvalido = false;

		File pasta = new File("./Registro");

		if (!pasta.exists()) {
			pasta.mkdir();
		}

		File arquivo = new File("Registro/" + codigo + ".txt");
		if (arquivo.exists()) {
			throw new Exception("Já existe uma excursão com o código " + codigo);
		}

		if (codigo > 0) {
			this.codigo = codigo;
		} else {
			codigoInvalido = true;
		}

		if (preco > 0) {
			this.preco = preco;
		} else {
			precoInvalido = true;
		}

		if (maxReservas > 0) {
			this.maxReservas = maxReservas;
		} else {
			maxReservasInvalido = true;
		}

		if (codigoInvalido && precoInvalido && maxReservasInvalido) {
			throw new Exception("O código, preço e número máximo de reservas devem ser maiores que zero");
		} else if (codigoInvalido && precoInvalido) {
			throw new Exception("O código e preço devem ser maiores que zero");
		} else if (codigoInvalido && maxReservasInvalido) {
			throw new Exception("O código e número máximo de reservas devem ser maiores que zero");
		} else if (precoInvalido && maxReservasInvalido) {
			throw new Exception("O preço e número máximo de reservas devem ser maiores que zero");
		} else if (codigoInvalido) {
			throw new Exception("O código deve ser maior que zero");
		} else if (precoInvalido) {
			throw new Exception("O preço deve ser maior que zero");
		} else if (maxReservasInvalido) {
			throw new Exception("O número máximo de reservas deve ser maior que zero");
		}
		listaReserva = new ArrayList<>();

	}

	/*
	 Construtor que recebe como parâmetro o código da excursão e verifica se já existe uma excursão com o código informado, caso haja convergência, ele inicializa a excursão com o dado fornecido.
	*/
	public Excursao(int codigo) throws Exception {

		File pasta = new File("./Registro");

		if (!pasta.exists()) {
			pasta.mkdir();
		}

		File arquivo = new File("Registro/" + codigo + ".txt");
		if (!arquivo.exists()) {
			throw new Exception("Não existe uma excursão com o código " + codigo);
		}

		this.codigo = codigo;
		listaReserva = new ArrayList<>();
	}

	// Construtor vazio
	public Excursao() {} 

	// Getters and Setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
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

	/* 
	 Método que recebe como parâmetro o CPF e o nome do cliente e verifica se existe uma reserva com estes dados, caso não haja convergência, haverá a criação desta reserva.
	*/
	public void criarReserva(String cpf, String nome) throws Exception {
		if (listaReserva.size() < maxReservas) {
			// if(cpf.length()!=11) {
			// throw new Exception("CPF deve conter 11 dígitos");
			// }

			for (int i = 0; i < listaReserva.size(); i++) {
				String[] separador = listaReserva.get(i).split("/"); // ["cpf","nome"]
				if (separador[1].toUpperCase().equals(nome.toUpperCase())) {
					throw new Exception("Nome já está cadastrado");
				}
			}
			listaReserva.add(cpf + "/" + nome);
		} else {
			throw new Exception("Limite excedido");
		}
	}

	/*  
	 Método que recebe como parâmetro o CPF e o nome do cliente e verifica se existe uma reserva com estes dados, caso haja convergência, haverá a remoção desta reserva.
	*/
	public void cancelarReserva(String cpf, String nome) throws Exception {
		if (!listaReserva.isEmpty()) {
			// if(cpf.length()!=11) {
			// throw new Exception("CPF deve conter 11 dígitos");
			// }

			boolean cancelamentoValido = false;
			boolean cpfValido = false;
			boolean nomeValido = false;

			for (int i = 0; i < listaReserva.size(); i++) {
				String[] separador = listaReserva.get(i).split("/"); // ["cpf","nome"]

				if (separador[0].equals(cpf) && separador[1].toUpperCase().equals(nome.toUpperCase())) {
					listaReserva.remove(i);
					cancelamentoValido = true;
					break;
				} else if (separador[0].equals(cpf)) {
					cpfValido = true;
				} else if (separador[1].toUpperCase().equals(nome.toUpperCase())) {
					nomeValido = true;
				}
			}

			if (!cancelamentoValido) {
				if (!cpfValido && !nomeValido) {
					throw new Exception("Não existe reserva com este CPF e nome.");
				} else if (!cpfValido) {
					throw new Exception("Não existe reserva com este CPF");
				} else if (!nomeValido) {
					throw new Exception("Não existe reserva com este nome");
				}
			}
		} else {
			throw new Exception("Não há registros de reserva na excursão");
		}

	}

	/* 
	 Método que recebe como parâmetro o CPF do cliente e verifica se existem reservas com este CPF, caso haja convergência, haverá a remoção das reservas encontradas.
	*/
	public void cancelarReserva(String cpf) throws Exception {
		if (!listaReserva.isEmpty()) {
			// if(cpf.length()!=11) {
			// throw new Exception("CPF deve conter 11 dígitos");
			// }
			boolean cancelamentoValido = false;

			for (int i = listaReserva.size() - 1; i >= 0; i--) {
				String[] separador = listaReserva.get(i).split("/"); // ["cpf","nome"]

				if (separador[0].equals(cpf)) {
					listaReserva.remove(i);
					cancelamentoValido = true;
				}
			}
			if (!cancelamentoValido) {
				throw new Exception("Não existe reserva com este cpf");
			}

		} else {
			throw new Exception("Não há registros de reserva na excursão");
		}

	}

	/*
	 Método que recebe como parâmetro o CPF do cliente (completo ou parte dele) e verifica se existem reservas com este CPF, caso haja convergência, ele retorna uma lista com as reservas encontradas.
	*/
	public ArrayList<String> listarReservasPorCpf(String cpf) throws Exception {
		if (!listaReserva.isEmpty()) {
			if (cpf.length() == 0) {
				return listaReserva;
			} else {
				// if(cpf.length()!=11) {
				// throw new Exception("CPF deve conter 11 dígitos");
				// }
				ArrayList<String> registrosEncontrados = new ArrayList<String>();
				for (int i = 0; i < listaReserva.size(); i++) {
					String reserva = listaReserva.get(i);
					String[] separador = reserva.split("/");

					if (separador[0].contains(cpf)) {
						registrosEncontrados.add(reserva);
					}
				}
				if (registrosEncontrados.isEmpty()) {
					throw new Exception("A sequência de dígitos não foi encontrada na lista de reservas");
				}
				return registrosEncontrados;
			}

		} else {
			throw new Exception("Não há registros de reserva na excursão");
		}
	}

	/* 
	 Método que recebe como parâmetro o nome do cliente (completo ou parte dele) e verifica se existem reservas com este nome, caso haja convergência, ele retorna uma lista com as reservas encontradas.
	*/
	public ArrayList<String> listarReservasPorNome(String nome) throws Exception {
		if (!listaReserva.isEmpty()) {
			if (nome.length() == 0) {
				return listaReserva;
			} else {
				ArrayList<String> registrosEncontrados = new ArrayList<String>();

				for (int i = 0; i < listaReserva.size(); i++) {
					String reserva = listaReserva.get(i);
					String[] separador = reserva.split("/"); // ["cpf","nome"]

					if (separador[1].toUpperCase().contains(nome.toUpperCase())) {
						registrosEncontrados.add(reserva);
					}
				}

				if (registrosEncontrados.isEmpty()) {
					throw new Exception("A sequência de caracteres não foi encontrada na lista de reservas");
				}
				return registrosEncontrados;
			}

		} else {
			throw new Exception("Não há registros de reserva na excursão");
		}
	}

	// Método para salvar os detalhes da excursão em um arquivo de texto
	public void salvar() throws Exception {
		String nomeArquivo = codigo + ".txt";

		try (FileWriter escritor = new FileWriter(new File(".\\Registro\\" + nomeArquivo).getCanonicalPath())) {
			// Escreve o preço na primeira linha
			escritor.write(String.valueOf(preco + "\n"));

			// Escreve o máximo de reservas na segunda linha
			escritor.write(String.valueOf(maxReservas + "\n"));

			// Escreve as reservas no formato CPF/Nome em linhas subsequentes
			for (String reserva : listaReserva) {
				escritor.write(reserva + "\n");
			}

		} catch (IOException e) {
			throw new Exception("Erro ao salvar no arquivo");
		}
	}

	// Método para ler os detalhes da excursão em um arquivo de texto
	public void ler() throws Exception {
		String nomeArquivo = codigo + ".txt";

		try (BufferedReader leitor = new BufferedReader(
				new FileReader(new File(".\\Registro\\" + nomeArquivo).getCanonicalPath()))) {
			// Lê o preço da primeira linha
			double preco = Double.parseDouble(leitor.readLine());

			// Lê o máximo de reservas da segunda linha
			int maxReservas = Integer.parseInt(leitor.readLine());

			// Lê as reservas do restante do arquivo
			ArrayList<String> reservas = new ArrayList<>();
			String linha;
			while ((linha = leitor.readLine()) != null) {
				reservas.add(linha);
			}

			// Atualiza os dados da instância com os dados lidos
			this.preco = preco;
			this.maxReservas = maxReservas;
			this.listaReserva = reservas;
		} catch (IOException e) {
			throw new Exception("Erro ao ler no arquivo");
		}
	}

	// Método para calcular o valor total da excursão
	public double calcularValorTotal() {
		double valorTotal = this.preco * listaReserva.size();
		return valorTotal;
	}

	// Método para retornar os dados da excursão em uma string
	@Override
	public String toString() {
		return "Excursao [codigo=" + codigo + ", preco=" + preco + ", maxReservas=" + maxReservas
				+ ", listaReserva=" + listaReserva + "]";
	}

}