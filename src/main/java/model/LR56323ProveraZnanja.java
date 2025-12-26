package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LR56323_PROVERA_ZNANJA database table.
 * 
 */
@Entity
@Table(name="LR56323_PROVERA_ZNANJA")
@NamedQuery(name="LR56323ProveraZnanja.findAll", query="SELECT l FROM LR56323ProveraZnanja l")
public class LR56323ProveraZnanja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LR56323_PROVERA_ZNANJA_PROVERAID_GENERATOR", sequenceName="LR56323_PROVERA_ZNANJA_PK")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LR56323_PROVERA_ZNANJA_PROVERAID_GENERATOR")
	@Column(name="PROVERA_ID")
	private long proveraId;

	@Column(name="DATUM_VREME")
	private Timestamp datumVreme;

	private BigDecimal rezultat;

	//bi-directional many-to-one association to LR56323KursPolaznik
	@ManyToOne
	@JoinColumn(name="KURS_POLAZNIK_ID")
	private LR56323KursPolaznik LR56323KursPolaznik;

	public LR56323ProveraZnanja() {
	}

	public long getProveraId() {
		return this.proveraId;
	}

	public void setProveraId(long proveraId) {
		this.proveraId = proveraId;
	}

	public Timestamp getDatumVreme() {
		return this.datumVreme;
	}

	public void setDatumVreme(Timestamp datumVreme) {
		this.datumVreme = datumVreme;
	}

	public BigDecimal getRezultat() {
		return this.rezultat;
	}

	public void setRezultat(BigDecimal rezultat) {
		this.rezultat = rezultat;
	}

	public LR56323KursPolaznik getLR56323KursPolaznik() {
		return this.LR56323KursPolaznik;
	}

	public void setLR56323KursPolaznik(LR56323KursPolaznik LR56323KursPolaznik) {
		this.LR56323KursPolaznik = LR56323KursPolaznik;
	}

}