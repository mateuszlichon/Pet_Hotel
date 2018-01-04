package pl.lichon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lichon.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
