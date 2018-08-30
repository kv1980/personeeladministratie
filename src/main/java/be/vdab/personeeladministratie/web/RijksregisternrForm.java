package be.vdab.personeeladministratie.web;

import java.time.LocalDate;

import be.vdab.personeeladministratie.constraints.Rijksregisternr;
import be.vdab.personeeladministratie.constraints.RijksregisternrKomtOvereenMetGeboortedatum;

@RijksregisternrKomtOvereenMetGeboortedatum
public class RijksregisternrForm {
	private LocalDate geboortedatum;
	@Rijksregisternr
	private long rijksregisternr;
	
	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}
	
	public void setGeboortedatum(LocalDate geboortedatum) {
		this.geboortedatum = geboortedatum;
	}
	
	public long getRijksregisternr() {
		return rijksregisternr;
	}
	
	public void setRijksregisternr(long rijksregisternr) {
		this.rijksregisternr = rijksregisternr;
	}
}
