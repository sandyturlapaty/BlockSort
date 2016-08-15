/**
 * 
 */
package com.ospreys.edu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author sandyturlapaty
 *
 */
public class GreedyAlgorithmOutput_V1 {
	
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

	public static void main(String[] args) throws IOException {

		List<String> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        //System.out.println("Input array Length");
        int arraylength = in.nextInt();
        int[] input = new int[arraylength];
        for (int i = 0; i < arraylength; i++) {
            input[i] = in.nextInt();
        }
        Permute(input, 0, list);
		
		int counter = 1;
		StringBuffer sb = new StringBuffer();
		sb.append("Sl\t\tInput\t\tReversal Count\tInversion Count\tNo of moves\tRatio\n");
		for (int i=0; i<list.size();i++) {
			BlockSort_V5 blockSort = new BlockSort_V5();
			String line = list.get(i);
			sb.append(counter+")\t\t"+line+"\t\t");
			//System.out.println(counter+") Input : "+line);
			StringTokenizer st = new StringTokenizer(line, " ");
			int size=0;
			while (st.hasMoreElements()) {
				size = size+1;
				blockSort.add(Integer.valueOf((String)st.nextElement()));
			}
			blockSort.print();
			blockSort.setPermutationSize(size);
			blockSort.greedyAlgorithm_v1(sb);
			sb.append("\n");
			counter++;
		}
		//System.out.println(sb.toString());
		writeToFile(sb, input.length);
	}

	/**
	 * @param sb
	 * @throws IOException
	 */
	private static void writeToFile(StringBuffer sb, int inputSize) throws IOException {
		File file = new File("greedy_algorithm_report_"+inputSize+".txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(sb.toString());
		bw.close();
		
	}
			
}