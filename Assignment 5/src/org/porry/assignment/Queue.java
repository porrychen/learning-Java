package org.porry.assignment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue {
	private int maxsize, addedCount = 0, doneCount = 0;
	private BlockingQueue<Passenger> queue = null;
	private Queue nextQueue = null;
	private long previousQueueTime, finishQueueTime;
	private double[] incData;

	public Queue(int maxSize, Queue nextQueue) {
		this.maxsize = maxSize;
		
		queue = new LinkedBlockingQueue<>(maxSize);
		this.nextQueue = nextQueue;
		
		// for check if increasing steadily 
		incData = new double[3];
	}

	/**
	 * Get a next Queue
	 * @return Queue
	 */
	public Queue getNextQueue() {
		return nextQueue;
	}

	/**
	 * get a ratio
	 * @return double
	 */
	public double getRatio() {
		return queue.size() * 1.0 / maxsize;
	}

	/**
	 * get previous time
	 * @return long
	 */
	public long getPreviousQueueTime() {
		return previousQueueTime;
	}

	public void setPreviousQueueTime(long previousQueueTime) {
		if (this.previousQueueTime < previousQueueTime) this.previousQueueTime = previousQueueTime;
	}

	/**
	 * get a done time
	 * @return long
	 */
	public long getFinishQueueTime() {
		return finishQueueTime;
	}

	public void setFinishQueueTime(long finishQueueTime) {
		if (this.finishQueueTime < finishQueueTime) this.finishQueueTime = finishQueueTime;
	}

	/**
	 * check if the Queue is full
	 * @return boolean
	 */
	public boolean isFull() {
		return queue.size() >= maxsize;
	}

	/**
	 * check if the Queue finishes
	 * @return boolean
	 */
	public boolean isFinish() {
		return doneCount == addedCount && addedCount != 0;
	}

	/**
	 * check if the Queue increases steadily
	 * @return boolean
	 */
	public boolean isIncrease() {
		for (int i = 0; i < incData.length - 1; i++) {
			if (incData[i] == 0 || incData[i] > incData[i + 1])
				return false;
		}
		return true;
	}

	private void addToIncData(double ratio) {
		for (int i = 0; i < incData.length; i++) {
			if (incData[i] == 0 || i == incData.length - 1) {
				incData[i] = ratio;
				return;
			}
			incData[i] = incData[i + 1];
		}
	}

	public void freshInData() {
		addToIncData(getRatio());
	}

	/**
	 * add a passenger in the Queue
	 * @param passenger
	 * @return
	 */
	public boolean add(Passenger passenger) {
		if (queue == null)
			return false;
		try {
			queue.put(passenger);
			addToIncData(getRatio());
			addedCount++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * take a passenger and remove from the Queue
	 * @return Passenger
	 * @throws InterruptedException
	 */
	public Passenger take() throws InterruptedException {
		doneCount += 1;

		return queue.take();
	}

	/**
	 * remove a passenger from the Queue
	 * @param passenger
	 * @return boolean
	 */
	public boolean remove(Passenger passenger) {
		if (queue == null)
			return false;

		return queue.remove(passenger);
	}
}
