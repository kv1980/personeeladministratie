package be.vdab.personeeladministratie.constraints;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.vdab.personeeladministratie.web.RijksregisternrForm;

public class RijksregisternrKomtOvereenMetGeboortedatumTest {
	private RijksregisternrKomtOvereenMetGeboortedatumValidator validator;
	private RijksregisternrForm form;
	
	@Before
	public void before(){
		validator = new RijksregisternrKomtOvereenMetGeboortedatumValidator();
		form = new RijksregisternrForm();
	}
	
	@Test
	public void gevalideerd_als_rijksregisternr_en_geboortedatum_overeenkomen() {
		form.setGeboortedatum(LocalDate.of(1970,01,01));
		form.setRijksregisternr(70010100188L);
		assertTrue(validator.isValid(form,null));		
	}
	
	@Test
	public void niet_gevalideerd_als_rijksregisternr_en_geboortedatum_niet_overeenkomen() {
		form.setGeboortedatum(LocalDate.of(1970,01,01));
		form.setRijksregisternr(80010100188L);
		assertFalse(validator.isValid(form,null));		
	}
}
