package br.tartato.index;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.tartaro.pojos.Carrinho;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListCarrinho extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableCarrinho modelCarrinho = new TableCarrinho();
	ListaProdutos pp = new ListaProdutos();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListCarrinho frame = new ListCarrinho();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListCarrinho() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		


		
		JButton btnRemover = new JButton("Remover");

		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto(modelCarrinho.getListaCarrinho().get(table.getSelectedRow()));
			}

			private void excluirProduto(Carrinho carrinho) {
					for (int i = 0; i < TableCarrinho.listaCarrinho.size(); i++) {
						//TableCarrinho.listaCarrinho.remove(carrinho.getNome());
						TableCarrinho.listaCarrinho.remove(i+1).equals(carrinho.getNome());
						
					}
				
			}
		});
		
		JButton btnGerarPagamento = new JButton("Gerar Pagamento");
		btnGerarPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double valorTotal = 0;
				for (int i = 0; i < TableCarrinho.listaCarrinho.size(); i++) {
					valorTotal += TableCarrinho.listaCarrinho.get(i).getValor();
				}
				
				int quero = JOptionPane.showConfirmDialog(ListCarrinho.this, "O Valor total da sua compra é de : R$"+valorTotal+ "Deseja Continuar? ");
			}
		});
		ListaProdutos pp = new ListaProdutos();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRemover)
							.addPreferredGap(ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
							.addComponent(btnGerarPagamento)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGerarPagamento)
						.addComponent(btnRemover)))
		);
		
		table = new JTable(modelCarrinho);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
