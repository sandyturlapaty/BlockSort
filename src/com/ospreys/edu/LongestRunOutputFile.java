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
public class LongestRunOutputFile {

	public static void main(String[] args) throws IOException {

		List<String> list = new ArrayList<String>();
		ClassLoader classLoader = LongestRunOutputFile.class.getClassLoader();
		File file = new File(classLoader.getResource("input_permutations.txt").getFile());

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				list.add(line);
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int counter = 1;
		StringBuffer sb = new StringBuffer();
		sb.append("Sl\t\tInput\t\tReversal Count\tInversion Count\tNo of moves\tRatio\n");
		for (int i=0; i<list.size();i++) {
			BlockSort_V5 blockSort = new BlockSort_V5();
			String line = list.get(i);
			sb.append(counter+")\t\t"+line+"\t\t");
			System.out.println(counter+") Input : "+line);
			StringTokenizer st = new StringTokenizer(line, " ");
			int size=0;
			while (st.hasMoreElements()) {
				size = size+1;
				blockSort.add(Integer.valueOf((String)st.nextElement()));
			}
			//blockSort.setInputsize(size);
			blockSort.allSubsequencesList();
			blockSort.print();
			blockSort.longestRun();
			blockSort.approximationRatioForLongestRun(sb);
			//blockSort.threeApproximationMove(sb);
			//System.out.println(blockSort.findbestMove());
			sb.append("\n");
			System.out.println("\n");
			counter++;
		}
		System.out.println(sb.toString());
		writeToFile(sb);
	}

	/**
	 * @param sb
	 * @throws IOException
	 */
	private static void writeToFile(StringBuffer sb) throws IOException {
		File file = new File("longestrun_report.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(sb.toString());
		bw.close();
		
	}
			
}