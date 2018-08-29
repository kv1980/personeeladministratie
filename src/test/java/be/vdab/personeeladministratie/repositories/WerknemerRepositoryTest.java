package be.vdab.personeeladministratie.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.personeeladministratie.entities.Werknemer;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertJobtitels.sql")
@Sql("/insertWerknemers.sql")
public class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	WerknemerRepository repository;
	
	private long idVanTestChef() {
		return super.jdbcTemplate.queryForObject("select id from werknemers where familienaam='testchef'", Long.class);
	}
	
	private long idVanTestOndergeschikte1() {
		return super.jdbcTemplate.queryForObject("select id from werknemers where familienaam='testondergeschikte1'", Long.class);
	}
	
	@Test
	public void repository_vindt_een_werknemer() {
		Werknemer werknemer = repository.findById(idVanTestChef()).get();
		assertEquals("testchef",werknemer.getFamilienaam());
	}
	
	@Test
	public void repository_vindt_ondergeschikten_van_een_werknemer() {
		Werknemer werknemer = repository.findById(idVanTestChef()).get();
		assertEquals("testchef",werknemer.getFamilienaam());
		Set<Werknemer> ondergeschikten = werknemer.getOndergeschikten();
		assertEquals(2,ondergeschikten.size());
		Werknemer ondergeschikte1 = repository.findById(idVanTestOndergeschikte1()).get();
		assertTrue(ondergeschikten.contains(ondergeschikte1));
	}
}