package com.nomad.entity;

public class Lend {
	private int bookNo;
	private int readerNo;
	private String lendTime;
	private String returnTime;
	private int quantity;
	/**
	 * @return the bookNo
	 */
	public int getBookNo() {
		return bookNo;
	}
	/**
	 * @param bookNo the bookNo to set
	 */
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	/**
	 * @return the readerNo
	 */
	public int getReaderNo() {
		return readerNo;
	}
	/**
	 * @param readerNo the readerNo to set
	 */
	public void setReaderNo(int readerNo) {
		this.readerNo = readerNo;
	}
	/**
	 * @return the lendTime
	 */
	public String getLendTime() {
		return lendTime;
	}
	/**
	 * @param lendTime the lendTime to set
	 */
	public void setLendTime(String lendTime) {
		this.lendTime = lendTime;
	}
	/**
	 * @return the returnTime
	 */
	public String getReturnTime() {
		return returnTime;
	}
	/**
	 * @param returnTime the returnTime to set
	 */
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}