package objComp.util;

public class MyLogger {
	private int DEBUG_VAL;
	
	//private constructor
	private MyLogger()
	{

	}

	//Sets DEBUG_VAL value
	public void setDebug(int Val) {
		DEBUG_VAL = Val;
	}

	//gets DEBUG_VAL value
	public int getDebug() {
		return DEBUG_VAL;
	}

	private volatile static MyLogger uniqueInstance;

	//double check locking on unique instance
	public static MyLogger getInstance() {
		if (uniqueInstance == null) {
			synchronized (MyLogger.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new MyLogger();
					
				}
			}
		}
		return uniqueInstance;
	}
	
    //Overriding toString method in MyLogger class
	public String toString() {
		return "\nOverriding toString in MyLogger Class";
	}

	//To print debug message to display
	public void printToStdout(int level, String debugMessage) {
		if (DEBUG_VAL != 0 && level == DEBUG_VAL) {
			System.out.println(debugMessage);
		}
		else
		{
			//Do nothing
		}
	}


}
