package entities;

import java.util.ArrayList;



public class Movie {
	
	private int id;
	private String title;
	private int year;
	private ArrayList<Producer> producers;
	private ArrayList<Studio> studios;
	private boolean winner;

	

	public Movie(int id, String title, int year, ArrayList<Producer> producers, ArrayList<Studio> studios, boolean winner) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.producers = producers;
		this.studios = studios;
		this.winner = winner;
	}
	public Movie () {	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public ArrayList<Producer> getProducers() {
		return producers;
	}
	public void setProducers(ArrayList<Producer> producers) {
		this.producers = producers;
	}
	public ArrayList<Studio> getStudios() {
		return studios;
	}
	public void setStudios(ArrayList<Studio> studios) {
		this.studios = studios;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", producers=" + producers + ", studios="
				+ studios + ", winner=" + winner + "]";
	}

}
