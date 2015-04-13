package Project;
import java.util.Random;

class Patients implements Runnable {
	   private Thread thread;
	   private String Patient;

	   
	    Patients(String name) {
	       Patient = name;
	       Random random = new Random();
	       System.out.println( Patient + " Arriving at Appointment." + "\n");
	       synchronized(this){
			   int ranInteger = random.nextInt(45);
			   try {
				for (int a = 0; a <=1; a++)   
				Thread.sleep(1000);
				System.out.println("Wait for " + Patient + " is " + ranInteger + " minutes.  " + "\n");
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
		   }
	   }
	   
	   public synchronized void run() {
		   	  Random examTime = new Random();
		   	  int total = 0;
		      synchronized(this)
		   	  {
		    	  	int exam = examTime.nextInt(35);
		            for(int i=0; i<1 ; i++){
		                total += i;
		                System.out.println("In Examination: " +  Patient + " Exam Time:  " + exam + " minutes." + "\n");
		            }
		            notify();
	      
	      
		      	}System.out.println("Appoitment Ended:  " +  Patient + " exiting." + "\n");
		      }
	public synchronized void start ()
	   {
	      System.out.println("Starting Examination:  " +  Patient + "\n");
	      if (thread == null)
	      {
	         thread = new Thread (this, Patient);
	         thread.start ();
	      }
	      
	   }
	
	
	
}