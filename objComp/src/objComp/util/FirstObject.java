package objComp.util;

public class FirstObject {

	private First first;

	// get FirstObject data
	public First getFirst() {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM getter getFirst in First Object class");
		return first;
	}

	// set FirstObject data
	public void setFirst(First first) {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM setter setFirst in First Object class");
		this.first = first;
	}

	// get count of first object
	public int getCount() {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM getter getCount in First Object class");
		return count;
	}

	// set count of first object
	public void setCount(int count) {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM setter setCount in First Object class");
		this.count = count;
	}

	private int count;

	// Overriding toString method in FirstObject class
	@Override
	public String toString() {
		return "\nOverriding toString in FirstObject Class";
	}
}
