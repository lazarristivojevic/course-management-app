package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.KursCrud;
import crud.KursPolaznikCrud;
import crud.PolaznikCrud;
import model.LR56323Kurs;
import model.LR56323Polaznik;

public class DUpisNaKurs extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DUpisNaKurs dialog = new DUpisNaKurs();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DUpisNaKurs() {
		setTitle("Upiši na kurs");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPolaznik = new JLabel("Polaznik:");
			lblPolaznik.setBounds(22, 20, 68, 20);
			contentPanel.add(lblPolaznik);
		}
		{
			JLabel lblKurs = new JLabel("Kurs:");
			lblKurs.setBounds(22, 104, 68, 20);
			contentPanel.add(lblKurs);
		}
		
		JComboBox<LR56323Polaznik> cbPolaznik = new JComboBox<LR56323Polaznik>();
		cbPolaznik.setMaximumRowCount(50);
		cbPolaznik.setBounds(85, 19, 341, 21);
		contentPanel.add(cbPolaznik);
		//dodavanje Polaznika u kombo boks iz baze
		PolaznikCrud pc = new PolaznikCrud();
		List<LR56323Polaznik> polaznici = pc.listPolaznici();
		for(LR56323Polaznik p: polaznici) {
			cbPolaznik.addItem(p);
		}
		
		JComboBox<LR56323Kurs> cbKurs = new JComboBox<LR56323Kurs>();
		cbKurs.setMaximumRowCount(50);
		cbKurs.setBounds(85, 103, 341, 21);
		contentPanel.add(cbKurs);
		//dodavanje Kurseva u kombo boks iz baze
		KursCrud kc = new KursCrud();
		List<LR56323Kurs> kursevi = kc.listKursevi();
		for(LR56323Kurs k: kursevi){
			cbKurs.addItem(k);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Upiši");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						LR56323Polaznik p = (LR56323Polaznik) cbPolaznik.getSelectedItem();
				        LR56323Kurs k = (LR56323Kurs) cbKurs.getSelectedItem();

				        KursPolaznikCrud kpc = new KursPolaznikCrud();
				        kpc.poveziKursIPolaznika(k, p);

				        System.out.println("Polaznik " + p.getIme() + " uspešno upisan na kurs " + k.getNaziv());
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
