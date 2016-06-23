package objComp.util;

public class First {
	private int IntValue;
	private String StringValue;

	//constructor
	public First() {

	}

	// sets Int value
	public void setIntValue(int iIn) {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM setter of setIntValue");
		this.IntValue = iIn;
	}

	// gets Int value
	public void setStringValue(String sIn) {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM setter of setStringValue");
		this.StringValue = sIn;
	}

	// gets String value
	public String getStringValue() {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM setter of setStringValue");
		return this.StringValue;
	}

	//overrides equals method
	@Override
	public boolean equals(Object obj) {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM overridden equals of First class");
		if (this.hashCode() == obj.hashCode())
			return true;
		return false;
	}

	// gets Int value
	public int getIntValue() {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM setter of getIntValue");
		return this.IntValue;
	}

	//overrides hashCode method
	@Override
	public int hashCode() {
		MyLogger.getInstance().printToStdout(1,
				"DEBUG MESSAGE FROM overriden hashCode in First class");
		return 41 * this.IntValue + this.StringValue.hashCode();
	}

	
}
