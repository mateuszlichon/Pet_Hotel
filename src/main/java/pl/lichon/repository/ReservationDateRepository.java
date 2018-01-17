package pl.lichon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lichon.entity.ReservationDate;

public interface ReservationDateRepository extends JpaRepository<ReservationDate, Long> {

	List<ReservationDate> findAllByHotelIdAndMonthIdOrderById(long hotelId, long monthId);

	List<ReservationDate> findAllByPetId(long petId);

	List<ReservationDate> findAllByHotelIdOrderById(long hotelId);

	List<ReservationDate> findAllByHotelIdAndMonthId(long id, long id2);

}
