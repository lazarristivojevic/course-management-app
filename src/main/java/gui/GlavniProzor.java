package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GlavniProzor {

	private JFrame frmkolaStranihJezika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzor window = new GlavniProzor();
					window.frmkolaStranihJezika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GlavniProzor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmkolaStranihJezika = new JFrame();
		frmkolaStranihJezika.setBackground(Color.LIGHT_GRAY);
		frmkolaStranihJezika.getContentPane().setBackground(Color.GRAY);
		frmkolaStranihJezika.setTitle("Škola stranih jezika");
		frmkolaStranihJezika.setBounds(100, 100, 450, 300);
		frmkolaStranihJezika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmkolaStranihJezika.getContentPane().setLayout(null);
		
		JButton btnUnesiPolaznika = new JButton("Unesi Polaznika");
		btnUnesiPolaznika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//prikazujemo dijalog Unesi polaznika iz gl prozora
				DUnesiPolaznika dup = new DUnesiPolaznika();
				dup.setVisible(true);
			}
		});
		btnUnesiPolaznika.setBounds(103, 35, 229, 21);
		frmkolaStranihJezika.getContentPane().add(btnUnesiPolaznika);
		
		JButton btnObrisiPolaznika = new JButton("Obriši Polaznika");
		btnObrisiPolaznika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DObrisiPolaznika dop = new DObrisiPolaznika();
				dop.setVisible(true);
			}
		});
		btnObrisiPolaznika.setBounds(103, 91, 229, 21);
		frmkolaStranihJezika.getContentPane().add(btnObrisiPolaznika);
		
		JButton btnUpisiNaKurs = new JButton("Upiši na kurs");
		btnUpisiNaKurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DUpisNaKurs dunk = new DUpisNaKurs();
				dunk.setVisible(true);
			}
		});
		btnUpisiNaKurs.setBounds(103, 147, 229, 21);
		frmkolaStranihJezika.getContentPane().add(btnUpisiNaKurs);
		
		JButton btnSviPolaznici = new JButton("Svi Polaznici");
		btnSviPolaznici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DPrikazPolaznikaKursa dppk = new DPrikazPolaznikaKursa();
				dppk.setVisible(true);
			}
		});
		btnSviPolaznici.setBounds(103, 203, 229, 21);
		frmkolaStranihJezika.getContentPane().add(btnSviPolaznici);
	}
}
