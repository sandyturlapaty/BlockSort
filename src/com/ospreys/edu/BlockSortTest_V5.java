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
		for (int i = 0; i < numbers.length; i++) {
			blockSort.add(numbers[i]);
		}
		
		StringBuffer output = new StringBuffer();
		//int valueToBeMoved = 3;
		//blockSort.moveAndMerge(valueToBeMoved, true);
		blockSort.setPermutationSize(numbers.length);
		//blockSort.threeApproximationMove(output);
		//System.out.println("List Size After 3rd Move: "+blockSort.getList().size());
		
		
		/*blockSort.allSubsequencesList();
		blockSort.print();
		blockSort.longestRun();
		blockSort.approximationRatioForLongestRun(output);*/
		
		/*for (int i = 0; i < numbers.length; i++) {
			System.out.println(blockSort.blockReductionCount(i+1, true));
		}*/
		testRunMerge(output,blockSort);
		//testLongestRun(output, blockSort);
		System.out.println(output.toString());
		
		/*valueToBeMoved = 2;
		blockSort.moveAndMerge(valueToBeMoved, false);
		
		System.out.println("Values : "+blockSort.toString());
		
		
		valueToBeMoved = 4;
		blockSort.moveAndMerge(valueToBeMoved, true);
		
		System.out.println("Values : "+blockSort.toString());
		System.out.println("List Size After 4rd Move: "+blockSort.getList().size());*/

	}
	
	private static void testRunMerge(StringBuffer output, BlockSort_V5 blockSort){
		blockSort.print();
		blockSort.runMerging();
		blockSort.approximationRatioForLongestRun(output);
	}
	
	private static void testOrderedPairFixing(StringBuffer output, BlockSort_V5 blockSort){
		blockSort.print();
		blockSort.orderedPairFixing();
		blockSort.approximationRatioForLongestRun(output);
	}
	
	private static void testLongestRun(StringBuffer output, BlockSort_V5 blockSort){
		blockSort.print();
		blockSort.longestRun();
		blockSort.approximationRatioForLongestRun(output);
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
