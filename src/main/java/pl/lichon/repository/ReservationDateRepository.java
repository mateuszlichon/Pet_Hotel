package pl.lichon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lichon.entity.ReservationDate;

public interface ReservationDateRepository extends JpaRepository<ReservationDate, Long> {

	List<ReservationDate> findAllByHotelIdAndMonth(long hotelId, int month);

}
