package pl.lichon.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Month {

	@Id
	private long id;
	
	private String name;
	
	private int dayDifference;

	public Month(long id, String name, int dayDifference) {
		super();
		this.id = id;
		this.name = name;
		this.dayDifference = dayDifference;
	}

	public Month() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDifference() {
		return dayDifference;
	}

	public void setDifference(int difference) {
		this.dayDifference = difference;
	}


}
