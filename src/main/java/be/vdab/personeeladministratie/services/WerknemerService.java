package be.vdab.personeeladministratie.services;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.personeeladministratie.entities.Werknemer;

public interface WerknemerService {
	Werknemer vindPresident();
	Werknemer vindWerknemer(long id);
	void verhoogSalaris(Werknemer werknemer, BigDecimal bedrag);
	void wijzigRijksregisternr(Werknemer werknemer, Long nieuwRijksregisternr);
	List<Werknemer> vindWerknemersMetJobtitel(String naam);
}
