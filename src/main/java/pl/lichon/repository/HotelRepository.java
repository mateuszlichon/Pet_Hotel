package pl.lichon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lichon.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

	Hotel findOneByEmail(String email);
}
