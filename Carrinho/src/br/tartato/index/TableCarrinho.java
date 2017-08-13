package br.tartato.index;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.tartaro.pojos.Carrinho;

public class TableCarrinho extends AbstractTableModel{
	
	public static List<Carrinho> listaCarrinho = new ArrayList<Carrinho>();
	
	
	public List<Carrinho> getListaCarrinho() {
	return listaCarrinho;
}

public void setListaCarrinho(List<Carrinho> listaCarrinho) {
	this.listaCarrinho = listaCarrinho;
	this.fireTableDataChanged();
}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaCarrinho.size()                                                                                                                                                                                                                                                                ;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Id";
		case 1:
			return "Nome";
		case 2:
			return "Quantidade Selecionada";
		case 3:
			return "Valor";
		}
		return "erro";
	}

	@Override
	public Object getValueAt(int row, int col) {
		Carrinho p = listaCarrinho.get(row);
		switch (col) {
		case 0:
			return p.getId();
		case 1:
			return p.getNome();
		case 2:
			return p.getQuantidade();
		case 3:
			return p.getValor();
		}
		return "erro";
		
		
	}
	
	public void adicionarNoModel(Carrinho p) {
	this.listaCarrinho.add(p);
	int row = this.listaCarrinho.size() -1;
	
	super.fireTableDataChanged();
	
	}

}
