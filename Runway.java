public class Runway {
	private final String tookRunway = "enter the ";
	private int tookCount = 0;
	private int departCount = 0;
	private int name;
	
	public Runway(int name) {
		this.name = name;
	}
	
	public void printReport() {
		System.out.println("Runway " + name + " has a total of "+ tookCount + " takes.");
		System.out.println("Runway " + name + " has a total of "+ departCount + " departures.");
	}

	public void inc(String status) {
		if (status.equals(tookRunway)) {
			tookCount++;
		} else {
			departCount++;
		}
	}

	public String getName() {
		return "Runway " + name;
	}

	public int getLandCount() {
		return tookCount;
	}

	public int getDepartCount() {
		return departCount;
	}

	public int getCount() {
		return departCount + tookCount;
	}
}