import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Aircraft implements Runnable{
	private final long timeToDeparture = 3000L;
	private final int randomTimeToDeparture = 3000;
	private final String tookRunway = "enter the ";
	private final String releaseRunway = "been departur";
	private int ID;
	private BlockingQueue<Runway> runways = null;
	private Runway runway;
	
	public Aircraft(int ID, BlockingQueue<Runway> runways) {
		this.ID = ID;
		this.runways = runways;

		System.out.println(java.time.LocalTime.now() + " An aircraft with the ID of " + ID + " has been created.");
	}

	@Override
	public void run() {
		try {
			Random rand = new Random();
			runway = runways.take();
			System.out.println(java.time.LocalTime.now() + " Aircraft " + ID + " has been assigned to " + runway.getName() + ".");

			runway.inc(tookRunway);
			System.out.println(java.time.LocalTime.now() + " Aircraft " + ID + " will now " + tookRunway + runway.getName() + ".");

			Thread.sleep(rand.nextInt(randomTimeToDeparture, randomTimeToDeparture+randomTimeToDeparture));

		} catch (InterruptedException e) {
			e.printStackTrace();
		} 

		System.out.println(java.time.LocalTime.now() + " Aircraft " + ID + " has " + releaseRunway + "ed.");
		runways.add(runway);
		System.out.println(java.time.LocalTime.now() + runway.getName() + " is vacant now.");
		runway.inc(releaseRunway);
	}
}
