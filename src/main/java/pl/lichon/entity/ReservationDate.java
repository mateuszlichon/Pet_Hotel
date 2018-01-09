package pl.lichon.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ReservationDate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private int day;
	
	private int weekDay;
	
	private int month;
	
	private int year;
	
	@ManyToOne
	private Hotel hotel;
	
	private int placesLeft;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Pet> pet = new ArrayList<>();

	public ReservationDate() {
		super();
	}
	
	

	public ReservationDate(int day, int weekDay, int month, int year, Hotel hotel, int placesLeft) {
		this(day, weekDay, month, year, hotel);
		this.placesLeft = placesLeft;
	}

	public ReservationDate(int day, int weekDay, int month, int year, Hotel hotel) {
		this(day, weekDay, month, year);
		this.hotel = hotel;
	}


	public ReservationDate(int day, int weekDay, int month, int year) {
		super();
		this.day = day;
		this.weekDay = weekDay;
		this.month = month;
		this.year = year;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getPlacesLeft() {
		return placesLeft;
	}

	public void setPlacesLeft(int placesLeft) {
		this.placesLeft = placesLeft;
	}

	public List<Pet> getPet() {
		return pet;
	}

	public void setPet(List<Pet> pet) {
		this.pet = pet;
	}
	
	public void addPet(Pet pet) {
		this.getPet().add(pet);
	}
	
	
}