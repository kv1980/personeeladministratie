package be.vdab.personeeladministratie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeeladministratie.entities.Jobtitel;

public interface JobtitelRepository extends JpaRepository<Jobtitel,Long> {
}
