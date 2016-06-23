package objComp.driver;

import objComp.fileOperations.FileProcessor;
import objComp.util.MyLogger;
import objComp.util.PopulateObjects;

public class Driver {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		if (args.length != 3) {
			System.err
					.println("Need to provide four arguments - inputfileName,No of Iterations,debugValue");
			System.exit(1);
		}

		int debugValue = Integer.parseInt(args[2]);
		if ((debugValue < 0) || (debugValue > 3)) {
			System.err.println("Need to provide proper debug value");
			System.exit(1);
		}
		int N = Integer.parseInt(args[1]);
		if (N <= 0) {
			System.err.println("Need to provide proper Iteration value");
		}
		String inputFileName = args[0];

		MyLogger.getInstance().setDebug(Integer.parseInt(args[2]));

		PopulateObjects objects = null;
		FileProcessor fProcessor= null;
		for (int i = 0; i < N; i++) {

			fProcessor = new FileProcessor(inputFileName);

			objects = new PopulateObjects(fProcessor);
			objects.deserObjects();

		}
		System.out.println(objects.getObjectCount());
		long finishTime = System.currentTimeMillis();

		long total_time = (finishTime - startTime) / N;

		System.out.println("total time : " + total_time);

	}
}
