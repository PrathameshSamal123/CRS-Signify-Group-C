/**
 * 
 */
package com.signify.bean;
import java.util.*;

/**
 * @author Dell
 *
 */
public class GradeCard {
	
	private int studentId;
	private int semester;
	private float cpi;
	ArrayList<String> registeredCourses = new ArrayList<>();
	
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public float getCpi() {
		return cpi;
	}
	public void setCpi(float cpi) {
		this.cpi = cpi;
	}
	public ArrayList<String> getRegisteredCourses() {
		return registeredCourses;
	}
	public void setRegisteredCourses(ArrayList<String> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}
	

}
