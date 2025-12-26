package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.LR56323Polaznik;

public class TableModelPolazniciKursa extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<LR56323Polaznik> polaznici = null;
	String[] naziviKolona = {"ID", "Ime", "Prezime"};
	
	public TableModelPolazniciKursa(List<LR56323Polaznik> polaznici) {
		this.polaznici = polaznici;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return polaznici.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		LR56323Polaznik p = polaznici.get(rowIndex);
		switch (columnIndex) {
		case 0: return p.getPolaznikId();
				
		case 1: return p.getIme();
		
		case 2: return p.getPrezime();
		
		}
		
		return null;
	}
	
	public String getColumnName(int i) {
		return naziviKolona[i];
	}
	
}
