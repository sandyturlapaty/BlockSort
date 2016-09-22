package com.ospreys.edu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ThreeApproxOutput_V3 {

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

    public static void main(String[] args) throws IOException{
    	List<String> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        //System.out.println("Input array Length");
        int arraylength = in.nextInt();
        int[] input = new int[arraylength];
        for (int i = 0; i < arraylength; i++) {
            input[i] = in.nextInt();
        }
        Permute(input, 0, list);
        
        System.out.println("Count "+list.size());

        int counter = 1;
		StringBuffer sb = new StringBuffer();
		sb.append("Sl\t\tInput\t\tReversal Count\tInversion Count\t\tNo of moves\t\tRatio\n");
		//sb.append("Sl\t\tInput\t\tRatio\n");
		List<BlockSort_V6> finalList = new ArrayList<>();
		for (int i=0; i<list.size();i++) {
			BlockSort_V6 blockSort = new BlockSort_V6();
			String line = list.get(i);
			//sb.append(counter+")\t\t"+line+"\t\t");
			//System.out.println(counter+") Input : "+line);
			StringTokenizer st = new StringTokenizer(line, " ");
			int size=0;
			while (st.hasMoreElements()) {
				size = size+1;
				blockSort.add(Integer.valueOf((String)st.nextElement()));
			}
			if(blockSort.getList().size()==arraylength){
				sb.append(counter+")\t\t"+line+"\t\t");
				blockSort.setPermutationSize(size);
				blockSort.threeApproximationMove(sb);
				sb.append("\n");
				finalList.add(blockSort);
			}
			//System.out.println("\n");
			counter++;
		}
		filerResults(finalList);
		//System.out.println(sb.toString());
		writeToFile(sb,input.length);
    }
    
    private static void filerResults(List<BlockSort_V6> finalList) {
    	Map<Double,List<BlockSort_V6>> map = new HashMap<>();
		for (Iterator iterator = finalList.iterator(); iterator.hasNext();) {
			BlockSort_V6 blockSort_V6 = (BlockSort_V6) iterator.next();
			if(map.containsKey(blockSort_V6.getApproxRatio())){
				List<BlockSort_V6> temp = map.get(blockSort_V6.getApproxRatio());
				temp.add(blockSort_V6);
			} else {
				List<BlockSort_V6> temp = new ArrayList<>();
				temp.add(blockSort_V6);
				map.put(blockSort_V6.getApproxRatio(), temp);
			}
		}
		
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			double key = (Double) iterator.next();
			System.out.println("Ratio : "+key+" , count : "+map.get(key).size());
			
		}
		
	}

	/**
	 * @param sb
	 * @throws IOException
	 */
	private static void writeToFile(StringBuffer sb, int inputSize) throws IOException {
		File file = new File("three_approx_report_"+inputSize+"_v1.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(sb.toString());
		bw.close();
		
	}

}