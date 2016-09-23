package com.ospreys.edu;

import java.util.LinkedList;
import java.util.ListIterator;


public class BlockSort_V6{
	
	private int permutationSize;
	
	private double approxRatio;
	
	private LinkedList<Block> originalList= new LinkedList<Block>(); // original input permutation list
	
	private LinkedList<LinkedList<Block>> listOfAllSubsequences= new LinkedList<LinkedList<Block>>();
	
	private String initialPerm;
	
	/**
	 * @return the initialPerm
	 */
	public String getInitialPerm() {
		return initialPerm;
	}

	/**
	 * @param initialPerm the initialPerm to set
	 */
	public void setInitialPerm(String initialPerm) {
		this.initialPerm = initialPerm;
	}

	/**
	 * @return the approxRatio
	 */
	public double getApproxRatio() {
		return approxRatio;
	}

	/**
	 * @param approxRatio the approxRatio to set
	 */
	public void setApproxRatio(double approxRatio) {
		this.approxRatio = approxRatio;
	}

	/**
	 * @return the permutationSize
	 */
	public int getPermutationSize() {
		return permutationSize;
	}

	/**
	 * @param permutation size the permutationSize to set
	 */
	public void setPermutationSize(int permutationSize) {
		this.permutationSize = permutationSize;
	}
	
	public LinkedList<Block> getList() {
		return originalList;
	}
	

	/**
	 * @param list the list to set
	 */
	public void setList(LinkedList<Block> list) {
		this.originalList = list;
	}

	/**
	 * @param value
	 * @param list
	 */
	public void add(int value){
		Block obj  = checkIncomingValueInList(value);
		if(null == obj){
			Block temp = new Block();
			//temp.setData(value);
			if (value != 1) {
				temp.setPre(value - 1);
			}
			temp.setSuc(value + 1);
			//temp.setValues(String.valueOf(value));
			originalList.add(temp);
		} else {
			
				obj.setSuc(value + 1);
				/*if(obj.getValues() == null){
					obj.setValues(obj.getValues());
				} else {
					String values = obj.getValues()+","+String.valueOf(value);
					obj.setValues(values);
				}*/
			
		}
	}
	
	/**
	 * @param value
	 * @return
	 */
	public Block checkIncomingValueInList(int value){
		if(null!=originalList && originalList.size()>0){
			int size = originalList.size();
			Block obj = originalList.get(size-1);
			if(obj.getSuc()==value){
				return obj;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String output = "";
		if(null!=originalList){
			for (int i=0;i<originalList.size();i++) {
				Block obj = originalList.get(i);
				output += "["+getValues(obj.getPre(),obj.getSuc())+"]";
			}
		}
		return output;
	}
	
	/**
	 * @param pre
	 * @param succ
	 * @return
	 */
	public String getValues(int pre, int succ){
		String values = "";
		for(int i=pre+1; i<succ; i++){
			values = values + String.valueOf(i) + " ";
		}
		return values.trim();
	}
	
	/**
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean merge(int i, int j) {
		Block obj = originalList.get(i);
		Block nextobj = originalList.get(j);
		//System.out.println("Merging elements ["+obj.getValues()+ "] and ["+nextobj.getValues()+"]");
		//if(obj.getSuc()==nextobj.getData()){
		if(nextobj.getPre()+1==obj.getSuc()){
			//String values = obj.getValues()+","+String.valueOf(nextobj.getValues());
			//obj.setValues(values);
			obj.setSuc(nextobj.getSuc());
			originalList.remove(nextobj);
			//System.out.println("Merge successful");
			return true;
		}
		//System.out.println("Cannot be merged");
		return false;
	}
	
	/**
	 * @param valueToBeMoved
	 * @return
	 *//*
	public int getSucPosition(int valueToBeMoved) {
		for (int i = 0; i < originalList.size(); i++) {
			Block obj = originalList.get(i);
			if(obj.getPre()==valueToBeMoved){
				return i;
			}
		}
		return -1;
	}*/
	
	/**
	 * @param valueToBeMoved
	 * @return
	 */
	public int getSucPosition(int valueToBeMoved) {
		Block obj = getBlockByValue(valueToBeMoved);
		return getBlockPosition(obj.getSuc());
	}
	
	/**
	 * @param valueToBeMoved
	 * @return
	 */
	public int getPrePosition(int valueToBeMoved) {
		Block obj = getBlockByValue(valueToBeMoved);
		return getBlockPosition(obj.getPre());
	}
	
	
	/**
	 * @param valueToBeMoved
	 * @return
	 *//*
	public int getPrePosition(int valueToBeMoved) {
		for (int i = 0; i < originalList.size(); i++) {
			Block obj = originalList.get(i);
			if(obj.getSuc()==valueToBeMoved){
				return i;
			}
		}
		return -1;
	}*/
	
	/**
	 * @param value
	 * @return
	 */
	public Block getBlockByValue(int value) {
		for (int i = 0; i < originalList.size(); i++) {
			Block obj = originalList.get(i);
			if(obj.getPre()< value && value<obj.getSuc()){
				return obj;
			}
		}
		return null;
	}
	
	/**
	 * This method will loop over all the elements in the list and 
	 * will return the position of the objVal in the list
	 * 
	 * Logic is to loop through the list and compare the objVal 
	 * passed with each element data property 
	 * 
	 * @param objVal
	 * @param list
	 * @return
	 */
	public int getBlockPosition(int objVal) {
		for (int i = 0; i < originalList.size(); i++) {
			Block obj = originalList.get(i);
			if(obj.getPre()<objVal && objVal<obj.getSuc()){
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method will move the value to the position after predecessor or before successor and merge the elements
	 * based on the boolean indicator passed to the method.
	 * 
	 * 1. if indicator value is true, this method will move the value to a position after its predecessor
	 * 2. if indicator value is false, this method will move the value to a position before its successor
	 * 3. if the predecessor/successor position is not found in the list then 
	 * this method will not alter anything and will print the message "move not allowed" on the console
	 * 
	 * 
	 * @param valueToBeMoved
	 * @param indicator
	 * @param list
	 */
	public boolean moveAndMerge(int valueToBeMoved, boolean indicator) {
		boolean movedSuccess = false;
		if(originalList.size()==2){
			//CustomObj first = list.get(0);
			Block temp = originalList.get(1);
			originalList.remove(1);
			originalList.add(0, temp);
			merge(0,1);
			movedSuccess = true;
		} else {
			Block obj = getBlockByValue(valueToBeMoved);
			int objPosition = getBlockPosition(valueToBeMoved);
			if(indicator){
				if(obj.getSuc()-obj.getPre() >2){
					valueToBeMoved = obj.getPre()+1;
				}
				int prePosition = getPrePosition(valueToBeMoved);
				if(prePosition==objPosition){
					//System.out.println("Move not allowed");
				} else if(prePosition!=-1 && objPosition !=-1){
					//int listSize = list.size();
					Block removedobj = originalList.remove(objPosition);
					if(objPosition < prePosition){
						originalList.add(prePosition, removedobj);
						movedSuccess = true;
						if(objPosition != 0){
							merge(objPosition-1,objPosition);
							
	
						}
						getCurrentPosAfterMoveAndMerge(valueToBeMoved);
						
					} else {
						originalList.add(prePosition+1, removedobj);
						movedSuccess = true;
						if(objPosition != originalList.size()-1){
							merge(objPosition,objPosition+1);
							
						}
						getCurrentPosAfterMoveAndMerge(valueToBeMoved);
					
					}
				}
			} else {
				int listSize = originalList.size();
				if(obj.getSuc()-obj.getPre() >2){
					valueToBeMoved = obj.getSuc()-1;
				}
				int sucPosition = getSucPosition(valueToBeMoved);
				if(sucPosition==objPosition){
					//System.out.println("Move not allowed");
				} else if(sucPosition!=-1 && objPosition !=-1){
					Block removedobj = originalList.remove(objPosition);
					if(objPosition < sucPosition){
						originalList.add(sucPosition-1, removedobj);
						movedSuccess = true;
						if(objPosition != 0){
							merge(objPosition-1,objPosition);
						}
						getCurrentPosAfterMoveAndMerge(valueToBeMoved);
					} else {
						originalList.add(sucPosition, removedobj);
						movedSuccess = true;
						if(objPosition != listSize-1){
							merge(objPosition,objPosition+1);
						}
						getCurrentPosAfterMoveAndMerge(valueToBeMoved);
					}
				}
			}
		}
		if(movedSuccess){
			//System.out.println("Move and Merge successful");
		}
		return movedSuccess;
	}

	/**
	 * @param valueToBeMoved
	 */
	private void getCurrentPosAfterMoveAndMerge(int valueToBeMoved) {
		int objCurrentPosition = getBlockPosition(valueToBeMoved);
		if(objCurrentPosition == 0){
			merge(objCurrentPosition,objCurrentPosition+1);
		} else if(objCurrentPosition == originalList.size()-1){
			merge(objCurrentPosition-1,objCurrentPosition);
		} else {
			boolean mergeSuccessful = merge(objCurrentPosition-1,objCurrentPosition);
			if(mergeSuccessful){
				merge(objCurrentPosition-1,objCurrentPosition);
			} else {
				merge(objCurrentPosition,objCurrentPosition+1);
			}
		}
	}
	
	/**
	 * @param sb
	 * @return
	 */
	public int threeApproximationMove(StringBuffer sb)  {
		int size = originalList.size();
		int reversalCount = findReversals();
		int inversionCount = findInversions();
		//sb.append(reversalCount).append("\t\t\t\t").append(inversionCount).append("\t\t\t"); // to generate output file
		int numberOfMoves=0;
		boolean moveOnebeforeTwo = moveAndMerge(1, false);
		if(moveOnebeforeTwo){
			numberOfMoves= numberOfMoves + 1;
		} 
		Block one = getBlockByValue(1);
		//System.out.println("Values : "+toString());
		//System.out.println("List Size after moving 1--> "+list.size());
		int j = one.getSuc();
		int blockPos = 0;
		while(blockPos != -1 && originalList.size()>1){
			blockPos = getBlockPosition(j);
			Block obj = getBlockByValue(j);
			if(null != obj){
				boolean success = moveAndMerge(j, true);	
				//System.out.println("Values : "+toString());
				//System.out.println("List Size after moving "+ i +" --> "+list.size());
				Block temp = getBlockByValue(j);
				j=temp.getSuc();
				if(success){
					numberOfMoves= numberOfMoves + 1;
				}
			} else {
				break;
			}
		}
		if(originalList.size()>1){
			//System.err.println("Block moves incomplete..!!! Attempting to continue");
			for (int i = 0; i < originalList.size(); i++) {
				Block block = originalList.get(i);
				moveAndMerge(block.getSuc(), false);
			}	
		}
		double denominator = Math.max(Math.max(reversalCount, inversionCount),size-1-inversionCount-reversalCount);
		//System.out.println("denominator --> "+denominator);
		//sb.append(numberOfMoves);
		//sb.append("\t\t");
		if(denominator>0){
			double ratio = numberOfMoves/denominator;
			approxRatio=ratio;
			//sb.append(ratio);
			if(approxRatio>=2){
				sb.append(initialPerm+"\t\t"+approxRatio);
				sb.append("\n");
			}
		}
		//System.out.println("total number of moves to sort the permutation --> "+numberOfMoves);
		return numberOfMoves;
	}
	
	
/*	*//**
	 * @return
	 *//*
	public int greedyAlgorithmMoveCount(StringBuffer sb){
		int size = originalList.size();
		int inversionCount = findInversions();
		int reversalCount = findReversals();
		sb.append(reversalCount).append("\t\t\t\t").append(inversionCount).append("\t\t\t");
		int noOfMoves = 0;
		int blockRedCount = 0;
		int listSize = getInputsize();
		for (int i = 1; i <= listSize; i++) {
			//System.out.println("Checking element : "+i);
			blockRedCount = blockReductionCount(i, true);
			if(blockRedCount>1){
				//System.out.println("moving element : "+i);
				boolean moveSuccessInd = moveAndMerge(i, true);
				if(moveSuccessInd){
					noOfMoves++;
				}
			} else{
				//System.out.println("Checking element : "+i+" , false");
				blockRedCount = blockReductionCount(i, false);
				if(blockRedCount>1){
					//System.out.println("moving element : "+i);
					boolean moveSuccessInd = moveAndMerge(i, false);
					if(moveSuccessInd){
						noOfMoves++;
					}
				}
			}
		}
		if(blockRedCount<2){
			for (int i = 1; i <= listSize; i++) {
				boolean moveSuccessInd = false;
				if(originalList.size()>1){
					int revReductionCount = reversalReductionCount(i);
					//System.out.println("Rev Reduction Count : "+revReductionCount);
					if(revReductionCount>0){
						//System.out.println("Moving Element : "+i);
						if(i==1){ //block 1
							moveSuccessInd = moveAndMerge(i, false);
						} else { //blocks containing element 2 and higher
							moveSuccessInd = moveAndMerge(i, true);
						}
						if(moveSuccessInd){
							noOfMoves++;
						}
					}
				}
			}
		}
		
		double denominator = Math.max(Math.max(reversalCount, inversionCount),size-1-inversionCount-reversalCount);
		//System.out.println("denominator --> "+denominator);
		sb.append("\t\t"+noOfMoves);
		if(denominator>0){
			double ratio = noOfMoves/denominator;
			sb.append("\t\t").append(ratio);
		}
		if(blockRedCount==1){
			StringBuffer sb = new StringBuffer();
			threeApproximationMove(sb);
			System.out.println(sb.toString());
		}
		return noOfMoves;
	}*/
	
	public int greedyAlgorithm_v1(StringBuffer sb){
		int size = originalList.size();
		int inversionCount = findInversions();
		//boolean greedyInd = false;
		int reversalCount = findReversals();
		//sb.append(reversalCount).append("\t\t\t\t").append(inversionCount).append("\t\t\t");
		int noOfMoves = 0;
		//System.out.println("Permutation is : "+toString());
		if(size>1){
			for (int i = 1; i <= getPermutationSize(); i++) {
				boolean moveSuccessInd = false;
				boolean movementInd = true;				
				if(getBlockReductionCount(i, movementInd) >1 || reversalReductionCount(i)>=1 || inversionReductionCount(i)>=1){
					//greedyInd = true;
					if(i==1){ //block 1
						moveSuccessInd = moveAndMerge(i, false);
					} else { //blocks containing element 2 and higher
						moveSuccessInd = moveAndMerge(i, movementInd);
						if(!moveSuccessInd){
							moveSuccessInd = moveAndMerge(i, !movementInd);
						}
					}
					
					/*if(i==1){ //block 1
						moveSuccessInd = moveAndMerge(i, false);
					} else { //blocks containing element 2 and higher
						moveSuccessInd = moveAndMerge(i, true);
						if(!moveSuccessInd){
							moveSuccessInd = moveAndMerge(i, false);
						}
					}*/
					if(moveSuccessInd){
						noOfMoves++;
					}
				} /*else {
					if(i==1){ //block 1
						moveSuccessInd = moveAndMerge(i, false);
					} else { //blocks containing element 2 and higher
						moveSuccessInd = moveAndMerge(i, true);
					}
					if(moveSuccessInd){
						noOfMoves++;
					}
				}*/
			}
			if(originalList.size()>1){
				/*boolean moveSuccessInd = false;
				for (int i = 1; i <= getPermutationSize(); i++) {
				if(i==1){ //block 1
						moveSuccessInd = moveAndMerge(i, false);
					} else { //blocks containing element 2 and higher
						moveSuccessInd = moveAndMerge(i, true);
					}
					if(moveSuccessInd){
						noOfMoves++;
					}
				}*/
				threeApproximationMove(sb);
			} else {
				double denominator = Math.max(Math.max(reversalCount, inversionCount),size-1-inversionCount-reversalCount);
				//System.out.println("denominator --> "+denominator);
				//sb.append("\t\t"+noOfMoves);
				if(denominator>0){
					double ratio = noOfMoves/denominator;
					approxRatio=ratio;
					if(approxRatio>=2){
						sb.append(initialPerm+"\t\t"+approxRatio);
						sb.append("\n");
					}
				}
			}
			
			/*double denominator = Math.max(Math.max(reversalCount, inversionCount),size-1-inversionCount-reversalCount);
			//System.out.println("denominator --> "+denominator);
			sb.append("\t\t"+noOfMoves);
			if(denominator>0){
				double ratio = noOfMoves/denominator;
				sb.append("\t\t").append(ratio);
			}*/
		}
		return noOfMoves;
	}

private int getBlockReductionCount(int i, boolean movementInd) {
	int blockRedCount =0;
	if(originalList.size()>1){
		if(i==1){ //block 1
			blockRedCount = calculateBlockReductionCount(i, false);
			movementInd = false;
		} else { //blocks containing element 2 and higher
			blockRedCount = calculateBlockReductionCount(i, true); 
			if(blockRedCount==0){
				blockRedCount = calculateBlockReductionCount(i, false); // if (2,true) fails, check calculateblockreducioncount for (2,false)
				movementInd = false;
			}
		}
		if(blockRedCount>1){
			//System.out.println("Block Reduction for element :"+i);
		}
	}
	return blockRedCount;
}
	
	/**
	 * @param value
	 * @param moveIndicator
	 * @return
	 */
	public int calculateBlockReductionCount(int value, boolean moveIndicator) {
		int blockRedCount = 1;
		if(moveIndicator){
			int prePosition = getPrePosition(value);
			if(prePosition == -1){
				blockRedCount = 0;
			} else {
				blockRedCount=checkAfterElementMovementAfterPre(prePosition,value)+
						checkAfterRemovingElement(value);
			}
		} else {
			int succPosition = getSucPosition(value);
			if(succPosition == -1){
				blockRedCount = 0;
			} else {
				blockRedCount=checkAfterElementMovementBeforeSuc(succPosition,value)+
						checkAfterRemovingElement(value);
			}
		}
		return blockRedCount;
	}
	
	/**
	 * @param value
	 * @return
	 */
	public int reversalReductionCount(int value) {
		int objPosition = getBlockPosition(value);
		if(originalList.size()>1){
			if(objPosition==-1){
				return 0;
			}
			if(objPosition==0){ // if value is in first position
				if(originalList.get(objPosition).getSuc()>originalList.get(objPosition+1).getSuc()){ // ex: value =4 ; 4 2 3 and 4 > 2
					//System.out.println("Reversal Reduction for element :"+value);
					return 1; //indicates reduction in reversal
				} else {
					return 0; //indicates no reduction
				}
			} else if(objPosition==originalList.size()-1){ // if value occupies last position
				if(originalList.get(objPosition-1).getSuc()>originalList.get(objPosition).getSuc()){
					//System.out.println("Reversal Reduction for element :"+value);
					return 1;
				} else {
					return 0;
				}
			} else { // if value is in the middle of pi
				if(originalList.get(objPosition-1).getSuc()<originalList.get(objPosition+1).getSuc()){
					//System.out.println("Reversal Reduction for element :"+value);
					return 1;
				} else {
					return 0;
				}
			}
		}
		return 0;
	}
	
	/**
	 * @param value
	 * @return
	 */
	public int inversionReductionCount(int value) {
		int objPosition = getBlockPosition(value);
		int succPosition = getSucPosition(value);
		int prePosition = getPrePosition(value);
		if(originalList.size()>1){
			if(objPosition==-1){
				return 0;
			}
			if((objPosition<prePosition && prePosition<succPosition) || 
					(prePosition<succPosition && succPosition<objPosition) || 
					(succPosition<objPosition && objPosition<prePosition)){
				//System.out.println("Inverse Reduction for element :"+value);
				return 1;
			} else {
				return 0;
			}
		}
		return 0;
	}
	
	/**
	 * @param value
	 * @return
	 *//*
	public int inversionReductionCount(int value) {
		int objPosition = getBlockPosition(value);
		int succPosition = getSucPosition(value);
		int prePosition = getPrePosition(value);
		if(originalList.size()>1){
			if(objPosition==-1){
				return 0;
			}
			if(value==1 && succPosition < objPosition){
				return 1;
			} else if(prePosition>objPosition && objPosition>succPosition){
				return 1;
			} else if(prePosition>objPosition && objPosition<succPosition && prePosition<succPosition){  // example: value= 4 and moving it after pre 3; 4 3 5
				return 1; //indicates reduction
			} else {
				return 0;
			}
		}
		return 0;
	}*/
	
	
	/**
	 * @param prePosition
	 * @param objValue
	 * @return
	 */
	public int checkAfterElementMovementAfterPre(int prePosition, int objValue){
		int count = 1;
		if((originalList.size() != prePosition+1) && originalList.get(prePosition+1).getPre()==objValue){
			count++;
		}
		return count;
	}
	
	/**
	 * @param sucPosition
	 * @param objValue
	 * @return
	 */
	public int checkAfterElementMovementBeforeSuc(int sucPosition, int objValue){
		int count = 1;
		if(sucPosition>0 && originalList.get(sucPosition-1).getSuc()==objValue){
			count++;
		} 
		return count;
	}
	
	/**
	 * @param value
	 * @return
	 */
	public int checkAfterRemovingElement(int value){
		int objPosition = getBlockPosition(value);
		if(objPosition==originalList.size()-1 || objPosition==0){
			return 0;
		}
		if(originalList.get(objPosition-1).getSuc()==originalList.get(objPosition+1).getPre()+1){
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @return
	 */
	private LinkedList<Block> getCopy() {
		LinkedList<Block> tempList = new LinkedList<Block>();
		for (int i = 0; i < originalList.size(); i++) {
			try {
				Block block = originalList.get(i);
				tempList.add((Block)block.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return tempList;
	}

	/**
	 * @return
	 */
	public int findReversals() {
		int reversalCount=0;
		for(int i = 0; i < originalList.size()-1; i++){
			Block block = originalList.get(i);
			Block nextBlock = originalList.get(i+1);
			int blockSucc = block.getSuc();
			int nextBlockSucc= nextBlock.getSuc();
			if(blockSucc>nextBlockSucc){
				reversalCount= reversalCount + 1;
			}
		}
		//System.out.println("Number of reversals --> "+reversalCount);
		return reversalCount;
	}
	
	/**
	 * @return
	 */
	public int findInversions() {

		int inversionCount = 0;
		int j = 1;
		int i = 0;
		while(i!=-1){
			i = getBlockPosition(j);
			Block block = getBlockByValue(j);
			if(null != block){
				int succBlockPos = getBlockPosition(block.getSuc());
				j = block.getSuc();
				if (i != -1 && succBlockPos != -1) {
					if (i > succBlockPos) {
						inversionCount = inversionCount + 1;
					}
				} else {
					break;
				}
			}
		}
		//System.out.println("Number of inversions --> " + inversionCount);
		return inversionCount;
	}
	
	/*public double approximationRatio()
	
	{
		int reversalCount = findReversals();
		int inversionCount = findInversions();
		int numberofMoves=threeApproximationMove(new StringBuffer());
		double ratio = numberofMoves/Math.max(Math.max(reversalCount, inversionCount), numberofMoves-reversalCount-inversionCount);
		
		return ratio;
		
	}*/
	
	/**
	 * 
	 */
	public void allSubsequencesList(){
		for (int i=0; i< originalList.size(); i++) {
			Block block = originalList.get(i);
			LinkedList<Block> subsequenceList = new LinkedList<Block>();
			subsequenceList.add(block);
			listOfAllSubsequences.add(subsequenceList);	
			for (ListIterator<LinkedList<Block>> iterator1 = listOfAllSubsequences.listIterator(); iterator1.hasNext();) {
				LinkedList<Block> subsequencesListObj = (LinkedList<Block>) iterator1.next(); //getting parent list element(child list) one by one
				if(subsequencesListObj.getLast().getSuc()<=block.getPre()+1){
					LinkedList<Block> newSubsequenceList = new LinkedList<Block>();
					newSubsequenceList.addAll(subsequencesListObj);
					iterator1.add(newSubsequenceList);
					subsequencesListObj.add(block);
				}
			}
		}
	}
	
	public void longestRun(){
		allSubsequencesList();
		int listSize=0;
		LinkedList<Block> result = null;
		for (ListIterator<LinkedList<Block>> iterator1 = listOfAllSubsequences.listIterator(); iterator1.hasNext();) {
			LinkedList<Block> subsequencesListObj = (LinkedList<Block>) iterator1.next();
			if(subsequencesListObj.size()>listSize){
				listSize = subsequencesListObj.size();
				result =subsequencesListObj;
			}
		}
		if(null!=result){   //printing longest run block
			String values = "";
			for (int i=0; i<result.size();i++) {
				Block longestrunBlock = result.get(i);
				for (int j=0; j<originalList.size();j++) {
					Block originalBlock = originalList.get(j);
					if(longestrunBlock.getPre() == originalBlock.getPre() && longestrunBlock.getSuc() ==originalBlock.getSuc()){
						originalBlock.setInLongestRun(true);
					}
				}
				values = values + getValues(longestrunBlock.getPre(), longestrunBlock.getSuc()) + " ";
				
			}
			//System.out.println("longestrun ----> "+values);
			//System.out.println("size : "+listSize);
		}
	}
	
	public void runMerging(){
		allSubsequencesList();
		LinkedList<Block> result = null;
		for (ListIterator<LinkedList<Block>> iterator1 = listOfAllSubsequences.listIterator(); iterator1.hasNext();) {
			LinkedList<Block> subsequencesListObj = (LinkedList<Block>) iterator1.next();
			if(subsequencesListObj.size()==3){
				result =subsequencesListObj;
				break;
			}
		}
		if(null!=result){   //printing longest run block
			String values = "";
			for (int i=0; i<result.size();i++) {
				Block longestrunBlock = result.get(i);
				for (int j=0; j<originalList.size();j++) {
					Block originalBlock = originalList.get(j);
					if(longestrunBlock.getPre() == originalBlock.getPre() && longestrunBlock.getSuc() ==originalBlock.getSuc()){
						originalBlock.setInLongestRun(true);
					}
				}
				values = values + getValues(longestrunBlock.getPre(), longestrunBlock.getSuc()) + " ";
				
			}
			//System.out.println("longestrun ----> "+values);
			//System.out.println("size : "+listSize);
		}
	}
	
	public void orderedPairFixing(){
		allSubsequencesList();
		LinkedList<Block> result = null;
		for (ListIterator<LinkedList<Block>> iterator1 = listOfAllSubsequences.listIterator(); iterator1.hasNext();) {
			LinkedList<Block> subsequencesListObj = (LinkedList<Block>) iterator1.next();
			if(subsequencesListObj.size()==2){
				result =subsequencesListObj;
				break;
			}
		}
		if(null!=result){   //printing longest run block
			String values = "";
			for (int i=0; i<result.size();i++) {
				Block longestrunBlock = result.get(i);
				for (int j=0; j<originalList.size();j++) {
					Block originalBlock = originalList.get(j);
					if(longestrunBlock.getPre() == originalBlock.getPre() && longestrunBlock.getSuc() ==originalBlock.getSuc()){
						originalBlock.setInLongestRun(true);
					}
				}
				values = values + getValues(longestrunBlock.getPre(), longestrunBlock.getSuc()) + " ";
				
			}
			//System.out.println("longestrun ----> "+values);
			//System.out.println("size : "+listSize);
		}
	}
	
	/**
	 * 
	 */
	public void approximationRatioForLongestRun(StringBuffer sb){
		int size = originalList.size();
		int reversalCount = findReversals();
		int inversionCount = findInversions();
		//sb.append(reversalCount).append("\t\t\t\t").append(inversionCount).append("\t\t\t");
		int numberOfMoves=0;
		LinkedList<Block> temp = getCopy();
		for (int i=0;i<temp.size();i++) {
			if(originalList.size()>1){
				Block block = temp.get(i);
				if(!block.isInLongestRun()){
					if(block.getPre() == 0){
						//System.out.println("Moving block with value "+(block.getPre()+1)+",false");
						if(moveAndMerge(block.getPre()+1, false)){
							numberOfMoves= numberOfMoves + 1;
						}
					} else {
						//System.out.println("Moving block with value "+(block.getPre()+1)+",true");
						if(moveAndMerge(block.getPre()+1, true)){
							numberOfMoves = numberOfMoves + 1;
						} else {
							//System.out.println("Moving block with value "+(block.getPre()+1)+",false");
							if(moveAndMerge(block.getPre()+1, false)){
								numberOfMoves = numberOfMoves + 1;
							}
						}
					}
					//System.out.println(toString());
				}
			}
		}
		//sb.append(numberOfMoves);
		//sb.append("\t\t");
		double denominator = Math.max(Math.max(reversalCount, inversionCount), size-1-reversalCount-inversionCount);
		//System.out.println("Total Reversal Count:" +reversalCount);
		//System.out.println("Total Inversion Count:" +inversionCount);
		//System.out.println("Total Number Of Moves:" +numberOfMoves);
		if(denominator>0){
			double ratio = numberOfMoves/denominator;
			approxRatio=ratio;
			//System.out.println("Approimation Ratio :"+ ratio);
			//sb.append(ratio);
			if(ratio>=2){
				//System.out.println(getInitialPerm());
				sb.append(getInitialPerm()+"\t\t"+approxRatio);
				sb.append("\n");
			}
		}
	
	}
	
	public void print(){
		for (int i=0; i< listOfAllSubsequences.size(); i++) {
			LinkedList<Block> childList = listOfAllSubsequences.get(i);
			String output = "";
			for (int j=0; j < childList.size(); j++) {
				Block block = (Block) childList.get(j);
				if(block.getSuc()-block.getPre()>2){
					output += "["+getValues(block.getPre(), block.getSuc()).trim()+"] ";
				} else {
					output += getValues(block.getPre(), block.getSuc());
				}
			}
			//System.out.println(output);
		}
	}
}
