package be.vdab.personeeladministratie.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.personeeladministratie.entities.Werknemer;
import be.vdab.personeeladministratie.exceptions.WerknemerNietGevondenException;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertJobtitels.sql")
@Sql("/insertWerknemers.sql")
@Import(WerknemerServiceImpl.class)
public class WerknemerServiceImplIntergrationTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	WerknemerServiceImpl service;
	@Autowired
	EntityManager manager;
	
	private long idVanTestOndergeschikte1() {
		return super.jdbcTemplate.queryForObject("select id from werknemers where familienaam='testondergeschikte1'", Long.class);
	}
	
	@Test
	public void de_president_wordt_gevonden() {
		Werknemer president = service.vindPresident();
		assertEquals("President",president.getJobtitel().getNaam());
	}
	
	@Test
	public void een_bestaande_werknemer_wordt_gevonden() {
		Werknemer werknemer = service.vindWerknemer(idVanTestOndergeschikte1());
		assertEquals("testondergeschikte1",werknemer.getFamilienaam());
	}
	
	@Test (expected = WerknemerNietGevondenException.class)
	public void een_onbestaande_werknemer_wordt_niet_gevonden() {
		Werknemer werknemer = service.vindWerknemer(-1L);
		assertEquals("testondergeschikte1",werknemer.getFamilienaam());
	}
	
	@Test
	public void salaris_kan_met_een_bedrag_worden_verhoogd() {
		long id = idVanTestOndergeschikte1();
		Werknemer werknemer = service.vindWerknemer(id);
		BigDecimal vorigSalaris = werknemer.getSalaris();
		BigDecimal extraSalaris = BigDecimal.valueOf(1000);
		service.verhoogSalaris(werknemer, extraSalaris);
		manager.flush();
		BigDecimal nieuwSalaris = super.jdbcTemplate.queryForObject(
				"select salaris from werknemers where id=?",BigDecimal.class,id);
		assertEquals(0,nieuwSalaris.compareTo(vorigSalaris.add(extraSalaris)));
	}
	
	@Test
	public void rijksregisternr_kan_met_een_correct_rijksregisternr_worden_gewijzigd() {
		long id = idVanTestOndergeschikte1();
		Werknemer werknemer = service.vindWerknemer(id);
		long origineelRijksregisternr = werknemer.getRijksregisternr();
		long nieuwRijksregisternr = 81010100128L;
		service.wijzigRijksregisternr(werknemer, nieuwRijksregisternr);
		manager.flush();
		long gewijzigdRijksregisternr = super.jdbcTemplate.queryForObject(
				"select rijksregisternr from werknemers where id=?",Long.class,id);
		assertEquals(nieuwRijksregisternr,gewijzigdRijksregisternr);
		assertNotEquals(origineelRijksregisternr,gewijzigdRijksregisternr);
	}
	
	@Test (expected = ConstraintViolationException.class)
	public void rijksregisternr_mag_met_een_foutief_rijksregisternr_niet_worden_gewijzigd() {
		long id = idVanTestOndergeschikte1();
		Werknemer werknemer = service.vindWerknemer(id);
		long nieuwRijksregisternr = 81010100129L;
		service.wijzigRijksregisternr(werknemer, nieuwRijksregisternr);
		manager.flush();
	}
	
	@Test (expected = PersistenceException.class)
	public void rijksregisternr_mag_met_een_reeds_bestaaand_rijksregisternr_niet_worden_gewijzigd() {
		long id = idVanTestOndergeschikte1();
		Werknemer werknemer = service.vindWerknemer(id);
		long nieuwRijksregisternr = 90010100123L;
			service.wijzigRijksregisternr(werknemer, nieuwRijksregisternr);
			manager.flush();
	}
}
