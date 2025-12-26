package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the LR56323_POLAZNIK database table.
 * 
 */
@Entity
@Table(name="LR56323_POLAZNIK")
@NamedQuery(name="LR56323Polaznik.findAll", query="SELECT l FROM LR56323Polaznik l")
public class LR56323Polaznik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LR56323_POLAZNIK_POLAZNIKID_GENERATOR", sequenceName="LR56323_POLAZNIK_PK")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LR56323_POLAZNIK_POLAZNIKID_GENERATOR")
	@Column(name="POLAZNIK_ID")
	private long polaznikId;

	private String ime;

	private String prezime;

	//bi-directional many-to-one association to LR56323KursPolaznik
	@OneToMany(mappedBy="LR56323Polaznik")
	private List<LR56323KursPolaznik> LR56323KursPolazniks;

	public LR56323Polaznik() {
		this.LR56323KursPolazniks = new ArrayList<>();
	}

	
	public LR56323Polaznik(String ime, String prezime) {
		this();
		this.ime = ime;
		this.prezime = prezime;
	}


	public long getPolaznikId() {
		return this.polaznikId;
	}

	public void setPolaznikId(long polaznikId) {
		this.polaznikId = polaznikId;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<LR56323KursPolaznik> getLR56323KursPolazniks() {
		return this.LR56323KursPolazniks;
	}

	public void setLR56323KursPolazniks(List<LR56323KursPolaznik> LR56323KursPolazniks) {
		this.LR56323KursPolazniks = LR56323KursPolazniks;
	}

	public LR56323KursPolaznik addLR56323KursPolaznik(LR56323KursPolaznik LR56323KursPolaznik) {
		getLR56323KursPolazniks().add(LR56323KursPolaznik);
		LR56323KursPolaznik.setLR56323Polaznik(this);

		return LR56323KursPolaznik;
	}

	public LR56323KursPolaznik removeLR56323KursPolaznik(LR56323KursPolaznik LR56323KursPolaznik) {
		getLR56323KursPolazniks().remove(LR56323KursPolaznik);
		LR56323KursPolaznik.setLR56323Polaznik(null);

		return LR56323KursPolaznik;
	}


	@Override
	public String toString() {
		return polaznikId + " "  + ime  + " " + prezime;
	}

	
}