package org.ex2.algorithms;

import java.util.Random;

public class Problem5 {

	private static final int NUMBER_OF_JOBS = 1;
	private static final int NUMBER_OF_PROCESSORS = 5;
	private static float listOfJobs[] = new float[NUMBER_OF_JOBS];
	private static float procJobs[] = new float[NUMBER_OF_PROCESSORS];

	private static Random randomValue = new Random(1);
	private static float execSumOfTime;
	private static float totalSumOfTime;

	public static void main(String[] args) {
		initializeJobs();
		quicksort(listOfJobs, 0, NUMBER_OF_JOBS - 1);

		try {
			runJobs();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void runJobs() throws InterruptedException {
		if (NUMBER_OF_PROCESSORS == 1) {
			System.out.println("Case: ONE PROCESSOR | Jobs: " + NUMBER_OF_JOBS);

			execSumOfTime = 0;
			for (int i = 0; i < NUMBER_OF_JOBS; i++) {
				execSumOfTime += listOfJobs[i];
			}

		} else if (NUMBER_OF_PROCESSORS > 0 && NUMBER_OF_JOBS == 1) {
			System.out.println("Case: MULTIPLE PROCESSORS: " + NUMBER_OF_PROCESSORS + " | Jobs: " + NUMBER_OF_JOBS);
			execSumOfTime += listOfJobs[0]*NUMBER_OF_PROCESSORS;
		} else if (NUMBER_OF_JOBS < NUMBER_OF_PROCESSORS) {
			System.out.println("Case: MULTIPLE PROCESSORS: " + NUMBER_OF_PROCESSORS + " | Jobs: " + NUMBER_OF_JOBS);
			for (int i = 0; i < NUMBER_OF_JOBS; i++) {
				execSumOfTime += listOfJobs[i];
			}
		}
		else if (NUMBER_OF_PROCESSORS % 2 == 0 && NUMBER_OF_JOBS % 2 == 0 && NUMBER_OF_JOBS > NUMBER_OF_PROCESSORS) {
			System.out.println("Case: MULTIPLE PROCESSORS: " + NUMBER_OF_PROCESSORS + " | Jobs: " + NUMBER_OF_JOBS);

			for (int i = 0; i < NUMBER_OF_PROCESSORS; i++) {
				System.out.println("Processor " + i);
				for (int j = 0; j < NUMBER_OF_JOBS / NUMBER_OF_PROCESSORS / 2; j++) {
					procJobs[i] += listOfJobs[NUMBER_OF_PROCESSORS * j + i]
							+ listOfJobs[NUMBER_OF_JOBS - 1 - NUMBER_OF_PROCESSORS * j - i];
					System.out.println("Job " + (NUMBER_OF_PROCESSORS * j + i) + " + "
							+ (NUMBER_OF_JOBS - 1 - NUMBER_OF_PROCESSORS * j - i));
				}
				execSumOfTime += procJobs[i];
			}
			for (int i = 0; i < ((NUMBER_OF_JOBS / 2) % NUMBER_OF_PROCESSORS); i++) {
				System.out.println("Processor " + i + "\nAdditional job "
						+ ((NUMBER_OF_JOBS / NUMBER_OF_PROCESSORS / 2) * NUMBER_OF_PROCESSORS + i) + " + "
						+ ((NUMBER_OF_JOBS - 1) - (NUMBER_OF_JOBS / NUMBER_OF_PROCESSORS / 2) * NUMBER_OF_PROCESSORS
								- i));
				execSumOfTime += listOfJobs[(NUMBER_OF_JOBS / NUMBER_OF_PROCESSORS / 2) * NUMBER_OF_PROCESSORS + i]
						+ listOfJobs[(NUMBER_OF_JOBS - 1)
								- (NUMBER_OF_JOBS / NUMBER_OF_PROCESSORS / 2) * NUMBER_OF_PROCESSORS - i];
			}
		} else if (NUMBER_OF_PROCESSORS % 2 == 0 && NUMBER_OF_JOBS % 2 != 0 && NUMBER_OF_JOBS > NUMBER_OF_PROCESSORS) {
			System.out.println("Case: MULTIPLE PROCESSORS: " + NUMBER_OF_PROCESSORS + " | Jobs: " + NUMBER_OF_JOBS);

			for (int i = 0; i < NUMBER_OF_PROCESSORS; i++) {
				System.out.println("Processor " + i);
				for (int j = 0; j < (NUMBER_OF_JOBS - 1) / NUMBER_OF_PROCESSORS / 2; j++) {
					procJobs[i] += listOfJobs[NUMBER_OF_PROCESSORS * j + i]
							+ listOfJobs[(NUMBER_OF_JOBS - 1) - 1 - NUMBER_OF_PROCESSORS * j - i];
					System.out.println("Job " + (NUMBER_OF_PROCESSORS * j + i) + " + "
							+ ((NUMBER_OF_JOBS - 1) - 1 - NUMBER_OF_PROCESSORS * j - i));
				}
				execSumOfTime += procJobs[i];
			}
			for (int i = 0; i < ((NUMBER_OF_JOBS / 2) % NUMBER_OF_PROCESSORS); i++) {
				System.out.println("Processor " + i + "\nAdditional job "
						+ ((NUMBER_OF_JOBS / NUMBER_OF_PROCESSORS / 2) * NUMBER_OF_PROCESSORS + i) + " + "
						+ ((NUMBER_OF_JOBS - 2) - (NUMBER_OF_JOBS / NUMBER_OF_PROCESSORS / 2) * NUMBER_OF_PROCESSORS
								- i));
				execSumOfTime += listOfJobs[(NUMBER_OF_JOBS / NUMBER_OF_PROCESSORS / 2) * NUMBER_OF_PROCESSORS + i]
						+ listOfJobs[(NUMBER_OF_JOBS - 2)
								- (NUMBER_OF_JOBS / NUMBER_OF_PROCESSORS / 2) * NUMBER_OF_PROCESSORS - i];
			}
			execSumOfTime += listOfJobs[NUMBER_OF_JOBS - 1];
			System.out.println("Processor " + (NUMBER_OF_PROCESSORS-1) + "\nAdditional job " + (NUMBER_OF_JOBS - 1));
		}

		System.out.println(">>> Execution time is " + execSumOfTime / NUMBER_OF_PROCESSORS
				+ ", total time is " + totalSumOfTime);
	}

	private static void initializeJobs() {
		for (int i = 0; i < NUMBER_OF_JOBS; i++) {
			listOfJobs[i] = randomValue.nextFloat();
			totalSumOfTime += listOfJobs[i];
		}
	}

	private static void quicksort(float[] listOfJobs2, int x, int y) {
		float v, temp;
		int i, j;
		i = x;
		j = y;
		v = listOfJobs2[x];
		do {
			while (listOfJobs2[i] < v) {
				i++;
			}
			while (v < listOfJobs2[j]) {
				j--;
			}
			if (i <= j) {
				temp = listOfJobs2[i];
				listOfJobs2[i] = listOfJobs2[j];
				listOfJobs2[j] = temp;
				i++;
				j--;
			}
		} while (i <= j);
		if (x < j)
			quicksort(listOfJobs2, x, j);
		if (i < y)
			quicksort(listOfJobs2, i, y);
	}

}
