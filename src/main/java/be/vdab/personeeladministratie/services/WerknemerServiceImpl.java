package be.vdab.personeeladministratie.services;



import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeeladministratie.entities.Werknemer;
import be.vdab.personeeladministratie.repositories.WerknemerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class WerknemerServiceImpl implements WerknemerService {
	private final WerknemerRepository werknemerRepository;
	
	WerknemerServiceImpl(WerknemerRepository werknemerRepository) {
		this.werknemerRepository = werknemerRepository;
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
}
