package com.ospreys.edu;

/**
 * 
 */

import java.util.Scanner;

/**
 * @author sandyturlapaty
 *
 */
public class BlockSortTest_V5 {

	public static void main(String[] args) {

		int[] numbers = readNumsFromCommandLine();
		BlockSort_V5 blockSort = new BlockSort_V5();
		blockSort.setInputsize(numbers.length);
		for (int i = 0; i < numbers.length; i++) {
			blockSort.add(numbers[i]);
		}
		
		StringBuffer output = new StringBuffer();
		//int valueToBeMoved = 3;
		//blockSort.moveAndMerge(valueToBeMoved, true);
		/*blockSort.setInputsize(numbers.length);
		blockSort.threeApproximationMove(output);
		//System.out.println("List Size After 3rd Move: "+blockSort.getList().size());
		blockSort.toString();*/
		
		blockSort.allSubsequencesList();
		blockSort.print();
		blockSort.longestRun();
		blockSort.approximationRatioForLongestRun(output);
		
		/*valueToBeMoved = 2;
		blockSort.moveAndMerge(valueToBeMoved, false);
		
		System.out.println("Values : "+blockSort.toString());
		
		
		valueToBeMoved = 4;
		blockSort.moveAndMerge(valueToBeMoved, true);
		
		System.out.println("Values : "+blockSort.toString());
		System.out.println("List Size After 4rd Move: "+blockSort.getList().size());*/

	}

	public static int[] readNumsFromCommandLine() {

		Scanner s = new Scanner(System.in);

		int count = s.nextInt();
		s.nextLine(); // throw away the newline.

		int[] numbers = new int[count];
		Scanner numScanner = new Scanner(s.nextLine());
		for (int i = 0; i < count; i++) {
			if (numScanner.hasNextInt()) {
				numbers[i] = numScanner.nextInt();
			} else {
				System.out.println("You didn't provide enough numbers");
				break;
			}
		}

		return numbers;
	}
}
