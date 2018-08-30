package be.vdab.personeeladministratie.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.personeeladministratie.entities.Jobtitel;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertJobtitels.sql")
@Import(JobtitelServiceImpl.class)
public class JobtitelServiceImplIntegrationTest {
	@Autowired
	JobtitelServiceImpl service;
	
	@Test
	public void alle_jobtitels_worden_gevonden() {
		List<Jobtitel> lijstJobtitels = service.vindAlleJobtitels();
		assertEquals(4,lijstJobtitels.size());
		String[] woorden = {"President","Manager","Javadeveloper","testjob"};
		List<String> controlelijst = Arrays.asList(woorden);
		lijstJobtitels.stream()
					  .map(jobtitel -> jobtitel.getNaam())
					  .forEach(jobtitelnaam -> assertTrue(controlelijst.contains(jobtitelnaam)));
	}
}
