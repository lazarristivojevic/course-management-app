package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.KursCrud;
import crud.KursPolaznikCrud;
import model.LR56323Kurs;
import model.LR56323Polaznik;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class DPrikazPolaznikaKursa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	//bolje ovde da ih deklarisemo da bi bili vidljivi u svim blokovima klase
	JComboBox<LR56323Kurs> cbKurs;
	KursCrud kc = new KursCrud();
	KursPolaznikCrud kpc = new KursPolaznikCrud();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DPrikazPolaznikaKursa dialog = new DPrikazPolaznikaKursa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DPrikazPolaznikaKursa() {
		setTitle("Svi polaznici");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.LIGHT_GRAY);
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblKurs = new JLabel("Odaberi kurs");
				panel.add(lblKurs);
			}
			{
				cbKurs = new JComboBox<LR56323Kurs>();
				cbKurs.setMaximumRowCount(50);
				panel.add(cbKurs);
				//dodavanje Kurseva u kombo boks iz baze
				//KursCrud kc = new KursCrud();
				List<LR56323Kurs> kursevi = kc.listKursevi();
				for(LR56323Kurs k: kursevi){
					cbKurs.addItem(k);
				}
			}
			{
				JButton btnPrikaziPolaznike = new JButton("Prikaži");
				btnPrikaziPolaznike.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LR56323Kurs k = (LR56323Kurs) cbKurs.getSelectedItem();
						List<LR56323Polaznik> polaznici = kpc.findPolazniciZaKurs(k.getKursId());
						TableModelPolazniciKursa tmpk = new TableModelPolazniciKursa(polaznici);
						table.setModel(tmpk);
					}
				});
				panel.add(btnPrikaziPolaznike);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setBackground(Color.LIGHT_GRAY);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Zatvori");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
