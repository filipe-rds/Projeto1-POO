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

public class Janela {

	private JFrame frame;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JLabel label;
	private JLabel label_1;
	private JTextArea textArea;

	private Excursao excursao;
	private boolean excursaoSelecionada = false;

	/**
	 * Launch the application.
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

	/**
	 * Create the application.
	 */

	public Janela() {
		initialize();
		try {
			excursao = new Excursao();

		} catch (Exception h) {
			System.out.println("Erro:" + h.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
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

		button = new JButton("Criar excursão");
		button.setFont(new Font("Arial", Font.BOLD, 12));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setForeground(UIManager.getColor("Button.foreground"));
		button.setBackground(UIManager.getColor("Button.background"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo;
				double preco;
				int max;
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

					Excursao teste1 = new Excursao(codigo, preco, max);

					teste1.existe();

					excursao = teste1;
					excursao.salvar();
					JOptionPane.showMessageDialog(frame, "Sua excursão foi criada com sucesso.");
					String codstr = String.valueOf(codigo);
					label_1.setText(codstr);
					excursaoSelecionada = true;

				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(frame, "Por favor, insira valores numéricos válidos.");

				} catch (Exception b) {
					JOptionPane.showMessageDialog(frame, "Erro: " + b.getMessage());
				}
			}
		});

		button.setBounds(32, 25, 203, 38);
		frame.getContentPane().add(button);

		button_1 = new JButton("Recuperar excursão");
		button_1.setBackground(Color.WHITE);
		button_1.setFont(new Font("Arial", Font.BOLD, 12));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo;
				textArea.setText("");
				try {

					String codigoStr = JOptionPane.showInputDialog(frame, "Digite o código para recuperar");
					if (codigoStr == null) {
						return;
					}

					codigo = Integer.parseInt(codigoStr);

					Excursao teste2 = new Excursao(codigo);

					teste2.naoExiste();
					teste2.ler();

					excursao = teste2;

					JOptionPane.showMessageDialog(frame, "Excursão recuperada com sucesso");

					String codstr = String.valueOf(codigo);
					label_1.setText(codstr);
					excursaoSelecionada = true;

				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(frame, "Por favor, insira valores numéricos válidos.");
				} catch (Exception b) {
					JOptionPane.showMessageDialog(frame, b.getMessage());
				}
			}
		}

		);
		button_1.setBounds(32, 73, 203, 38);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("Criar reserva");
		button_2.setFont(new Font("Arial", Font.BOLD, 12));
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cpf = "";
				String nome = "";
				textArea.setText("");

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
						nome = JOptionPane.showInputDialog(frame, "Digite o nome").toUpperCase();

						if (nome == null) {
							return;
						}
						if (!nome.matches("^[a-zA-Z\\s]+$") || nome.matches(".*\\s{2,}.*")) {
							throw new Exception(
									"O nome deve conter apenas letras e espaços e não pode ter espaços consecutivos.");

						}

						excursao.criarReserva(cpf, nome);
						excursao.salvar();

					} catch (Exception d) {
						JOptionPane.showMessageDialog(frame, "Erro: " + d.getMessage());
						return;
					}

					JOptionPane.showMessageDialog(frame, excursao);

				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}
			}
		});
		button_2.setBounds(32, 121, 203, 38);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Cancelar reserva individual");
		button_3.setFont(new Font("Arial", Font.BOLD, 12));
		button_3.setHorizontalAlignment(SwingConstants.LEFT);
		button_3.addActionListener(new ActionListener() {
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
						nome = JOptionPane.showInputDialog(frame, "Digite o nome").toUpperCase();

						if (nome == null) {
							return;
						}
						if (!nome.matches("^[a-zA-Z\\s]+$") || nome.matches(".*\\s{2,}.*")) {
							throw new Exception(
									"O nome deve conter apenas letras e espaços e não pode ter espaços consecutivos.");

						}

						excursao.cancelarReserva(cpf, nome);
						excursao.salvar();

					} catch (Exception x) {
						JOptionPane.showMessageDialog(frame, "Erro: " + x.getMessage());
						return;
					}

					JOptionPane.showMessageDialog(frame, excursao);

				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}

			}
		});
		button_3.setBounds(32, 169, 203, 38);
		frame.getContentPane().add(button_3);

		button_4 = new JButton("Cancelar reserva grupo");
		button_4.addActionListener(new ActionListener() {
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
						if (cpf.isEmpty()) {
							throw new Exception("O CPF deve conter pelo menos um número.");

						}

						excursao.cancelarReserva(cpf);
						excursao.salvar();

					} catch (NumberFormatException b) {
						JOptionPane.showMessageDialog(frame, "Erro: Por favor, insira valores numéricos válidos");
						return;

					} catch (Exception c) {
						JOptionPane.showMessageDialog(frame, "Erro: " + c.getMessage());
						return;
					}

					JOptionPane.showMessageDialog(frame, excursao);

				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}

			}
		});
		button_4.setFont(new Font("Arial", Font.BOLD, 12));
		button_4.setHorizontalAlignment(SwingConstants.LEFT);
		button_4.setBounds(32, 217, 203, 38);
		frame.getContentPane().add(button_4);

		button_5 = new JButton("Listar reserva por CPF");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = "";
				ArrayList<String> listaCpf = new ArrayList<>();
				textArea.setText("");

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

						listaCpf = excursao.listarReservasPorCpf(cpf);

						for (String str : listaCpf) {
							textArea.append(str + "\n"); // Adicione a string e uma quebra de linha
						}

						textArea.setVisible(true);

					} catch (Exception b) {
						JOptionPane.showMessageDialog(frame, "Erro: " + b.getMessage());
						return;

					}
				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}

			}
		});
		button_5.setFont(new Font("Arial", Font.BOLD, 12));
		button_5.setHorizontalAlignment(SwingConstants.LEFT);
		button_5.setBounds(32, 265, 203, 38);
		frame.getContentPane().add(button_5);

		button_6 = new JButton("Listar reserva por nome");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = "";
				ArrayList<String> listaNome = new ArrayList<>();
				textArea.setText("");

				if (excursaoSelecionada) {
					if (excursao.getListaReserva().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Erro: Não há registros de reserva na excursão");
						return;
					}

					try {
						nome = JOptionPane.showInputDialog(frame, "Digite o nome").toUpperCase();
						listaNome = excursao.listarReservasPorNome(nome);

						for (String str : listaNome) {
							textArea.append(str + "\n"); // Adicione a string e uma quebra de linha
						}

						textArea.setVisible(true);

					} catch (Exception a) {
						JOptionPane.showMessageDialog(frame, "Erro: " + a.getMessage());
						return;

					}
				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}
			}
		});
		button_6.setFont(new Font("Arial", Font.BOLD, 12));
		button_6.setHorizontalAlignment(SwingConstants.LEFT);
		button_6.setBounds(32, 313, 203, 38);

		frame.getContentPane().add(button_6);

		label = new JLabel("Código da excursão atual:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(new Color(255, 0, 0));
		label.setFont(new Font("Constantia", Font.BOLD, 17));
		label.setBounds(21, 420, 247, 38);
		frame.getContentPane().add(label);

		label_1 = new JLabel("Nenhuma excursão selecionada");
		label_1.setForeground(Color.BLACK);
		label_1.setBackground(Color.WHITE);
		label_1.setFont(new Font("Monospaced", Font.BOLD, 18));
		label_1.setBounds(267, 423, 335, 24);
		frame.getContentPane().add(label_1);

		button_7 = new JButton("Calcular valor total");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (excursaoSelecionada) {
					if (excursao.getListaReserva().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Erro: Não há registros de reserva na excursão");
						return;
					}

					double total = excursao.calcularValorTotal();
					JOptionPane.showMessageDialog(frame, "O valor total da excursão: R$" + total);
				} else {
					JOptionPane.showMessageDialog(frame, "Erro: Não tem nenhuma excursão selecionada");
				}

			}
		});
		button_7.setHorizontalAlignment(SwingConstants.LEFT);
		button_7.setFont(new Font("Arial", Font.BOLD, 12));
		button_7.setBounds(32, 361, 203, 38);
		frame.getContentPane().add(button_7);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setVisible(false);
		textArea.setBounds(515, 32, 286, 367);
		frame.getContentPane().add(textArea);
	}
}
