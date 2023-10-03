package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import Processamento.Excursao;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Janela {

	private JFrame frame;
	private JButton btnCriarExcursao;
	private JButton btnRecuperarExcursao;
	private JButton btnCriarReserva;
	private JButton btnCancelarReservaIndividual;
	private JButton btnCancelarReservaGrupo;
	private JButton btnListarReservaPorCPF;
	private JButton btnListarReservaPorNome;
	private JButton btnCalcularValorTotal;
	private JLabel labelCodigoDaExcursaoAtual;
	private JLabel labelNenhumaExcursaoSelecionada;
	private JTextArea textAreaPainel;
	private Excursao excursao;
	private boolean excursaoSelecionada = false;
	private JScrollPane scrollPane;

	/*
	  Launch the application.
	*/

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela window = new Janela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	  Create the application.
	*/

	public Janela() {
		initialize();
		try {
			excursao = new Excursao();

		} catch (Exception h) {
			System.out.println("Erro:" + h.getMessage());
		}
	}

	/*
	  Initialize the contents of the frame.
	*/

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(new Color(51, 51, 102));
		frame.getContentPane().setFont(new Font("Monospaced", Font.BOLD, 17));
		frame.setTitle("Excursão");
		frame.setBounds(100, 100, 879, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		btnCriarExcursao = new JButton("Criar excursão");

		btnCriarExcursao.setFont(new Font("Arial", Font.BOLD, 12));
		btnCriarExcursao.setHorizontalAlignment(SwingConstants.LEFT);
		btnCriarExcursao.setForeground(UIManager.getColor("Button.foreground"));
		btnCriarExcursao.setBackground(UIManager.getColor("Button.background"));

		/*
		 Botão responsável por criar uma nova excursão, ele recebe o código, o preço e a quantidade máxima de pessoas e salva as informações em um arquivo.
		*/
		btnCriarExcursao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo;
				double preco;
				int max;
				textAreaPainel.setVisible(false);
				textAreaPainel.setText("");

				try {

					String codigoStr = JOptionPane.showInputDialog(frame, "Digite o código da sua excursão");
					if (codigoStr == null) {
						// O usuário pressionou "Cancelar" ou fechou a caixa de diálogo
						return;
					}
					codigo = Integer.parseInt(codigoStr);

					String precoStr = JOptionPane.showInputDialog(frame, "Digite o preço por pessoa");
					if (precoStr == null) {
						return;
					}

					preco = Double.parseDouble(precoStr);

					String maxStr = JOptionPane.showInputDialog(frame, "Digite a quantidade máxima de pessoas");

					if (maxStr == null) {
						return;
					}

					max = Integer.parseInt(maxStr);

					Excursao temp = new Excursao(codigo, preco, max);
					excursao = temp;

					excursao.salvar();

					JOptionPane.showMessageDialog(frame, "Sua excursão foi criada com sucesso.");
					String codstr = String.valueOf(codigo);
					labelNenhumaExcursaoSelecionada.setText(codstr);
					excursaoSelecionada = true;

				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(frame, "Por favor, insira valores numéricos válidos.");

				} catch (Exception b) {
					JOptionPane.showMessageDialog(frame, "Erro: " + b.getMessage());
				}
			}
		});

		btnCriarExcursao.setBounds(32, 59, 203, 38);
		frame.getContentPane().add(btnCriarExcursao);

		btnRecuperarExcursao = new JButton("Recuperar excursão");
		btnRecuperarExcursao.setBackground(Color.WHITE);
		btnRecuperarExcursao.setFont(new Font("Arial", Font.BOLD, 12));
		btnRecuperarExcursao.setHorizontalAlignment(SwingConstants.LEFT);
		/*
		 Botão responsável por recuperar uma excursão, ele recebe o código e recupera as informações da excursão através de um arquivo.
		*/
		btnRecuperarExcursao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textAreaPainel.setVisible(false);
				textAreaPainel.setText("");
				int codigo;
				ArrayList<String> lista = new ArrayList<>();

				try {

					String codigoStr = JOptionPane.showInputDialog(frame, "Digite o código para recuperar");
					if (codigoStr == null) {
						return;
					}

					codigo = Integer.parseInt(codigoStr);

					Excursao temp = new Excursao(codigo);
					excursao = temp;
					excursao.ler();

					String codstr = String.valueOf(codigo);
					labelNenhumaExcursaoSelecionada.setText(codstr);
					excursaoSelecionada = true;

					lista = excursao.listarReservasPorNome("");
					for (String str : lista) {
						String[] separador = str.split("/");
						textAreaPainel.append(separador[0] + " - " + separador[1] + "\n");
					}
					textAreaPainel.setVisible(true);
					JOptionPane.showMessageDialog(frame, "Excursão recuperada com sucesso");

				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(frame, "Por favor, insira valores numéricos válidos.");
				} catch (Exception b) {
					JOptionPane.showMessageDialog(frame, b.getMessage());
				}
			}
		}

		);
		btnRecuperarExcursao.setBounds(263, 59, 203, 38);
		frame.getContentPane().add(btnRecuperarExcursao);

		btnCriarReserva = new JButton("Criar reserva");
		btnCriarReserva.setFont(new Font("Arial", Font.BOLD, 12));
		btnCriarReserva.setHorizontalAlignment(SwingConstants.LEFT);
		/*
		 Botão responsável por criar uma reserva em uma excurssão, ele recebe o CPF e o nome do cliente e salva as informações da excurção em um arquivo. 
		*/
		btnCriarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaPainel.setVisible(false);
				textAreaPainel.setText("");

				String cpf = "";
				String nome = "";

				if (excursaoSelecionada) {
					try {
						String input = JOptionPane.showInputDialog(frame,
								"Digite o CPF, qualquer outro caracter, será desconsiderado");

						// Remove espaços em branco e caracteres não numéricos da entrada
						try {
							cpf = input.replaceAll("[^0-9]", "");

						} catch (Exception a) {
							return;
						}
						if (cpf.isEmpty()) {
							throw new Exception("O CPF deve conter pelo menos um número.");

						}

					} catch (NumberFormatException b) {
						JOptionPane.showMessageDialog(frame, "Erro: Por favor, insira valores numéricos válidos");
						return;

					} catch (Exception c) {
						JOptionPane.showMessageDialog(frame, "Erro: " + c.getMessage());
						return;
					}

					try {
						nome = JOptionPane.showInputDialog(frame, "Digite o nome");

						if (nome == null) {
							return;
						}
						if (!nome.matches("^(?U)[\\p{L}\\s]+$") || nome.matches(".*\\s{2,}.*")) {
							throw new Exception(
									"O nome deve conter apenas letras, espaços e não pode ter espaços consecutivos.");
						}

						excursao.criarReserva(cpf, nome);
						excursao.salvar();
						textAreaPainel.setVisible(false);
						textAreaPainel.setText("");

					} catch (Exception d) {
						JOptionPane.showMessageDialog(frame, "Erro: " + d.getMessage());
						return;
					}

					JOptionPane.showMessageDialog(frame, "Reserva realizada com sucesso");

				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}

			}

		});
		btnCriarReserva.setBounds(32, 163, 203, 38);
		frame.getContentPane().add(btnCriarReserva);

		btnCancelarReservaIndividual = new JButton("Cancelar reserva individual");
		btnCancelarReservaIndividual.setFont(new Font("Arial", Font.BOLD, 12));
		btnCancelarReservaIndividual.setHorizontalAlignment(SwingConstants.LEFT);
		/*
		Botão responsável por cancelar uma reserva individual em uma excurssão, ele recebe o CPF e o nome do cliente e remove esta reserva, posteriormente é salvo as informações atuais da excursão em um arquivo.
		*/
		btnCancelarReservaIndividual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = "";
				String nome = "";

				if (excursaoSelecionada) {
					if (excursao.getListaReserva().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Erro: Não há registros de reserva na excursão");
						return;
					}
					try {
						String input = JOptionPane.showInputDialog(frame,
								"Digite o CPF, qualquer outro caracter, será desconsiderado");

						// Remove espaços em branco e caracteres não numéricos da entrada
						try {
							cpf = input.replaceAll("[^0-9]", "");

						} catch (Exception a) {
							return;
						}

					} catch (NumberFormatException b) {
						JOptionPane.showMessageDialog(frame, "Erro: Por favor, insira valores numéricos válidos");
						return;

					} catch (Exception c) {
						JOptionPane.showMessageDialog(frame, "Erro: " + c.getMessage());
						return;
					}

					try {
						nome = JOptionPane.showInputDialog(frame, "Digite o nome");

						if (nome == null) {
							return;
						}
						if (!nome.matches("^(?U)[\\p{L}\\s]+$") || nome.matches(".*\\s{2,}.*")) {
							throw new Exception(
									"O nome deve conter apenas letras, espaços e não pode ter espaços consecutivos.");
						}

						excursao.cancelarReserva(cpf, nome);
						excursao.salvar();
						textAreaPainel.setText("");
						textAreaPainel.setVisible(false);

					} catch (Exception x) {
						JOptionPane.showMessageDialog(frame, "Erro: " + x.getMessage());
						return;
					}

					JOptionPane.showMessageDialog(frame,
							"Cancelamento do CPF " + cpf + " e nome " + nome + " feito com sucesso");

				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}

			}
		});
		btnCancelarReservaIndividual.setBounds(32, 239, 203, 38);
		frame.getContentPane().add(btnCancelarReservaIndividual);

		btnCancelarReservaGrupo = new JButton("Cancelar reserva grupo");
		/*
		Botão responsável por cancelar uma reserva em grupo da excurssão, ele recebe o CPF do grupo e remove todas as reservas que possuem este CPF, e posteriormente é salvo as informações atuais da excursão em um arquivo.
		*/
		btnCancelarReservaGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = "";

				if (excursaoSelecionada) {
					if (excursao.getListaReserva().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Erro: Não há registros de reserva na excursão");
						return;
					}

					try {
						String input = JOptionPane.showInputDialog(frame,
								"Digite o CPF, qualquer outro caracter, será desconsiderado");

						// Remove espaços em branco e caracteres não numéricos da entrada
						try {
							cpf = input.replaceAll("[^0-9]", "");

						} catch (Exception a) {
							return;
						}

						excursao.cancelarReserva(cpf);
						excursao.salvar();
						textAreaPainel.setVisible(false);
						textAreaPainel.setText("");

					} catch (NumberFormatException b) {
						JOptionPane.showMessageDialog(frame, "Erro: Por favor, insira valores numéricos válidos");
						return;

					} catch (Exception c) {
						JOptionPane.showMessageDialog(frame, "Erro: " + c.getMessage());
						return;
					}

					JOptionPane.showMessageDialog(frame,
							"Todas as reservas feitas no CPF " + cpf + " foram canceladas com sucesso");

				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}

			}
		});
		btnCancelarReservaGrupo.setFont(new Font("Arial", Font.BOLD, 12));
		btnCancelarReservaGrupo.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancelarReservaGrupo.setBounds(32, 310, 203, 38);
		frame.getContentPane().add(btnCancelarReservaGrupo);
		/*
		Botão responsável por listar as reservas de uma excursão através do CPF fornecido (completo ou parte dele), ele recebe o CPF e retorna todas as reservas que possuem este CPF. 
		*/
		btnListarReservaPorCPF = new JButton("Listar reserva por CPF");
		btnListarReservaPorCPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaPainel.setVisible(false);
				textAreaPainel.setText("");
				String cpf = "";
				ArrayList<String> listaCpf = new ArrayList<>();

				if (excursaoSelecionada) {
					if (excursao.getListaReserva().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Erro: Não há registros de reserva na excursão");
						return;
					}

					try {
						String input = JOptionPane.showInputDialog(frame,
								"Digite o CPF, qualquer outro caracter, será desconsiderado");

						// Remove espaços em branco e caracteres não numéricos da entrada
						try {
							cpf = input.replaceAll("[^0-9]", "");

						} catch (Exception a) {
							return;
						}

						textAreaPainel.setText("");

						listaCpf = excursao.listarReservasPorCpf(cpf);

						for (String str : listaCpf) {
							String[] separador = str.split("/");
							textAreaPainel.append(separador[0] + " - " + separador[1] + "\n");
						}

						textAreaPainel.setVisible(true);

					} catch (Exception b) {
						JOptionPane.showMessageDialog(frame, "Erro: " + b.getMessage());
						return;

					}

					if (cpf.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Todas as reservas feitas");
					} else {
						JOptionPane.showMessageDialog(frame, "Lista de reservas por CPF: " + cpf);
					}

				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}

			}
		});
		btnListarReservaPorCPF.setFont(new Font("Arial", Font.BOLD, 12));
		btnListarReservaPorCPF.setHorizontalAlignment(SwingConstants.LEFT);
		btnListarReservaPorCPF.setBounds(263, 239, 203, 38);
		frame.getContentPane().add(btnListarReservaPorCPF);

		/*
		 Botão responsável por listar as reservas de uma excursão através do nome fornecido (completo ou parte dele), ele recebe o nome e retorna todas as reservas que possuem este nome. 
		*/

		btnListarReservaPorNome = new JButton("Listar reserva por nome");
		btnListarReservaPorNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaPainel.setVisible(false);
				textAreaPainel.setText("");
				String nome = "";
				ArrayList<String> listaNome = new ArrayList<>();

				if (excursaoSelecionada) {
					if (excursao.getListaReserva().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Erro: Não há registros de reserva na excursão");
						return;
					}

					try {
						nome = JOptionPane.showInputDialog(frame, "Digite o nome");

						if (nome == null) {
							return;
						}
						textAreaPainel.setText("");
						listaNome = excursao.listarReservasPorNome(nome);

						for (String str : listaNome) {

							String[] separador = str.split("/");
							textAreaPainel.append(separador[0] + " - " + separador[1] + "\n");
						}

						textAreaPainel.setVisible(true);

					} catch (Exception a) {
						JOptionPane.showMessageDialog(frame, "Erro: " + a.getMessage());
						return;

					}
					if (nome.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Todas as reservas feitas");
					} else
						JOptionPane.showMessageDialog(frame, "Lista de reservas pelo nome: " + nome);
				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");

				}
			}
		});
		btnListarReservaPorNome.setFont(new Font("Arial", Font.BOLD, 12));
		btnListarReservaPorNome.setHorizontalAlignment(SwingConstants.LEFT);
		btnListarReservaPorNome.setBounds(263, 163, 203, 38);

		frame.getContentPane().add(btnListarReservaPorNome);

		labelCodigoDaExcursaoAtual = new JLabel("Código da excursão atual:");
		labelCodigoDaExcursaoAtual.setHorizontalAlignment(SwingConstants.LEFT);
		labelCodigoDaExcursaoAtual.setForeground(new Color(255, 0, 0));
		labelCodigoDaExcursaoAtual.setFont(new Font("Constantia", Font.BOLD, 17));
		labelCodigoDaExcursaoAtual.setBounds(21, 420, 247, 38);
		frame.getContentPane().add(labelCodigoDaExcursaoAtual);

		labelNenhumaExcursaoSelecionada = new JLabel("Nenhuma excursão selecionada");
		labelNenhumaExcursaoSelecionada.setForeground(Color.BLACK);
		labelNenhumaExcursaoSelecionada.setBackground(Color.WHITE);
		labelNenhumaExcursaoSelecionada.setFont(new Font("Monospaced", Font.BOLD, 18));
		labelNenhumaExcursaoSelecionada.setBounds(267, 423, 335, 24);
		frame.getContentPane().add(labelNenhumaExcursaoSelecionada);
		/*
		 Botão responsável por retornar o valor total da excursão.
		*/
		btnCalcularValorTotal = new JButton("Calcular valor total");
		btnCalcularValorTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (excursaoSelecionada) {
					if (excursao.getListaReserva().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Erro: Não há registros de reserva na excursão");
						return;
					}

					double total = excursao.calcularValorTotal();
					textAreaPainel.setText("");
					textAreaPainel.setVisible(false);
					JOptionPane.showMessageDialog(frame, "O valor total da excursão: R$" + total);
				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}

			}
		});
		btnCalcularValorTotal.setHorizontalAlignment(SwingConstants.LEFT);
		btnCalcularValorTotal.setFont(new Font("Arial", Font.BOLD, 12));
		btnCalcularValorTotal.setBounds(263, 310, 203, 38);
		frame.getContentPane().add(btnCalcularValorTotal);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(515, 59, 317, 289);
		frame.getContentPane().add(scrollPane);
		
				textAreaPainel = new JTextArea();
				scrollPane.setViewportView(textAreaPainel);
				textAreaPainel.setBackground(Color.LIGHT_GRAY);
				textAreaPainel.setFont(new Font("Arial", Font.BOLD, 15));
				textAreaPainel.setEditable(false);
				textAreaPainel.setVisible(false);
	}
}