package pl.lichon.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lichon.entity.Hotel;
import pl.lichon.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Reservation findOneByHotelAndDate(Hotel hotel, Date date);
}
