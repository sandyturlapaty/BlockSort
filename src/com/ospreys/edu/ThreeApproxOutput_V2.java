package com.ospreys.edu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ThreeApproxOutput_V2 {

    public static void Permute(int[] input, int startindex, List<String> inputList) {
        int size = input.length;
        if (size == startindex + 1) {
            //System.out.println(counter + "Permutation is");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < size; i++) {
                //System.out.print(input[i] + ",  ");
                sb.append(input[i]+" ");
            }
            inputList.add(sb.toString().trim());
            //System.out.println();
            //System.out.println("##########################");
        } else {
            for (int i = startindex; i < size; i++) {
            	int temp=input[i];
            	input[i]=input[startindex];
            	input[startindex]=temp;
            	Permute(input, startindex+1, inputList);
            }
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

        int counter = 1;
		StringBuffer sb = new StringBuffer();
		sb.append("Sl\t\tInput\t\tReversal Count\tInversion Count\t\tNo of moves\t\tRatio\n");
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
			blockSort.setInputsize(size);
			blockSort.threeApproximationMove(sb);
			sb.append("\n");
			//System.out.println("\n");
			counter++;
		}
		//System.out.println(sb.toString());
		writeToFile(sb,input.length);
    }
    
    /**
	 * @param sb
	 * @throws IOException
	 */
	private static void writeToFile(StringBuffer sb, int inputSize) throws IOException {
		File file = new File("three_approx_report_"+inputSize+".txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(sb.toString());
		bw.close();
		
	}

}