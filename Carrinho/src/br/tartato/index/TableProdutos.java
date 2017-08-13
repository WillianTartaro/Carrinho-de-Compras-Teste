package br.tartato.index;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.tartaro.pojos.Produtos;

public class TableProdutos extends AbstractTableModel{
	
	public static List<Produtos> lista2 = new ArrayList<Produtos>();
	
	
	public List<Produtos> getLista2() {
	return lista2;
}

public void setLista2(List<Produtos> lista2) {
	this.lista2 = lista2;
	this.fireTableDataChanged();
}


	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista2.size();
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
			return "Quantidade Total";
		
		case 3:
			return "Valor";
		}
		return "erro";
	}

	@Override
	public Object getValueAt(int row, int col) {
		Produtos p = lista2.get(row);
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
	
	public void adicionarNoModel(Produtos p) {
	this.lista2.add(p);
	int row = this.lista2.size() -1;
	
	super.fireTableDataChanged();
	
	}

}
