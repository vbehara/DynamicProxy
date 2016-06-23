package objComp.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import objComp.fileOperations.FileProcessor;

public class PopulateObjects {
	private FileProcessor fProcessor;
	private Map<Integer, FirstObject> first;
	private Map<Integer, SecondObject> second;
	private Map<String, Class<?>> primitiveMap;
	
	private LinkedList<Integer> s= new LinkedList<Integer>();
	// constructor
	public PopulateObjects(FileProcessor fProcessor) {
		MyLogger.getInstance()
				.printToStdout(
						2,
						"DEBUG MESSAGE FROM Constructor of Populate Objects with file processor as param");
		this.first = new Hashtable<Integer, FirstObject>();
		this.second = new Hashtable<Integer, SecondObject>();

		this.fProcessor = fProcessor;
	}

	// Initialize primitive map data structure
	public void primitiveMap() {
		MyLogger.getInstance()
				.printToStdout(3,
						"DEBUG MESSAGE FROM primitiveMap method of PopulateObjects class");
		this.primitiveMap = new Hashtable<String, Class<?>>();
		this.primitiveMap.put("int", Integer.TYPE);
		this.primitiveMap.put("Integer", Integer.TYPE);
		this.primitiveMap.put("Double", Double.class);
		this.primitiveMap.put("String", String.class);
		this.primitiveMap.put("double", Double.TYPE);
	}

	public void deserObjects() {
		MyLogger.getInstance()
				.printToStdout(3,
						"DEBUG MESSAGE FROM deserObjects method of PopulateObjects class");
		primitiveMap();
		String inputLine = null;
		Class<?> cls = null;
		Object obj = null;
		String clsName = null;

		String[] inputValues = null;
		String[] splitInputValues = null;
		String[] values = null;
		int fieldLength = 0;
		try {
			while ((inputLine = this.fProcessor.readFromFile()) != null) {

				if (!inputLine.trim().isEmpty()) {

					if (inputLine.contains("fqn:")) {

						clsName = inputLine.substring(4, inputLine.length());// generalize
						cls = Class.forName(clsName);
						obj = cls.newInstance();
						fieldLength = cls.getDeclaredFields().length;

					} else {
						inputValues = inputLine.split(", ");
						if (inputValues != null) {
							values = new String[inputValues.length];
							for (int i = 0; i < inputValues.length; i++) {
								splitInputValues = inputValues[i].split("=");
								if (splitInputValues != null) {
									values[i] = splitInputValues[1];
								}
							}
							handleDataTypes(values, obj, cls);
						}
						fieldLength--;
						if (fieldLength == 0) {
							saveToDataStructures(obj);
						}
					}
				}

			}

		} catch (InstantiationException e) {
			MyLogger.getInstance()
					.printToStdout(
							0,
							"DEBUG MESSAGE FROM catch block due to problem creating instance in deserObjects method of class");

			System.err
					.println("Class Instance Cannot be created with provided class Name");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			MyLogger.getInstance()
					.printToStdout(
							0,
							"DEBUG MESSAGE FROM catch block due to problem finding class in deserObjects method of class");

			System.err
					.println("Invalid Class Name - Class Not found Exception from deserObjects of PopulateObjects");
			System.exit(1);
		} catch (IllegalAccessException e) {
			MyLogger.getInstance()
					.printToStdout(
							0,
							"DEBUG MESSAGE FROM catch block due to problem accessing class in deserObjects method of class");

			System.err.println("Executing method doesnt have access to class");
			System.exit(1);
		}

	}

	/***
	 * Method which handles data types of different types
	 * 
	 * @param values
	 * @param object
	 *            type
	 * @param class
	 * @return
	 */
	public Object handleDataTypes(String[] values, Object obj, Class<?> cls) {
		MyLogger.getInstance()
				.printToStdout(3,
						"DEBUG MESSAGE FROM handleDataTypes method of populateObjects class");

		try {
			if (values != null) {
				String methodName = "set";
				int valueLength = values.length;
				if (this.primitiveMap.containsKey(values[valueLength
						- valueLength])) {
					Class<?>[] signature = new Class<?>[1];
					Object[] params = new Object[1];

					methodName = methodName + values[valueLength - 2];
					signature[0] = this.primitiveMap.get(values[valueLength
							- valueLength]);

					Method meth = cls.getMethod(methodName, signature);

					if (values[valueLength - valueLength].contains("int"))
						params[0] = Integer.parseInt(values[valueLength - 1]);

					else if (values[valueLength - valueLength]
							.contains("String"))
						params[0] = values[valueLength - 1];
					else if (values[valueLength - valueLength]
							.contains("double"))
						params[0] = Double.parseDouble(values[valueLength - 1]);
					else if (values[valueLength - valueLength]
							.contains("Double"))
						params[0] = Double.parseDouble(values[valueLength - 1]);
					else if (values[valueLength - valueLength]
							.contains("Integer"))
						params[0] = Integer.parseInt(values[valueLength - 1]);

					meth.invoke(obj, params);
				}
			}
		} catch (InvocationTargetException e) {
			MyLogger.getInstance()
					.printToStdout(
							0,
							"DEBUG MESSAGE FROM catch block due to Method not available from handleDataTypes method of populateObjects class");

			System.err
					.println("Invalid method - Method not available from handleDataTypes method of populateObjects class");
			System.exit(1);
		} catch (NoSuchMethodException e) {
			MyLogger.getInstance()
					.printToStdout(
							0,
							"DEBUG MESSAGE FROM catch block due to Invalid method Name from handleDataTypes method of populateObjects class");
			System.err
					.println("Invalid method Name from handleDataTypes method");
			System.exit(1);
		} catch (IllegalArgumentException e) {
			MyLogger.getInstance()
					.printToStdout(
							0,
							"DEBUG MESSAGE FROM catch block due to Invalid method Name from handleDataTypes method of populateObjects class");

			System.err.println("Invalid arguments passed to invoke method");
			System.exit(1);
		} catch (IllegalAccessException e) {
			MyLogger.getInstance()
					.printToStdout(
							0,
							"DEBUG MESSAGE FROM catch block due to Illegal class operation from handleDataTypes method of populateObjects class");

			System.err.println("Executing method doesnt have access to class");
			System.exit(1);
		}
		return obj;

	}

	/***
	 * Method which saves the formed reflection object to data structure
	 * 
	 * @param result
	 */
	public void saveToDataStructures(Object result) {
		MyLogger.getInstance()
				.printToStdout(3,
						"DEBUG MESSAGE FROM SaveToDataStructures method of populateObjects class");
		int count = 0;

		if (result instanceof First) {
			First obj = (First) result;

			int firstInput = obj.hashCode();
			FirstObject firstObj = null;
			if (!first.containsKey(firstInput)) {
				firstObj = new FirstObject();
				firstObj.setFirst(obj);
				firstObj.setCount(1);
				first.put(firstInput, firstObj);

			} else {
				firstObj = first.get(firstInput);
				count = firstObj.getCount();
				firstObj.setCount(count + 1);
			}

		} else {

			Second obj = (Second) result;
			int secondInput = obj.hashCode();

			SecondObject secondObj = null;

			if (!second.containsKey(secondInput)) {
				secondObj = new SecondObject();
				secondObj.setSecond(obj);
				secondObj.setCount(1);
				second.put(secondInput, secondObj);
			} else {
				secondObj = second.get(secondInput);
				count = secondObj.getCount();
				secondObj.setCount(count + 1);
			}
		}

	}

	/***
	 * Method which counts the occurrence of first and second objects
	 * 
	 * @return String contains count of duplicate and non duplicate objects
	 */
	public String getObjectCount() {
		MyLogger.getInstance()
				.printToStdout(3,
						"DEBUG MESSAGE FROM getObjectCount method of populateObjects class");

		if (this.first != null && this.second != null) {
			int uniqueCountFirst = this.first.size();
			int uniqueCountSecond = this.second.size();

			int duplicateCountFirst = 0;
			int duplicateCountSecond = 0;

			Collection<FirstObject> valuesFirst = this.first.values();
			for (FirstObject obj : valuesFirst) {
				duplicateCountFirst = duplicateCountFirst + obj.getCount();
			}

			Collection<SecondObject> valuesSecond = this.second.values();
			for (SecondObject obj : valuesSecond) {
				duplicateCountSecond = duplicateCountSecond + obj.getCount();
			}

			String output = "Number of non-duplidate First objects: "
					+ uniqueCountFirst + "\n"
					+ "Total Number of First objects: " + duplicateCountFirst
					+ "\n" + "Number of non-duplidate Second objects: "
					+ uniqueCountSecond + "\n"
					+ "Total Number of Second objects: " + duplicateCountSecond;

			return output;
		}
		return null;
	}

	// Overriding toString method in PopulateObjects class
	@Override
	public String toString() {
		return "\nOverriding toString in PopulateObjects Class";
	}

}
