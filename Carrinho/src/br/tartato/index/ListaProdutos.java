package br.tartato.index;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.tartaro.pojos.Carrinho;
import br.tartaro.pojos.Produtos;

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
		Produtos a = new Produtos();
		a.setId(1);
		a.setNome("Produto 1");
		a.setQuantidade(10);
		a.setValor(100.00);
		TableProdutos.lista2.add(a);
		
		Produtos b = new Produtos();
		b.setId(2);
		b.setNome("Produto 2");
		b.setQuantidade(5);
		b.setValor(25.00);
		TableProdutos.lista2.add(b);
		
		Produtos c = new Produtos();
		c.setId(3);
		c.setNome("Produto 3");
		c.setQuantidade(2);
		c.setValor(5.00);
		TableProdutos.lista2.add(c);
		
		
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
					int valorQuant = produto.getQuantidade();
					produto.setQuantidade(valorQuant - quantCliente);
					Carrinho a = new Carrinho();
					a.setId(TableCarrinho.listaCarrinho.size()+1);
					a.setNome(produto.getNome());
					a.setQuantidade(produto.getQuantidade());
					a.setValor(produto.getValor()*quantCliente);
					TableCarrinho.listaCarrinho.add(a);
					
					
				}
			
			}
		});
		scrollPane.setViewportView(table);
		
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

}
