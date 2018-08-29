package be.vdab.personeeladministratie.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeeladministratie.entities.Werknemer;
import be.vdab.personeeladministratie.exceptions.PresidentNietGevondenException;
import be.vdab.personeeladministratie.repositories.WerknemerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class WerknemerServiceImpl implements WerknemerService {
	private final WerknemerRepository werknemerRepository;
	
	WerknemerServiceImpl(WerknemerRepository werknemerRepository) {
		this.werknemerRepository = werknemerRepository;
	}
	
	@Override
	public Werknemer vindPresident() {
		List<Werknemer> lijstMetPresidenten = werknemerRepository.findByJobtitelNaam("President");
		if (lijstMetPresidenten.size() != 1) {
			String fout = lijstMetPresidenten.size() == 0 ? "Er is geen president gevonden." : "Er zijn meerdere presidenten gevonden.";
			throw new PresidentNietGevondenException(fout);
		}
		return lijstMetPresidenten.get(0);
	}
		
	@Override
	public Optional<Werknemer> vindWerknemer(long id) {
		return werknemerRepository.findById(id);
	}

	@Override
	public void verhoogSalaris(BigDecimal bedrag) {
	}

	@Override
	public void wijzigRijksregisternr(Long nieuwRijksregisternr) {
	}

	@Override
	public List<Werknemer> vindWerknemersMetJobtitel(String naam) {
		return null;
	}
}
