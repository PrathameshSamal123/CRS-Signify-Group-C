/**
 * 
 */
package com.signify.bean;

import java.util.Date;

/**
 * @author Dell
 *
 */
public class SemesterRegistration {

	private int StudentId;
	private int semester;
	private Date dateOfregistration;
	
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public Date getDateOfregistration() {
		return dateOfregistration;
	}
	public void setDateOfregistration(Date dateOfregistration) {
		this.dateOfregistration = dateOfregistration;
	}
	
}
