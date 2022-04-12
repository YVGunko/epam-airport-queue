import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Airport {
	public static void main(String[] args) {
		// The size is fixed to <runwayCapacity> as there cannot be more than <runwayCapacity> aircraft
		// executing at the same time as it is limited to the number of runway objects.
		final int runwayCapacity = 3;
		// Fixed number of aircraft to be departure
		final int aircraftCapacity = 30;
		Random rand = new Random();
		
		// Create a blocking queue to store runways.
		BlockingQueue<Runway> runways = new ArrayBlockingQueue<Runway>(runwayCapacity);
		
		// Create a thread pool for the aircraft objects.
		ExecutorService apool  = Executors.newFixedThreadPool(runwayCapacity);
		
		// Generate <runwayCapacity> runway objects and add it to the runway queue.
		for (int i = 1; i <= runwayCapacity; i++) {
			runways.add(new Runway(i));
		}
		
		// Generate <aircraftCapacity> aircraft at random intervals.
		for(int i = 1; i <= aircraftCapacity; i++) {
			try {
				// Wait from 0 to 5 seconds to generate a new aircraft.
				Thread.sleep(rand.nextInt(5000));
				// Add generated aircraft into queue.
				apool.submit(new Aircraft(i, runways));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		apool.shutdown();
		
		try {
			if (apool.awaitTermination(100, TimeUnit.SECONDS)) {
				for (int i = 1; i <= runwayCapacity; i++) {
					runways.take().printReport();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
