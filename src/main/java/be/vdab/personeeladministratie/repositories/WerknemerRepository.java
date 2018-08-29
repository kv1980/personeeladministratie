package be.vdab.personeeladministratie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeeladministratie.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer,Long> {
	List<Werknemer> findByJobtitelNaam(String naam);
}
