package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Studio {
	
	@JsonIgnore
	private int id;
	private String name;
	
	
	public Studio(int id, String name) {
		this.id = id;
		this.name = name;

	}
	public Studio() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Studio [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
