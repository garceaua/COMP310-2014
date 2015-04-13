package Project;
import java.util.Random;

public  class Execution{
	
	public synchronized static void main(String args[])  {

		{
			
			int i = 10;
			while ( i != 0)
			{
				Random ID = new Random();
				int patientID = ID.nextInt(1000);
				Patients appoitment = new Patients("Patient ID -  " + patientID );
				appoitment.start();
			}
			
		}
	}
}