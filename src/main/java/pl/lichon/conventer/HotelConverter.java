package pl.lichon.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.lichon.entity.Hotel;
import pl.lichon.repository.HotelRepository;

public class HotelConverter implements Converter<String, Hotel> {

	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel convert(String source) {
		long id = Long.parseLong(source);
		Hotel hotel = this.hotelRepository.findOne(id);
		return hotel;
	}
}
