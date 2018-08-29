package be.vdab.personeeladministratie.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import be.vdab.personeeladministratie.entities.Jobtitel;
import be.vdab.personeeladministratie.entities.Werknemer;
import be.vdab.personeeladministratie.repositories.WerknemerRepository;

@RunWith(MockitoJUnitRunner.class)
public class WerknemerServiceJUnitTest {
	private WerknemerServiceImpl service;
	@Mock
	private WerknemerRepository repository;
	private Werknemer president;
	
	@Before
	public void before() {
		service = new WerknemerServiceImpl(repository);
		president = new Werknemer("familienaam", "voornaam", "email", null, new Jobtitel("President"), BigDecimal.valueOf(2000),"test",
								  LocalDate.of(1980,01,01),66080100153L, new HashSet<>());
		List<Werknemer> lijstMetPresidenten = new ArrayList<>();
		lijstMetPresidenten.add(president);
		when(repository.findByJobtitelNaam("President")).thenReturn(lijstMetPresidenten);
	}
	
	@Test
	public void de_president_wordt_gevonden() {
			Werknemer werknemer = service.vindPresident();
			assertEquals("President",werknemer.getJobtitel().getNaam());
			verify(repository).findByJobtitelNaam("President");
	}
}
