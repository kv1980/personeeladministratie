package be.vdab.personeeladministratie.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeeladministratie.entities.Jobtitel;
import be.vdab.personeeladministratie.repositories.JobtitelRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class JobtitelServiceImpl implements JobtitelService {
	private final JobtitelRepository jobtitelRepository;

	JobtitelServiceImpl(JobtitelRepository jobtitelRepository) {
		this.jobtitelRepository = jobtitelRepository;
	}

	@Override
	public List<Jobtitel> vindAlleJobtitels() {
		return jobtitelRepository.findAll();
	}
}