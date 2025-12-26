package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.PolaznikCrud;
import model.LR56323Polaznik;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class DUnesiPolaznika extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfIme;
	private JTextField tfPrezime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DUnesiPolaznika dialog = new DUnesiPolaznika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DUnesiPolaznika() {
		setBackground(Color.WHITE);
		setTitle("Unesi Polaznika");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblIme = new JLabel("Ime:");
			lblIme.setBounds(23, 22, 72, 19);
			contentPanel.add(lblIme);
		}
		
		tfIme = new JTextField();
		tfIme.setBounds(82, 22, 331, 19);
		contentPanel.add(tfIme);
		tfIme.setColumns(30);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(23, 61, 72, 19);
		contentPanel.add(lblPrezime);
		{
			tfPrezime = new JTextField();
			tfPrezime.setBounds(82, 61, 331, 19);
			contentPanel.add(tfPrezime);
			tfPrezime.setColumns(30);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Unesi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//uzimamo ime i prezime polaznika i kreiramo ga
						String ime = tfIme.getText();
						String prezime = tfPrezime.getText();
						LR56323Polaznik polaznik = new LR56323Polaznik(ime, prezime);
						
						//dodajemo polaznika u tabelu
						PolaznikCrud pc = new PolaznikCrud();
						pc.insertPolaznik(polaznik);
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
