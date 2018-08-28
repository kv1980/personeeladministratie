package be.vdab.personeeladministratie.constraints;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class RijksregisternrTest {
	private RijksregisternrValidator validator;
	
	@Before
	public void before() {
		validator=new RijksregisternrValidator();
	}
	
	@Test
	public void correct_rijksregisternr_voor_2000() {
		assertTrue(validator.isValid(66080100153L,null));
	}
	
	@Test
	public void correct_rijksregisternr_na_2000() {
		assertTrue(validator.isValid(10100105L,null));
	}
	
	@Test
	public void negatief_rijksregisternr_kan_niet() {
		assertFalse(validator.isValid(-66080100153L,null));
	}
	
	@Test
	public void te_kort_rijksregisternr_kan_niet() {
		assertFalse(validator.isValid(31068001L,null));
	}
}
