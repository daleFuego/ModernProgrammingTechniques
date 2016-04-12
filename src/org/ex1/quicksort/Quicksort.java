package org.ex1.quicksort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Quicksort {
	private final static int numberOfElements = 1500; // = 10, 50, 100, 200, 300, 400, 500, 1000, 1500
	private final static int numberOfRepetitions = 20;
	private final static int dataRange = 1000;
	private static int values[] = new int[numberOfElements];
	private static HashMap<Long, Integer> elapsedTime = new HashMap<>();
	private static int numOfIterations = 1 ;

	private static void quicksort(int tablica[], int x, int y) {
		int i, j, v, temp;
		i = x;
		j = y;
		v = tablica[x];
		do {
			while (tablica[i] < v) {
				i++;
			}
			while (v < tablica[j]) {
				j--;
			}
			if (i <= j) {
				temp = tablica[i];
				tablica[i] = tablica[j];
				tablica[j] = temp;
				i++;
				j--;
			}
			numOfIterations++;
		} while (i <= j);
		if (x < j)
			quicksort(tablica, x, j);
		if (i < y)
			quicksort(tablica, i, y);
	}

	public static void main(String[] args) {
		
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		ArrayList<Long> list3 = new ArrayList<>();
		for (int j = 0; j < numberOfRepetitions; j++) {
			for (int i = 0; i < numberOfElements; i++) {
				values[i] = new Random().nextInt(dataRange);
			}
			long startTime = System.nanoTime();
			quicksort(values, 0, numberOfElements - 1);
			long stopTime = System.nanoTime();
			long startTime2 = System.nanoTime();
			quicksort(values, 0, numberOfElements - 1);
			long stopTime2 = System.nanoTime();
			long elapsedTime = (stopTime - startTime);
			long elapsedTime2 = (stopTime2 - startTime2);
			System.out.println("elapsedTime  " + elapsedTime);
			System.out.println("elapsedTime2 " + elapsedTime2);
			if (j != 0) {
				Quicksort.elapsedTime.put(elapsedTime, numOfIterations);
				list1.add(j);
				list2.add(numOfIterations);
				list3.add(elapsedTime);
			}
			numOfIterations = 0;
		}
		for (Integer numOfIterations : list2) {
			System.out.println(numOfIterations);
		}
		long avgTime = 0;
		for (Long elapsedTime : list3) {
			System.out.println(elapsedTime);
			avgTime+=elapsedTime;
		}
		
		System.out.println(avgTime/(list3.size()));
		Long maximumTime = new Long(Long.MIN_VALUE);
		Long minimumTime = new Long(Long.MAX_VALUE);
		Long mediumTime = new Long(0);
		Long mediumIterations = new Long(0);
		for (Long key : elapsedTime.keySet()) {
			mediumTime += key;
			mediumIterations += elapsedTime.get(key);
			if (key >= maximumTime) {
				maximumTime = key;
			}
			if (key <= minimumTime) {
				minimumTime = key;
			}
		}
		System.out.println("\nWorst case: " + maximumTime + " ns -> " + elapsedTime.get(maximumTime) + " iterations");
		System.out.println("\nBest case: " + minimumTime + " ns -> " + elapsedTime.get(minimumTime) + " iterations");
		System.out.println("\nAverage time = " + mediumTime / elapsedTime.size() + "ns\nAverage number of iterations = "
				+ mediumIterations / elapsedTime.size());
	}
}