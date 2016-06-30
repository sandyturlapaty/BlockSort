package com.ospreys.edu;



public class Block implements Cloneable{
	//int data;
	int pre;
	int suc;
	String values;
	boolean inLongestRun=false;
	
	/**
	 * @return the inLongestRun
	 */
	public boolean isInLongestRun() {
		return inLongestRun;
	}

	/**
	 * @param inLongestRun the inLongestRun to set
	 */
	public void setInLongestRun(boolean inLongestRun) {
		this.inLongestRun = inLongestRun;
	}

	public Block() {
		
	}
	
	/*// Block constructor
	public Block(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}*/

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getSuc() {
		return suc;
	}

	public void setSuc(int suc) {
		this.suc = suc;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	/**
	 * @return the moveblock
	 */
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	
}