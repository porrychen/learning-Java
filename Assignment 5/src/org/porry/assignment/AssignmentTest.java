/**
 * Test the Fifth Assignment
 */
package org.porry.assignment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author porrychen
 * AssignmentTest Class
 */
public class AssignmentTest {
	// start time
	private long beginTime;
	
	/**
	 * convert a millisecond to h:m:s
	 * @param millis: Milliseconds
	 */
	private String convertMillis(long millis) {
		long seconds = millis / 1000;
		return String.format("%d:%d:%d", seconds / (60 * 60), (seconds % (60 * 60)) / (60),
				(seconds % (60 * 60)) % 60);
	}
	
	/**
	 * generate a size for a Queue
	 * @param min
	 * @param max
	 * @return int
	 */
	private int generateSize(int min, int max) {
		return (int)((Math.random() * (max + 1 - min)) + min);
	}
	
	/**
	 * simulate 3-Queue
	 * @param caseNumber
	 * @throws InterruptedException
	 */
	private void simulate(int caseNumber) throws InterruptedException {
		System.out.println("\n-------------------- case " + caseNumber + " --------------------");
		// create a ThreadPool
		ExecutorService pool = Executors.newCachedThreadPool();
		
		// random the sizes of Queue A and Queue B
		int sizeA = generateSize(20, 38);
		int sizeB = generateSize(10, 27);
		
		// create three-Queue
		Queue queueC = new Queue(sizeA + sizeB, null);
		Queue queueA = new Queue(sizeA, queueC);
		Queue queueB = new Queue(sizeB, queueC);

		// create three Checkers and initialize their Queue
		Checker checkerC = new Checker(queueC, "Checker C");
		pool.execute(checkerC);
		
		Checker checkerA = new Checker(queueA ,"Checker A");
		pool.execute(checkerA);
		
		Checker checkerB = new Checker(queueB ,"Checker B");
		pool.execute(checkerB);
		
		beginTime = System.currentTimeMillis();
		long arriveTime = beginTime;
		// create all the passengers and add to Queue A or Queue B
		for(int i = 0; i < sizeA + sizeB; i++){
			int later = (int)(Math.random() * 600 + 1);
			arriveTime += later * 100;

			if (!queueA.isFull() && queueA.getRatio() < queueB.getRatio())
				queueA.add(new Passenger(i + 1, arriveTime));
			else 
				queueB.add(new Passenger(i + 1, arriveTime));
		}
		
		// shutdown ThreadPool
		pool.shutdown();
		while (true) {
			if (pool.isTerminated()) {
				System.out.println("\nTotal Time of case " + caseNumber + ": " + convertMillis(queueC.getFinishQueueTime() - beginTime));
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * run
	 */
	private void run() {
		// the size of the pool can be changed
		try {
			simulate(1);
			simulate(2);
			simulate(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("================================================");
		System.out.println("========= This is the fifth assignment =========");
		System.out.println("================================================");
		
		AssignmentTest test = new AssignmentTest();
		try {
			test.run();
		} finally {
			System.out.println("------------------------------------------------");
			System.out.println("                       Bye!");
		}
	}

}
