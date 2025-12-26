package gui;

import crud.PolaznikCrud;
import model.LR56323Polaznik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DObrisiPolaznika extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<LR56323Polaznik> cbPolaznik;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DObrisiPolaznika dialog = new DObrisiPolaznika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DObrisiPolaznika() {
		setTitle("Obriši polaznika");
		setBounds(100, 100, 400, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblPolaznik = new JLabel("Izaberi polaznika:");
		lblPolaznik.setBounds(20, 30, 120, 20);
		contentPanel.add(lblPolaznik);

		cbPolaznik = new JComboBox<LR56323Polaznik>();
		cbPolaznik.setBounds(150, 30, 200, 22);
		contentPanel.add(cbPolaznik);

		// Popunjavanje ComboBox-a
		PolaznikCrud pc = new PolaznikCrud();
		List<LR56323Polaznik> polaznici = pc.listPolaznici();
		for (LR56323Polaznik p : polaznici) {
			cbPolaznik.addItem(p);
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Obriši");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LR56323Polaznik izabrani = (LR56323Polaznik) cbPolaznik.getSelectedItem();
				if (izabrani != null) {
					int potvrda = JOptionPane.showConfirmDialog(DObrisiPolaznika.this,
							"Da li si siguran da želiš da obrišeš polaznika: " + izabrani,
							"Potvrda brisanja", JOptionPane.YES_NO_OPTION);

					if (potvrda == JOptionPane.YES_OPTION) {
						boolean obrisan = pc.deletePolaznik(izabrani.getPolaznikId());
						if (obrisan) {
							JOptionPane.showMessageDialog(DObrisiPolaznika.this, "Polaznik uspešno obrisan!");
							cbPolaznik.removeItem(izabrani);
						} else {
							JOptionPane.showMessageDialog(DObrisiPolaznika.this, "Greška pri brisanju polaznika!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(DObrisiPolaznika.this, "Nema izabranog polaznika.");
				}
			}
		});
		buttonPane.add(okButton);

		JButton cancelButton = new JButton("Zatvori");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonPane.add(cancelButton);
	}
}
