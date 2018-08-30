package be.vdab.personeeladministratie.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.personeeladministratie.entities.Jobtitel;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertJobtitels.sql")
@Sql("/insertWerknemers.sql")
public class JobtitelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	JobtitelRepository repository;

	private long idVanTestjob() {
		return super.jdbcTemplate.queryForObject("select id from jobtitels where naam='testjob'", Long.class);
	}

	@Test
	public void repository_vindt_jobtitel_met_bestaande_naam() {
		Optional<Jobtitel> jobtitel = repository.findByNaam("testjob");
		assertTrue(jobtitel.isPresent());
		assertEquals(idVanTestjob(), jobtitel.get().getId());
	}

	@Test
	public void repository_vindt_jobtitel_met_onbestaande_naam_niet() {
		Optional<Jobtitel> jobtitel = repository.findByNaam("nietbestaandenaam");
		assertFalse(jobtitel.isPresent());
	}

	@Test
	public void repository_vindt_werknemers_met_dezelfde_jobtitel() {
		Jobtitel jobtitel = repository.findByNaam("testjob").get();
		int aantalWerknemersMetTestjob = super.jdbcTemplate.queryForObject("select count(*) from werknemers "
				+ "inner join jobtitels on werknemers.jobtitelid = jobtitels.id " + "where jobtitels.naam=?",
				Integer.class, "testjob");
		assertEquals(aantalWerknemersMetTestjob, jobtitel.getWerknemers().size());
		jobtitel.getWerknemers().stream().map(werknemer -> werknemer.getJobtitel().getNaam())
				.forEach(naam -> assertEquals("testjob", naam));
	}
}