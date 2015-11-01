package Threads;

public class MutexMediador {

	
	public synchronized void waitForCondition(){
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
   public synchronized void releaseCondition(){
		
		notify();
	}
}
