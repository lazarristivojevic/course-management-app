package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LR56323_KURS database table.
 * 
 */
@Entity
@Table(name="LR56323_KURS")
@NamedQuery(name="LR56323Kurs.findAll", query="SELECT l FROM LR56323Kurs l")
public class LR56323Kurs implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LR56323_KURS_KURSID_GENERATOR", sequenceName="LR56323_KURS_PK")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LR56323_KURS_KURSID_GENERATOR")
	@Column(name="KURS_ID")
	private long kursId;

	@Temporal(TemporalType.DATE)
	@Column(name="DATUM_POCETKA")
	private Date datumPocetka;

	@Temporal(TemporalType.DATE)
	@Column(name="DATUM_ZAVRSETKA")
	private Date datumZavrsetka;

	private String naziv;

	//bi-directional many-to-one association to LR56323Nivo
	@ManyToOne
	@JoinColumn(name="NIVO_ID")
	private LR56323Nivo LR56323Nivo;

	//bi-directional many-to-one association to LR56323KursPolaznik
	@OneToMany(mappedBy="LR56323Kurs")
	private List<LR56323KursPolaznik> LR56323KursPolazniks;

	//bi-directional many-to-many association to LR56323Predavac
	@ManyToMany(mappedBy="LR56323Kurs")
	private List<LR56323Predavac> LR56323Predavacs;

	public LR56323Kurs() {
		this.LR56323Predavacs = new ArrayList<>();
	}
	
	public LR56323Kurs(String naziv) {
		this();
		this.naziv = naziv;
	}



	public long getKursId() {
		return this.kursId;
	}

	public void setKursId(long kursId) {
		this.kursId = kursId;
	}

	public Date getDatumPocetka() {
		return this.datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public Date getDatumZavrsetka() {
		return this.datumZavrsetka;
	}

	public void setDatumZavrsetka(Date datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public LR56323Nivo getLR56323Nivo() {
		return this.LR56323Nivo;
	}

	public void setLR56323Nivo(LR56323Nivo LR56323Nivo) {
		this.LR56323Nivo = LR56323Nivo;
	}

	public List<LR56323KursPolaznik> getLR56323KursPolazniks() {
		return this.LR56323KursPolazniks;
	}

	public void setLR56323KursPolazniks(List<LR56323KursPolaznik> LR56323KursPolazniks) {
		this.LR56323KursPolazniks = LR56323KursPolazniks;
	}

	public LR56323KursPolaznik addLR56323KursPolaznik(LR56323KursPolaznik LR56323KursPolaznik) {
		getLR56323KursPolazniks().add(LR56323KursPolaznik);
		LR56323KursPolaznik.setLR56323Kurs(this);

		return LR56323KursPolaznik;
	}

	public LR56323KursPolaznik removeLR56323KursPolaznik(LR56323KursPolaznik LR56323KursPolaznik) {
		getLR56323KursPolazniks().remove(LR56323KursPolaznik);
		LR56323KursPolaznik.setLR56323Kurs(null);

		return LR56323KursPolaznik;
	}

	public List<LR56323Predavac> getLR56323Predavacs() {
		return this.LR56323Predavacs;
	}

	public void setLR56323Predavacs(List<LR56323Predavac> LR56323Predavacs) {
		this.LR56323Predavacs = LR56323Predavacs;
	}

	@Override
	public String toString() {
		return kursId + " " + naziv;
	}
	
	

}