package dto;

public class StudioDTO {
	private String name;
	private int winCount;
	
	public StudioDTO(String name,int winCount) {
		this.name = name;
		this.winCount = winCount;
	}
	
	public int getWinCount() {
		return winCount;
	}
	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StudioDTO [name=" + name + ", winCount=" + winCount + "]";
	}
	
}
