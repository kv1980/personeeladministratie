package be.vdab.personeeladministratie.services;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeeladministratie.entities.Werknemer;
import be.vdab.personeeladministratie.exceptions.PresidentNietGevondenException;
import be.vdab.personeeladministratie.exceptions.WerknemerNietGevondenException;
import be.vdab.personeeladministratie.repositories.JobtitelRepository;
import be.vdab.personeeladministratie.repositories.WerknemerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class WerknemerServiceImpl implements WerknemerService {
	private final WerknemerRepository werknemerRepository;
	private final JobtitelRepository jobtitelRepository;

	WerknemerServiceImpl(WerknemerRepository werknemerRepository, JobtitelRepository jobtitelRepository) {
		this.werknemerRepository = werknemerRepository;
		this.jobtitelRepository = jobtitelRepository;
	}

	@Override
	public Werknemer vindPresident() {
		Set<Werknemer> lijstMetPresidenten = jobtitelRepository.findByNaam("President").get().getWerknemers();
		// alternatief was zoek de werknemer zonder chef
		if (lijstMetPresidenten.size() != 1) {
			String fout = lijstMetPresidenten.size() == 0 ? 
					"Er is geen president gevonden." : "Er zijn meerdere presidenten gevonden.";
			throw new PresidentNietGevondenException(fout);
		}
		return lijstMetPresidenten.stream().findFirst().get();
	}

	@Override
	public Werknemer vindWerknemer(long id) {
		Optional<Werknemer> optioneleWerknemer = werknemerRepository.findById(id);
		if (optioneleWerknemer.isPresent()) {
			return optioneleWerknemer.get();
		}
		throw new WerknemerNietGevondenException();
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void verhoogSalaris(Werknemer werknemer, BigDecimal bedrag) {
		werknemer.verhoogSalaris(bedrag);
		werknemerRepository.save(werknemer);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void wijzigRijksregisternr(Werknemer werknemer, Long nieuwRijksregisternr) {
		werknemer.wijzigRijksregisternr(nieuwRijksregisternr);
		werknemerRepository.save(werknemer);
	}
}