package dto;

public class YearsDTO {
	
	private int year;
	private int winnerCount;
	
public YearsDTO(int year, int winnerCount) {
		
		this.year = year;
		this.winnerCount = winnerCount;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getWinnerCount() {
		return winnerCount;
	}
	public void setWinnerCount(int winnerCount) {
		this.winnerCount = winnerCount;
	}
	@Override
	public String toString() {
		return "MovieDTO [year=" + year + ", winnerCount=" + winnerCount + "]";
	}
	
	
	

}
