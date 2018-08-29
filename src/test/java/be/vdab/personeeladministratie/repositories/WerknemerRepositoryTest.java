package be.vdab.personeeladministratie.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
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
	public void repository_vindt_ondergeschikten_van_een_chef() {
		Werknemer testChef = repository.findById(idVanTestChef()).get();
		Set<Werknemer> ondergeschiktenVanTestChef = testChef.getOndergeschikten();
		assertEquals(2,ondergeschiktenVanTestChef.size());
		Werknemer testOndergeschikte1 = repository.findById(idVanTestOndergeschikte1()).get();
		assertTrue(ondergeschiktenVanTestChef.contains(testOndergeschikte1));
	}
	
	@Test
	public void repository_vindt_chef_van_een_ondergeschikte() {
		Werknemer testOndergeschikte = repository.findById(idVanTestOndergeschikte1()).get();
		Werknemer chefVanTestOndergeschikte = testOndergeschikte.getChef();
		Werknemer testChef = repository.findById(idVanTestChef()).get();
		assertEquals(testChef,chefVanTestOndergeschikte);
	}
	
	@Test
	public void repository_vindt_geen_chef_bij_de_grote_baas() {
		Werknemer testChef = repository.findById(idVanTestChef()).get();
		assertNull(testChef.getChef());
	}
	
	@Test
	public void repository_vindt_geen_ondergeschikten_bij_de_meest_ondergeschikte() {
		Werknemer testOndergeschikte = repository.findById(idVanTestOndergeschikte1()).get();
		assertEquals(0,testOndergeschikte.getOndergeschikten().size());
	}
	
	@Test
	public void repository_vindt_de_juiste_werknemers_met_een_opgegeven_jobtitel() {
		List<Werknemer> managerLijst = repository.findByJobtitelNaam("testjob");
		assertEquals(2,managerLijst.size());
		List<Werknemer> presidentLijst = repository.findByJobtitelNaam("President");
		assertEquals(1,presidentLijst.size());
	}
}