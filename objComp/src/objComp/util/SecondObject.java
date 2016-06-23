package objComp.util;

public class SecondObject {

	private Second second;

	// constructor
	public SecondObject() {

	}

	// getter for second
	public Second getSecond() {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM getter getSecond in Second Object class");

		return second;
	}

	// setter for second
	public void setSecond(Second second) {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM setter setSecond in Second Object class");
		this.second = second;
	}

	// getter for count of second
	public int getCount() {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM getter getCount in Second Object class");

		return count;
	}

	// setter for count of second
	public void setCount(int count) {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM setter setCount in Second Object class");
		this.count = count;
	}

	private int count;

	// Overriding toString method in Second Object class
	@Override
	public String toString() {
		return "\nOverriding toString in Second Object Class";
	}

}
