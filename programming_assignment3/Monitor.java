import java.util.PriorityQueue;
/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Monitor
{


	boolean[] chopsticks = null;
	boolean canTalk  = true;
	int numOfPhils = 0;
	PriorityQueue<Integer> eatQueue = new PriorityQueue<Integer>();

	/*
	 * ------------
	 * Data members
	 * ------------
	 */


	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers

		chopsticks = new boolean[piNumberOfPhilosophers];
		numOfPhils = piNumberOfPhilosophers;

		for(int i = 0; i < numOfPhils; i++){
			chopsticks[i] = true;
		}
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{

		while(true){

			boolean canEat = (chopsticks[piTID%numOfPhils]&&chopsticks[piTID-1]);

			if(canEat && eatQueue.isEmpty()){
				chopsticks[piTID%numOfPhils] = false;
				chopsticks[piTID-1] = false;
				break;
			}else if(!canEat && !eatQueue.contains(piTID)){
				eatQueue.add(piTID);
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(!canEat && eatQueue.contains(piTID)){
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(canEat && (eatQueue.contains(piTID))){
				chopsticks[piTID%numOfPhils] = false;
				chopsticks[piTID-1] = false;
				eatQueue.remove(piTID);
				break;
			}else if(canEat && !(eatQueue.contains(piTID))){
				eatQueue.add(piTID);
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}




	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		chopsticks[piTID%numOfPhils] = true;
		chopsticks[piTID-1] = true;
		notifyAll();

	}

	/**
	 * Only one philosopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{
		while(true){
			if(canTalk == true){
				canTalk = false;
				break;
			}else{
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		canTalk = true;
		notifyAll();
	}
}

// EOF
