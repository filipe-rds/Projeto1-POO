package Processamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	public Excursao(int codigo, double preco, int maxReservas) throws Exception {
		boolean codigoInvalido = false;
		boolean precoInvalido = false;
		boolean maxReservasInvalido = false;

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

	public Excursao(int codigo) {
		this.codigo = codigo;
		listaReserva = new ArrayList<>();
	}

	public Excursao() {
		// Construtor vazio
	}

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
	public void criarReserva(String cpf, String nome) throws Exception {
		// Adiciona uma reserva “cpf/nome”
		// Verificar se existe um nome igual na lista

		if (listaReserva.size() < maxReservas) {
			for (int i = 0; i < listaReserva.size(); i++) {
				// String temp = listaReserva.get(i);
				String[] separacao = listaReserva.get(i).split("/"); // ["cpf","nome"]
				if (separacao[1].toUpperCase().equals(nome.toUpperCase())) {
					throw new Exception("Nome já está cadastrado");
				}
			}
			listaReserva.add(cpf + "/" + nome);
		} else {
			throw new Exception("Limite excedido");
		}
	}

	public void cancelarReserva(String cpf, String nome) throws Exception {
		if (!listaReserva.isEmpty()) {
			boolean cancelamentoValido = false;
			boolean cpfValido = false;
			boolean nomeValido = false;

			for (int i = 0; i < listaReserva.size(); i++) {
				String[] separacao = listaReserva.get(i).split("/"); // ["cpf","nome"]

				if (separacao[0].equals(cpf) && separacao[1].toUpperCase().equals(nome.toUpperCase())) {
					listaReserva.remove(i);
					cancelamentoValido = true;
					break;
				} else if (separacao[0].equals(cpf)) {
					cpfValido = true;
				} else if (separacao[1].toUpperCase().equals(nome.toUpperCase())) {
					nomeValido = true;
				}
			}

			if (!cancelamentoValido) {
				if (!cpfValido && !nomeValido) {
					throw new Exception("Não existe reserva com esse CPF e nome.");
				} else if (!cpfValido) {
					throw new Exception("Não existe esse CPF");
				} else if (!nomeValido) {
					throw new Exception("Não existe esse nome");
				}
			}
		} else {
			throw new Exception("Não há registros de reserva na excursão");
		}

	}

	public void cancelarReserva(String cpf) throws Exception {
		if (!listaReserva.isEmpty()) {
			boolean cancelamentoValido = false;

			for (int i = listaReserva.size() - 1; i >= 0; i--) {
				String[] separacao = listaReserva.get(i).split("/"); // ["cpf","nome"]

				if (separacao[0].equals(cpf)) {
					listaReserva.remove(i);
					cancelamentoValido = true;
				}
			}
			if (!cancelamentoValido) {
				throw new Exception("Não existe esse cpf");
			}

		} else {
			throw new Exception("Não há registros de reserva na excursão");
		}

	}

	public ArrayList<String> listarReservasPorCpf(String digitos) throws Exception {
		if (!listaReserva.isEmpty()) {
			if (digitos == "") {
				ArrayList<String> registroTotal = new ArrayList<String>();
				for (int i = 0; i < listaReserva.size(); i++) {
					String reserva = listaReserva.get(i);
					registroTotal.add(reserva);
				}
				return registroTotal;

			} else {
				ArrayList<String> registrosEncontrados = new ArrayList<String>();

				for (int i = 0; i < listaReserva.size(); i++) {
					String reserva = listaReserva.get(i);
					String[] separacao = reserva.split("/");

					if (separacao[0].contains(digitos)) {
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

	public ArrayList<String> listarReservasPorNome(String texto) throws Exception {
		if (!listaReserva.isEmpty()) {
			if (texto == "") {
				ArrayList<String> registroTotal = new ArrayList<String>();
				for (int i = 0; i < listaReserva.size(); i++) {
					String reserva = listaReserva.get(i);
					registroTotal.add(reserva);
				}
				return registroTotal;
			} else {
				ArrayList<String> registrosEncontrados = new ArrayList<String>();

				for (int i = 0; i < listaReserva.size(); i++) {
					String reserva = listaReserva.get(i);
					String[] separacao = reserva.split("/");

					if (separacao[1].toUpperCase().contains(texto.toUpperCase())) {
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

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Registro/" + nomeArquivo))) {
			// Escreve o preço na primeira linha
			writer.write(String.valueOf(preco));
			writer.newLine();

			// Escreve o máximo de reservas na segunda linha
			writer.write(String.valueOf(maxReservas));
			writer.newLine();

			// Escreve as reservas no formato CPF/Nome em linhas subsequentes
			for (String reserva : listaReserva) {
				writer.write(reserva);
				writer.newLine();
			}

		} catch (IOException e) {
			throw new Exception("Erro ao tentar salvar");
		}
	}

	public void ler() {
		String nomeArquivo = codigo + ".txt";

		try (BufferedReader reader = new BufferedReader(new FileReader("src/Registro/" + nomeArquivo))) {
			// Lê o preço da primeira linha
			double preco = Double.parseDouble(reader.readLine());

			// Lê o máximo de reservas da segunda linha
			int maxReservas = Integer.parseInt(reader.readLine());

			// Lê as reservas do restante do arquivo
			ArrayList<String> reservas = new ArrayList<>();
			String linha;
			while ((linha = reader.readLine()) != null) {
				reservas.add(linha);
			}

			// Atualiza os dados da instância com os dados lidos
			this.preco = preco;
			this.maxReservas = maxReservas;
			this.listaReserva = reservas;
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo: " + e.getMessage());
		}
	}

	public void existe() throws Exception {

		File arquivo = new File("src/Registro/" + codigo + ".txt");
		if (arquivo.exists()) {
			throw new Exception("Já existe uma excursão com o código " + codigo);
		}

	}

	public void naoExiste() throws Exception {

		File arquivo = new File("src/Registro/" + codigo + ".txt");
		if (!arquivo.exists()) {
			throw new Exception("Não existe uma excursão com o codigo " + codigo);
		}

	}

	public double calcularValorTotal() {
		double valorTotal = this.preco * listaReserva.size();
		return valorTotal;
	}

	@Override
	public String toString() {
		return "Excursao [codigo=" + codigo + ", preco=" + preco + ", maxReservas=" + maxReservas
				+ ", listaReserva=" + listaReserva + "]";
	}

}