package br.tartaro.pojos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.tartaro.pojos.Carrinho;
import br.tartaro.pojos.Produtos;


public class BancoDeDados {
	
	private static Connection con;
	
	public BancoDeDados() throws SQLException{
		AbrirConexao();
	}

	private void AbrirConexao() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/Carrinho";
		String user = "postgres";
		String pass = "123Willi@n";
		
		try {
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Conex�o Aberta!");
		} catch (SQLException e) {
			System.out.println("N�o Foi Possivel Fazer a Conex�o!");
		}
	}
	

	
	public static Connection getConexao() throws SQLException{
		if (con == null) {
			new BancoDeDados();
		}
		
		return con;
		
	}
	
	public void GravarCarrinho(Carrinho c){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO carrinho( ID, NOME, QUANTIDADE, VALOR) VALUES (?, ?, ?, ?)");
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNome());
			ps.setInt(3, c.getQuantidade());
			ps.setDouble(4, c.getValor());
		
			ps.executeUpdate();
			

			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void ExcluirCarrinho(Carrinho c) throws SQLException{
		PreparedStatement ps = con.prepareStatement("delete from carrinho where id = ?");
		ps.setInt(1, c.getId());
		ps.executeUpdate();
		ps.close();
	}
	
	
	public List<Produtos> produtoTabela() throws SQLException {
		List<Produtos> lista = new ArrayList<Produtos>();
		PreparedStatement ps = con.prepareStatement("SELECT * FROM PRODUTOS");
		//PreparedStatement ps = con.prepareStatement("SELECT * FROM produtos");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Produtos p = new Produtos();
			p.setId(rs.getInt("id"));
			p.setNome(rs.getString("nome"));
			p.setQuantidade(rs.getInt("quantidade"));
			p.setValor(rs.getDouble("valor"));

			
			lista.add(p);
		}
		
		return lista;
	}
	
	public List<Carrinho> carrinhoTabela() throws SQLException {
		List<Carrinho> lista = new ArrayList<Carrinho>();
		PreparedStatement ps = con.prepareStatement("SELECT * FROM CARRINHO");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Carrinho p = new Carrinho();
			p.setId(rs.getInt("id"));
			p.setNome(rs.getString("nome"));
			p.setQuantidade(rs.getInt("quantidade"));
			p.setValor(rs.getDouble("valor"));

			
			lista.add(p);
		}
		
		return lista;
	}
	
	
	
	
	public void AlteraCarrinho(Carrinho c) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("UPDATE carrinho SET nome=?, quantidade=?, valor=? where id=?");
			ps.setString(1, c.getNome());
			ps.setInt(2, c.getQuantidade());
			ps.setDouble(3, c.getValor());
			ps.setInt(4, c.getId());
		
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void AlteraProduto(Produtos p) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("UPDATE produtos SET nome=?, quantidade=?, valor=? where id=?");
			ps.setString(1, p.getNome());
			ps.setInt(2, p.getQuantidade());
			ps.setDouble(3, p.getValor());
			ps.setInt(4, p.getId());
			
		
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	




}
