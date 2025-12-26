package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the LR56323_NIVO database table.
 * 
 */
@Entity
@Table(name="LR56323_NIVO")
@NamedQuery(name="LR56323Nivo.findAll", query="SELECT l FROM LR56323Nivo l")
public class LR56323Nivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LR56323_NIVO_NIVOID_GENERATOR", sequenceName="LR56323_NIVO_PK")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LR56323_NIVO_NIVOID_GENERATOR")
	@Column(name="NIVO_ID")
	private long nivoId;

	private String oznaka;

	//bi-directional many-to-one association to LR56323Kurs
	@OneToMany(mappedBy="LR56323Nivo")
	private List<LR56323Kurs> LR56323Kurs;

	public LR56323Nivo() {
		this.LR56323Kurs = new ArrayList<>();
	}
		

	public LR56323Nivo(String oznaka) {
		this();
		this.oznaka = oznaka;
	}

	public long getNivoId() {
		return this.nivoId;
	}

	public void setNivoId(long nivoId) {
		this.nivoId = nivoId;
	}

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public List<LR56323Kurs> getLR56323Kurs() {
		return this.LR56323Kurs;
	}

	public void setLR56323Kurs(List<LR56323Kurs> LR56323Kurs) {
		this.LR56323Kurs = LR56323Kurs;
	}

	public LR56323Kurs addLR56323Kur(LR56323Kurs LR56323Kur) {
		getLR56323Kurs().add(LR56323Kur);
		LR56323Kur.setLR56323Nivo(this);

		return LR56323Kur;
	}

	public LR56323Kurs removeLR56323Kur(LR56323Kurs LR56323Kur) {
		getLR56323Kurs().remove(LR56323Kur);
		LR56323Kur.setLR56323Nivo(null);

		return LR56323Kur;
	}


	@Override
	public String toString() {
		return " [oznaka=" + oznaka + "]";
	}
	
	

}