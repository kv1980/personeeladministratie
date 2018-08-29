package be.vdab.personeeladministratie.entities;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(Werknemer.class)
public class WerknemerTest {
	@Autowired
	Werknemer werknemer;
	
	@Before
	public void before() {
		werknemer=new Werknemer("test","test","test@vdab.be",new Jobtitel("werknemer"),BigDecimal.valueOf(2000),"test",LocalDate.of(1980,01,01),66080100153L);		
	}
	
	@Test
	public void rijksregisternummer_kan_worden_gewijzigd_met_correct_nummer() {
		werknemer.wijzigRijksregisternr(80061327389L);
		assertEquals(80061327389L,werknemer.getRijksregisternr());
	}
	
	@Test
	public void salaris_wordt_correct_verhoogd() {
		werknemer.verhoogSalaris(BigDecimal.valueOf(100));
		assertEquals(BigDecimal.valueOf(2100),werknemer.getSalaris());
	}
	
	@Test
	public void rijksregisternummer_is_goed_getest() {
		werknemer.wijzigRijksregisternr(80061327389L);
		assertEquals(80061327389L,werknemer.getRijksregisternr());
	}
}
