package pl.lichon.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.lichon.entity.Pet;
import pl.lichon.repository.PetRepository;

public class PetConverter implements Converter<String, Pet> {

	@Autowired
	private PetRepository petRepository;
	
	public Pet convert(String source) {
		long id = Long.parseLong(source);
		Pet pet = this.petRepository.findOne(id);
		return pet;
	}

}
