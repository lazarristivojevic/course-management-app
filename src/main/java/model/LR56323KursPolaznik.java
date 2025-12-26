package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the LR56323_KURS_POLAZNIK database table.
 * 
 */
@Entity
@Table(name="LR56323_KURS_POLAZNIK")
@NamedQuery(name="LR56323KursPolaznik.findAll", query="SELECT l FROM LR56323KursPolaznik l")
public class LR56323KursPolaznik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LR56323_KURS_POLAZNIK_KURSPOLAZNIKID_GENERATOR", sequenceName="LR56323_KURS_POLAZNIK_PK")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LR56323_KURS_POLAZNIK_KURSPOLAZNIKID_GENERATOR")
	@Column(name="KURS_POLAZNIK_ID")
	private long kursPolaznikId;

	//bi-directional many-to-one association to LR56323Kurs
	@ManyToOne
	@JoinColumn(name="KURS_ID")
	private LR56323Kurs LR56323Kurs;

	//bi-directional many-to-one association to LR56323Polaznik
	@ManyToOne
	@JoinColumn(name="POLAZNIK_ID")
	private LR56323Polaznik LR56323Polaznik;

	//bi-directional many-to-one association to LR56323ProveraZnanja
	@OneToMany(mappedBy="LR56323KursPolaznik")
	private List<LR56323ProveraZnanja> LR56323ProveraZnanjas;

	public LR56323KursPolaznik() {
		this.LR56323ProveraZnanjas = new ArrayList<>();
	}

	
	public LR56323KursPolaznik(model.LR56323Kurs lR56323Kurs, model.LR56323Polaznik lR56323Polaznik) {
		this();
		this.LR56323Kurs = lR56323Kurs;
		this.LR56323Polaznik = lR56323Polaznik;
	}


	public long getKursPolaznikId() {
		return this.kursPolaznikId;
	}

	public void setKursPolaznikId(long kursPolaznikId) {
		this.kursPolaznikId = kursPolaznikId;
	}

	public LR56323Kurs getLR56323Kurs() {
		return this.LR56323Kurs;
	}

	public void setLR56323Kurs(LR56323Kurs LR56323Kurs) {
		this.LR56323Kurs = LR56323Kurs;
	}

	public LR56323Polaznik getLR56323Polaznik() {
		return this.LR56323Polaznik;
	}

	public void setLR56323Polaznik(LR56323Polaznik LR56323Polaznik) {
		this.LR56323Polaznik = LR56323Polaznik;
	}

	public List<LR56323ProveraZnanja> getLR56323ProveraZnanjas() {
		return this.LR56323ProveraZnanjas;
	}

	public void setLR56323ProveraZnanjas(List<LR56323ProveraZnanja> LR56323ProveraZnanjas) {
		this.LR56323ProveraZnanjas = LR56323ProveraZnanjas;
	}

	public LR56323ProveraZnanja addLR56323ProveraZnanja(LR56323ProveraZnanja LR56323ProveraZnanja) {
		getLR56323ProveraZnanjas().add(LR56323ProveraZnanja);
		LR56323ProveraZnanja.setLR56323KursPolaznik(this);

		return LR56323ProveraZnanja;
	}

	public LR56323ProveraZnanja removeLR56323ProveraZnanja(LR56323ProveraZnanja LR56323ProveraZnanja) {
		getLR56323ProveraZnanjas().remove(LR56323ProveraZnanja);
		LR56323ProveraZnanja.setLR56323KursPolaznik(null);

		return LR56323ProveraZnanja;
	}

}