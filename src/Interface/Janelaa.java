package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import Processamento.Excursao;

public class Janelaa {

	private JFrame frame;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JLabel label;
	private JLabel label_1;
	private Excursao excursao;
	private JButton button_7;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janelaa window = new Janelaa();
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
	public Janelaa() {
		initialize();
		try {
			excursao = new Excursao();
		
	}catch(Exception h){
		System.out.println("Erro:" + h.getMessage());
	}
	}
	boolean estaExcursao = false;

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
		        int codigo ;
				double preco ;
				int max ;
				try {
					
					String codigoStr = JOptionPane.showInputDialog(frame, "Digite o código da sua excursão");
					if (codigoStr == null) {
					    // O usuário pressionou "Cancelar" ou fechou a caixa de diálogo
					    return;
					}
		            codigo = Integer.parseInt(codigoStr);
		            
		            	
		           String precoStr = JOptionPane.showInputDialog(frame,"Digite o preço por pessoa");
		           
		           if (precoStr == null) {
		        	   return;
		           }
		           
		           preco = Double.parseDouble(precoStr);
		           
		           
		          
		           String maxStr = JOptionPane.showInputDialog(frame,"Digite a quantidade máxima de pessoas");
		           
		           if (maxStr == null) {
		        	   return;
		           }
		           
		           max = Integer.parseInt(maxStr);
		            
		         
		            Excursao teste1 = new Excursao(codigo,preco,max);
		            
		            teste1.exist();
		            
		            excursao = teste1;
		            excursao.salvar();
		            JOptionPane.showMessageDialog(frame, "Sua excursão foi criada com sucesso.");
		            String codstr = String.valueOf(codigo); 
		            label_1.setText(codstr);
		            estaExcursao = true;

		            
		            
		            

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(frame, "Por favor, insira valores numéricos válidos.");

		        } catch (Exception g) {
		            JOptionPane.showMessageDialog(frame, "Erro: " + g.getMessage());
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
				
				try {
					
					String codigoStr = JOptionPane.showInputDialog(frame,"Digite o código para recuperar");
					if (codigoStr == null) {
						return;
					}
					
				 codigo = Integer.parseInt(codigoStr);
				
				
				Excursao teste2 = new Excursao(codigo);
				
				teste2.notExist();
				teste2.ler();
				
				excursao = teste2;
				
				
				JOptionPane.showMessageDialog(frame,"Excursão recuperada com sucesso");
				
				String codstr = String.valueOf(codigo); 
	            label_1.setText(codstr);
	            estaExcursao = true;
				
				
					
					
					
				
				}catch(NumberFormatException exx){
					JOptionPane.showMessageDialog(frame, "Por favor, insira valores numéricos válidos.");
				}
				catch(Exception gg) {
					JOptionPane.showMessageDialog(frame,gg.getMessage());
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
			public void actionPerformed(ActionEvent e)  {
				
				String CPF = "" ;
				String nome = "";
				if (estaExcursao) {
					try {
				        String input = JOptionPane.showInputDialog(frame, "Digite o CPF, qualquer outro caracter, será desconsiderado");
				        
				        
				        
				        
				        // Remove espaços em branco e caracteres não numéricos da entrada
				        try {
				        CPF = input.replaceAll("[^0-9]", "");
				        
				        } catch(Exception u){
				        	return;
				        }
				        if (CPF.isEmpty()) {
				            throw new Exception("O CPF deve conter pelo menos um número.");
				            
				        }
				        }
				        catch (NumberFormatException exxx) {
				        JOptionPane.showMessageDialog(frame, "Erro: Por favor, insira valores numéricos válidos");
				        return;
				        
				        }
						
						catch(Exception p) {
				    	JOptionPane.showMessageDialog(frame, "Erro: " + p.getMessage());
				    	return;
				    }

				    try {
				         nome = JOptionPane.showInputDialog(frame, "Digite o nome");
				         
				         if (nome == null) {
				        	 return;
				         }
				        if (!nome.matches("^[a-zA-Z\\s]+$") || nome.matches(".*\\s{2,}.*")) {
				            throw new Exception("O nome deve conter apenas letras e espaços e não pode ter espaços consecutivos.");
				            
				        }
				        
				    } catch (Exception x) {
				        JOptionPane.showMessageDialog(frame, "Erro: " + x.getMessage());
				        return;
				    }
				    
				    try {
				    	excursao.criarReserva(CPF, nome);
				    }catch(Exception y){{
				    		JOptionPane.showMessageDialog(frame, "Erro: " + y.getMessage());
				    	}
				    }
				    try {
				    	excursao.salvar();
				    }catch(Exception z){
				    	JOptionPane.showMessageDialog(frame, "Erro: " + z.getMessage());
				    }
				    JOptionPane.showMessageDialog(frame,excursao);
				    
				    
				    
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
			}
		});
		button_3.setBounds(32, 169, 203, 38);
		frame.getContentPane().add(button_3);
		
		button_4 = new JButton("Cancelar reserva grupo");
		button_4.setFont(new Font("Arial", Font.BOLD, 12));
		button_4.setHorizontalAlignment(SwingConstants.LEFT);
		button_4.setBounds(32, 217, 203, 38);
		frame.getContentPane().add(button_4);
		
		button_5 = new JButton("Listar reserva por CPF");
		button_5.setFont(new Font("Arial", Font.BOLD, 12));
		button_5.setHorizontalAlignment(SwingConstants.LEFT);
		button_5.setBounds(32, 265, 203, 38);
		frame.getContentPane().add(button_5);
		
		button_6 = new JButton("Listar reserva por nome");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		label.setBounds(32, 411, 203, 38);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("Nenhuma excursão selecionada");
		label_1.setForeground(Color.BLACK);
		label_1.setBackground(Color.WHITE);
		label_1.setFont(new Font("Monospaced", Font.BOLD, 18));
		label_1.setBounds(249, 437, 335, 24);
		frame.getContentPane().add(label_1);
		
		button_7 = new JButton("Calcular valor total");
		button_7.setHorizontalAlignment(SwingConstants.LEFT);
		button_7.setFont(new Font("Arial", Font.BOLD, 12));
		button_7.setBounds(32, 361, 203, 38);
		frame.getContentPane().add(button_7);
	}
}
