package org.porry.assignment;

public class Passenger {
	private int number;
	private long arrivedTimeMillis, checkinTimeMillis, finishTimeMillis;

	public Passenger(int number, long arrivedTimeMillis) {
		this.number = number;
		this.arrivedTimeMillis = arrivedTimeMillis;
	}

	/**
	 * get arrived time
	 * @return long
	 */
	public long getArrivedTimeMillis() {
		return arrivedTimeMillis;
	}

	public void setArrivedTimeMillis(long arrivedTimeMillis) {
		this.arrivedTimeMillis = arrivedTimeMillis;
	}

	/**
	 * get checkIn time
	 * @return long
	 */
	public long getCheckInTimeMillis() {
		return checkinTimeMillis;
	}

	public void setCheckInTimeMillis(long checkinTimeMillis) {
		this.checkinTimeMillis = checkinTimeMillis;
	}

	/**
	 * get finish time
	 * @return long
	 */
	public long getFinishTimeMillis() {
		return finishTimeMillis;
	}

	public void setFinishTimeMillis(long finishTimeMillis) {
		this.finishTimeMillis = finishTimeMillis;
	}

	/**
	 * get number
	 * @return int
	 */
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
