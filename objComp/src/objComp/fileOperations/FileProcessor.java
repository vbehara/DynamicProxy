package objComp.fileOperations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import objComp.util.MyLogger;

public class FileProcessor {
	private BufferedReader reader;

	// constructor takes input read file
	public FileProcessor(String inputFileName) {
		MyLogger.getInstance()
				.printToStdout(2,
						"DEBUG MESSAGE FROM Constructor of FileProcessor with input file name as param");

		try {
			this.reader = new BufferedReader(new FileReader(inputFileName));

		} catch (FileNotFoundException e) {
			MyLogger.getInstance()
					.printToStdout(0,
							"Exception due to input file not found - from catch block in Constructor of FileProcessor");
			System.err
					.println("Exception as inputfile not found from constructor of fileProcessor");
			System.exit(1);
		}
	}

	/***
	 * Reads data from the file
	 * 
	 * @return only single of file
	 */
	public String readFromFile() {
		MyLogger.getInstance().printToStdout(3,
				"DEBUG MESSAGE FROM readFromFile method of FileProcessor");
		String inputLineFromFile = "";
		try {
			inputLineFromFile = this.reader.readLine(); // buffer reader use
		} catch (FileNotFoundException e) {
			MyLogger.getInstance()
					.printToStdout(0,
							"Exception due to input file not found - readFromFile method of FileProcessor");
			System.err.println("Exception as file not found");
			System.exit(1);
		} catch (IOException e) {
			MyLogger.getInstance()
					.printToStdout(0,
							"Exception due to Stream Reader - readFromFile method of FileProcessor");
			System.err.println("Exception due Stream Reader I/O operation ");
			System.exit(1);
		}
		return inputLineFromFile;
	}

	// Overriding toString method in FileProcessor class
	@Override
	public String toString() {
		return "\nOverriding toString in FileProcessor Class";
	}

}
