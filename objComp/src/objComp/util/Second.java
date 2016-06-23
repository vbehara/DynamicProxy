package objComp.util;


public class Second {

	private int IntValue;
	private double DoubleValue;

	// constructor
	public Second() {

	}

	// sets Int value
	public void setIntValue(int iIn) {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM setter setIntValue in Second class");
		this.IntValue = iIn;
	}

	// Sets Double value
	public void setDoubleValue(double dIn) {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM setter setDoubleValue in Second class");
		this.DoubleValue = dIn;
	}

	// gets Double value
	public double getDoubleValue() {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM getter getDoubleValue in Second class");
		return this.DoubleValue;
	}

	// gets Int value
	public int getIntValue() {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM getter getIntValue in Second class");
		return this.IntValue;
	}

	// overrides default hashcode
	@Override
	public int hashCode() {
		MyLogger.getInstance()
				.printToStdout(1,
						"DEBUG MESSAGE FROM overridden hashCode method of second class");
		return 41 * this.IntValue + new Double(DoubleValue).hashCode();
	}

	// overrides default equals
	@Override
	public boolean equals(Object obj) {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM overridden equals method of second class");
		if (this.hashCode() == obj.hashCode())
			return true;
		return false;
	}

	
	// Overriding toString method in Second class
	@Override
	public String toString() {
		return "\nOverriding toString in second Class";
	}

}
