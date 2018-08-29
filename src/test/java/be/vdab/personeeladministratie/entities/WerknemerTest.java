package be.vdab.personeeladministratie.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class WerknemerTest {
	Werknemer chef;
	Werknemer ondergeschikte;
	
	@Before
	public void before() {
		Set<Werknemer> ondergeschikten = new HashSet<>();
		chef=new Werknemer("testChef","test","test@vdab.be",null,new Jobtitel("werknemer"),BigDecimal.valueOf(2000),"test",
							LocalDate.of(1980,01,01),66080100153L,ondergeschikten);	
		ondergeschikte=new Werknemer("testOndergeschikte","test","test@vdab.be",chef,new Jobtitel("werknemer"),BigDecimal.valueOf(2000),"test",
									LocalDate.of(1980,01,01),66080100153L,new HashSet<>());
		ondergeschikten.add(ondergeschikte);
	}
	
	@Test
	public void een_werknemer_heeft_mogelijks_een_chef() {
		assertFalse(chef.heeftChef());
		assertTrue(ondergeschikte.heeftChef());
	}
	
	@Test
	public void een_werknemer_heeft_mogelijks_ondergeschikten() {
		assertTrue(chef.heeftOndergeschikten());
		assertFalse(ondergeschikte.heeftOndergeschikten());
	}
	
	@Test
	public void rijksregisternummer_kan_worden_gewijzigd() {
		chef.wijzigRijksregisternr(80061327389L);
		assertEquals(80061327389L,chef.getRijksregisternr());
	}
	
	@Test
	public void salaris_kan_met_een_bedrag_worden_verhoogd() {
		chef.verhoogSalaris(BigDecimal.valueOf(100));
		assertEquals(BigDecimal.valueOf(2100),chef.getSalaris());
	}
}
