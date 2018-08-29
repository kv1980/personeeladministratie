package be.vdab.personeeladministratie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name="jobtitels")
public class Jobtitel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@SafeHtml
	private String naam;
	@Version
	private long versie;
	
	protected Jobtitel() {
	}
	
	public Jobtitel(String naam) {
		this.naam = naam;
	}
	public long getId() {
		return id;
	}
	public String getNaam() {
		return naam;
	}
	public long getVersie() {
		return versie;
	}
}
