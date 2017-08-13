package br.tartato.index;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.tartaro.pojos.Carrinho;
import br.tartaro.pojos.Produtos;
import br.tartaro.pojos.BancoDeDados;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListaProdutos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableProdutos model2 = new TableProdutos();
	private TableCarrinho modelCarrinho = new TableCarrinho();
	private JMenuBar menuBar;
	private JMenu mnAtalhos;
	private JMenuItem mntmCarrinho;
	private int quantCliente;
	private JLabel lblNewLabel;
	public double valorTotal;


	public double getValorTotal() {
		return valorTotal;
	}



	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {		
					ListaProdutos frame = new ListaProdutos();
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
	public ListaProdutos() {

		
		
		setTitle("Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		mnAtalhos = new JMenu("Atalhos");
		menuBar.add(mnAtalhos);
		
		mntmCarrinho = new JMenuItem("Carrinho");
		mntmCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListCarrinho carrinho = new ListCarrinho();
				carrinho.setVisible(true);
			}
		});
		mnAtalhos.add(mntmCarrinho);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable(model2);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					CarregarDaTabelaProduto(model2.getLista2().get(table.getSelectedRow()));
				}
			}

			private void CarregarDaTabelaProduto(Produtos produto) {
				quantCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite a Quantidade que deseja", null ));
				if(quantCliente > produto.getQuantidade()){
					JOptionPane.showMessageDialog(ListaProdutos.this, "Não temos essa quantidade em estoque");
				} else {
					JOptionPane.showMessageDialog(ListaProdutos.this, "Produto Adicionado ao Carrinho!");
					
					
					Carrinho c = new Carrinho();
					
					c.setId(TableCarrinho.listaCarrinho.size()+1);
					c.setNome(produto.getNome());
					c.setQuantidade(quantCliente);
					c.setValor(produto.getValor()*quantCliente);

					modelCarrinho.adicionarNoModel(c);
					
					
					produto.setId(produto.getId());
					produto.setNome(produto.getNome());
					int valorQuant = produto.getQuantidade();
					produto.setQuantidade(valorQuant - quantCliente);
					produto.setValor(produto.getValor());
					
					
					try {
						br.tartaro.pojos.BancoDeDados banco2 = new BancoDeDados();
						banco2.AlteraProduto(produto);
						banco2.GravarCarrinho(c);
						AtualizaTabel();
					} catch (SQLException e) {
						// TODO: handle exception
					}
					
					
					
				}
			
			}

		
		});
		scrollPane.setViewportView(table);
		AtualizaTabel();
		
		
		lblNewLabel = new JLabel("Dê dois cliques no produto de sua escolha para adicionar ao carrinho");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}



	private void AtualizaTabel() {
		
		try {
			BancoDeDados banco = new BancoDeDados();
			model2.setLista2(banco.produtoTabela());
			
		} catch (SQLException e) {
			// TODO: handle exception
		}	
}

}
