package com.ospreys.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PermuteArray_BackUp {

    public static int counter = 0;

    /**
	 * @param args
	 *            the command line arguments
	 */
	static void printArray(int[] a, List<String> inputList) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < a.length; i++) {
			//System.out.print(a[i] + " ");
			 sb.append(a[i]+" ");
		}
		inputList.add(sb.toString().trim());
		//System.out.println("");
	}

	static void Permute(int[] a, int k, List<String> inputList) {
		if (k == a.length)
			printArray(a,inputList);
		else
			for (int i = k; i < a.length; i++) {
				int temp = a[k];
				a[k] = a[i];
				a[i] = temp;
				Permute(a, k + 1,inputList);
				temp = a[k];
				a[k] = a[i];
				a[i] = temp;
			}
	}

    public static void main(String[] args) {
    	List<String> inputList = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Input array Length");
        int arraylength = in.nextInt();
        int[] input = new int[arraylength];
        for (int i = 0; i < arraylength; i++) {
            input[i] = in.nextInt();
        }
        counter = 0;
        Permute(input, 0, inputList);      
    }

}