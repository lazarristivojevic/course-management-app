package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the LR56323_PREDAVAC database table.
 * 
 */
@Entity
@Table(name="LR56323_PREDAVAC")
@NamedQuery(name="LR56323Predavac.findAll", query="SELECT l FROM LR56323Predavac l")
public class LR56323Predavac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LR56323_PREDAVAC_PREDAVACID_GENERATOR", sequenceName="LR56323_PREDAVAC_PK")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LR56323_PREDAVAC_PREDAVACID_GENERATOR")
	@Column(name="PREDAVAC_ID")
	private long predavacId;

	private String ime;

	private String prezime;

	//bi-directional many-to-many association to LR56323Kurs
	@ManyToMany
	@JoinTable(
		name="LR56323_KURS_PREDAVAC"
		, joinColumns={
			@JoinColumn(name="PREDAVAC_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="KURS_ID")
			}
		)
	private List<LR56323Kurs> LR56323Kurs;

	public LR56323Predavac() {
		this.LR56323Kurs = new ArrayList<>();
	}
	

	public LR56323Predavac(String ime, String prezime) {
		this();
		this.ime = ime;
		this.prezime = prezime;
	}



	public long getPredavacId() {
		return this.predavacId;
	}

	public void setPredavacId(long predavacId) {
		this.predavacId = predavacId;
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

	public List<LR56323Kurs> getLR56323Kurs() {
		return this.LR56323Kurs;
	}

	public void setLR56323Kurs(List<LR56323Kurs> LR56323Kurs) {
		this.LR56323Kurs = LR56323Kurs;
	}


	@Override
	public String toString() {
		return " [predavacId=" + predavacId + ", ime=" + ime + ", prezime=" + prezime + "]";
	}
	
	

}