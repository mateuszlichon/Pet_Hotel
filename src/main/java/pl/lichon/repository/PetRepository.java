package pl.lichon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lichon.entity.Pet;
import pl.lichon.entity.User;

public interface PetRepository extends JpaRepository<Pet, Long> {
	
	List<Pet> findByUserId(long id);

}
