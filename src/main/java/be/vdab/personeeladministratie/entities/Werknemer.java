package be.vdab.personeeladministratie.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import be.vdab.personeeladministratie.adapters.LocalDateAdapter;
import be.vdab.personeeladministratie.constraints.Rijksregisternr;

@Entity
@Table(name = "werknemers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Werknemer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@SafeHtml
	private String familienaam;
	@NotBlank
	@SafeHtml
	private String voornaam;
	@NotBlank
	@Email
	private String email;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "chefid")
	private Werknemer chef;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "jobtitelid")
	private Jobtitel jobtitel;
	@NumberFormat(style = Style.CURRENCY)
	@NotNull
	@Min(1)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal salaris;
	@NotBlank
	@SafeHtml
	private String paswoord;
	@DateTimeFormat(style = "S-")
	@NotNull
	@Past
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	private LocalDate geboorte;
	@NotNull
	@Rijksregisternr
	@Column(unique = true)
	private long rijksregisternr;
	@Version
	private long versie;
	@OneToMany(mappedBy = "chef")
	Set<Werknemer> ondergeschikten;
	
	protected Werknemer() {
	}

	public Werknemer(String familienaam,String voornaam,String email, Werknemer chef, Jobtitel jobtitel,
					BigDecimal salaris,String paswoord,LocalDate geboorte,long rijksregisternr,
					Set<Werknemer> ondergeschikten) {
		this.familienaam = familienaam;
		this.voornaam = voornaam;
		this.email = email;
		this.chef = chef;
		this.jobtitel = jobtitel;
		this.salaris = salaris;
		this.paswoord = paswoord;
		this.geboorte = geboorte;
		this.rijksregisternr = rijksregisternr;
		this.ondergeschikten = ondergeschikten;
	}

	public long getId() {
		return id;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getEmail() {
		return email;
	}

	public Werknemer getChef() {
		return chef;
	}
	
	public Jobtitel getJobtitel() {
		return jobtitel;
	}

	public BigDecimal getSalaris() {
		return salaris;
	}

	public String getPaswoord() {
		return paswoord;
	}

	public LocalDate getGeboorte() {
		return geboorte;
	}

	public long getRijksregisternr() {
		return rijksregisternr;
	}

	public long getVersie() {
		return versie;
	}

	public Set<Werknemer> getOndergeschikten() {
		return Collections.unmodifiableSet(ondergeschikten);
	}
	
	public boolean heeftChef() {
		return chef != null;
	}
	
	public boolean heeftOndergeschikten() {
		return !ondergeschikten.isEmpty();
	}
	
	public void wijzigRijksregisternr(long rijksregisternr) {
		this.rijksregisternr = rijksregisternr;
	}
	
	public void verhoogSalaris(BigDecimal bedrag) {
		this.salaris = this.salaris.add(bedrag);
	}
}
