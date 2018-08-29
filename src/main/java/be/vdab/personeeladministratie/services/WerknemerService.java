package be.vdab.personeeladministratie.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.personeeladministratie.entities.Werknemer;

public interface WerknemerService {
	Werknemer vindPresident();
	Optional<Werknemer> vindWerknemer(long id);
	void verhoogSalaris(Werknemer werknemr, BigDecimal bedrag);
	void wijzigRijksregisternr(Werknemer werknemer, Long nieuwRijksregisternr);
	List<Werknemer> vindWerknemersMetJobtitel(String naam);
}
