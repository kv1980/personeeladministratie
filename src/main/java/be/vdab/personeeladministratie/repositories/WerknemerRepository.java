package be.vdab.personeeladministratie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeeladministratie.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer,Long> {
}
