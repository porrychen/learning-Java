package org.porry.assignment;

/**
 * @author porrychen 
 * Checker Class
 */
public class Checker implements Runnable {
	private String name;
	private Queue queue;
	private Queue nextQueue = null;
	private long previousTime;

	public Checker(Queue queue, String name) {
		this.queue = queue;
		this.name = name;
	}

	@Override
	public void run() {

		while (!queue.isFinish()) {
			processingPassenger();
		}
	}

	/**
	 * one by one checkin a passenger
	 */
	private void processingPassenger() {
		Passenger passenger;
		// random a service time
		long serviceTime = useStrategy();
		try {
			// pop a passenger
			passenger = queue.take();
			queue.freshInData();
			nextQueue = queue.getNextQueue();

			// get previous time
			previousTime = queue.getPreviousQueueTime();
			if (nextQueue != null && previousTime == 0)
				previousTime = passenger.getArrivedTimeMillis();
			if (nextQueue == null && previousTime == 0)
				previousTime = passenger.getCheckInTimeMillis();

			// set the passenger's time
			queue.setPreviousQueueTime(serviceTime + previousTime);
			if (passenger.getCheckInTimeMillis() == 0)
				passenger.setCheckInTimeMillis(serviceTime + previousTime);
			else if (passenger.getFinishTimeMillis() == 0) {
				passenger.setFinishTimeMillis(serviceTime + previousTime);
				queue.setFinishQueueTime(serviceTime + previousTime);
			}

			try {
				Thread.sleep((int) (100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// done and move to next Queue
			if (nextQueue != null)
				nextQueue.add(passenger);

			System.out.println("Thread: id" + Thread.currentThread().getId() + " " + name + " --- Passenger "
					+ passenger.getNumber() + "\t--- done！\t");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * generate a random service time
	 * @return
	 */
	private long generateServiceTime() {
		int min = 1, max = 15;
		return (long) ((Math.random() * (max + 1 - min)) + min) * 60 * 1000;
	}

	/**
	 * use a strategy when queue is increasing steadily
	 * @return long
	 */
	private long useStrategy() {
		long time = generateServiceTime();
		if (queue.isIncrease()) {
			double min = nextQueue == null ? 0.01 : 0.1, max = (nextQueue == null ? 0.09 : 0.9);
			double randouNumb = (Math.random() * (max + 0.1 - min)) + min;
			time *= randouNumb;
		}

		return (long) time;
	}
}
