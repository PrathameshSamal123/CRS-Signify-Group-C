/**
 * 
 */
package com.signify.bean;

/**
 * @author Dell
 *
 */
public class Student extends User {

	private int studentId;
	private String branch;
	private int batch;
	private boolean isRegistered;

	public boolean isRegistered() {
		return isRegistered;
	}
	
	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
		
	
}
