package be.vdab.personeeladministratie.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.personeeladministratie.entities.Werknemer;

public interface WerknemerService {
	Optional<Werknemer> vindWerknemer(long id);
	void verhoogSalaris(BigDecimal bedrag);
	void wijzigRijksregisternr(Long nieuwRijksregisternr);
	Werknemer vindPresident();
	List<Werknemer> vindWerknemersMetJobtitel(String naam);
}
