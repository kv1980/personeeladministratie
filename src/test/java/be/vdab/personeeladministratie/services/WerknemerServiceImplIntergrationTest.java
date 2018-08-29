package be.vdab.personeeladministratie.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.personeeladministratie.entities.Werknemer;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertJobtitels.sql")
@Sql("/insertWerknemers.sql")
@Import(WerknemerServiceImpl.class)
public class WerknemerServiceImplIntergrationTest {
	@Autowired
	WerknemerServiceImpl service;
	
	@Test
	public void de_president_wordt_gevonden() {
		Werknemer president = service.vindPresident();
		assertEquals("President",president.getJobtitel().getNaam());
	}
}
