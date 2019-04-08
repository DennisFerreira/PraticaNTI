package entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Producer {
	@JsonIgnore
	private int id;
	private String name;
	
	public Producer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Producer() {
		
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
		return "Producer [id=" + id + ", name=" + name + "]";
	}
	
}
