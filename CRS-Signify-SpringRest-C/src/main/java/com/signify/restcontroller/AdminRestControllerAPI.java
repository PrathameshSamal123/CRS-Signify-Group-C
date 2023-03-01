/**
 * 
 */
package com.signify.restcontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.User;
import com.signify.service.AdminServiceOperations;
import com.signify.service.ProfessorServiceOperations;

/**
 * @author Acer
 *
 */

@RestController
public class AdminRestControllerAPI {

	@Autowired
	private AdminServiceOperations adminService;
	
	/**
	 *  Adding the Professor
	 * @param professor
	 * @return Response Entity String for status of adding professor
	 * @throws SQLException
	 */
	
	@RequestMapping(method=RequestMethod.POST,value= "/addProfessor")
	@ResponseBody
	public ResponseEntity<String> addProfessor(@RequestBody Professor professor) throws SQLException {
		
		boolean profAdded = adminService.addProfessor(professor);
		
		 if(profAdded==true)
		   {
			   return ResponseEntity.status(HttpStatus.OK).body("Professor added successfully!!");
		   }
		   
   
		   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Professor can't be added");
		
	}
	
	
	/**
	 * Adds admin 
	 * @param admin
	 * @return Response Entity tring ig Admin Added Successfully
	 * @throws SQLException
	 */
	@RequestMapping(method=RequestMethod.POST,value= "/addAdmin")
	public ResponseEntity<String> addAdmin(@RequestBody Admin admin) throws SQLException {
		
		boolean profAdded = adminService.addAdmin(admin);
		
		 if(profAdded==true)
		   {
			   return ResponseEntity.status(HttpStatus.OK).body("Admin added successfully!!");
		   }
		   
   
		   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Admin can't be added");		
	}
	
	
	
	/**
	 * Assign Course Professor
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(method=RequestMethod.POST,value= "/assignCourse")
	public ResponseEntity<String> assignCourse(@RequestBody Map<String,String> param ) throws SQLException {
		
		String courseID = param.get("courseID");
		int profID = Integer.parseInt(param.get("profID"));
		boolean isAdded = adminService.assignCourse(courseID, profID);
		
		if(isAdded==true)
		{
			 return ResponseEntity.status(HttpStatus.OK).body("Course Assigned to Professor");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course Could Not be assigned");		
	}
	
	
	/**
	 * Method to view all professors
	 * @return Professor List in JSON
	 * @throws SQLException
	 */
	 @RequestMapping(produces = MediaType.APPLICATION_JSON, method=RequestMethod.GET,value= "/viewProfessor")
	  public ResponseEntity<String> viewProfessor() throws SQLException {

		 ResultSet professor = adminService.viewProfessor();

		 
		 if(professor!=null)
		 {
			 String json = ResultSetToJsonConverter.convertResultSetToJson(professor);
		      return new ResponseEntity<String>(json,HttpStatus.OK);
		     
		 }

		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Professor Details Not Available");
	
	  }
	 
	 /**
	  * Add a new course 
	  * @param course
	  * @return status of adding course 
	  * @throws SQLException
	  */
	 @RequestMapping(method=RequestMethod.POST,value= "/addCourseAdmin")
		public ResponseEntity<String> addCourseAdmin(@RequestBody Course course ) throws SQLException {
			
			boolean isAdded = adminService.addCourse(course);
			
			if(isAdded==true)
			{
				 return ResponseEntity.status(HttpStatus.OK).body("Course Added");
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course Could Not be Added");		
		}
	 
	 
	 /**
	  * Delete a course 
	  * @param courseToDelete
	  * @return status of deletion
	  * @throws SQLException
	  */
	 @RequestMapping(method=RequestMethod.POST,value= "/deleteCourseAdmin")
		public ResponseEntity<String> deleteCourseAdmin(@RequestBody Map<String,String> param ) throws SQLException {
		 	String courseToDelete = param.get("courseToDelete");
			boolean isAdded = adminService.deleteCourse(courseToDelete);
			
			if(isAdded==true)
			{
				 return ResponseEntity.status(HttpStatus.OK).body("Course Deleted");
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course Could Not be Deleted");		
		}
	 
	 
	 /**
	  * Method to view courses 
	  * @return ALl courses 
	  * @throws SQLException
	  */
	 @RequestMapping(produces = MediaType.APPLICATION_JSON, method=RequestMethod.GET,value= "/viewCourseDetailsAdmin")
	  public ResponseEntity<String> viewCourseDetailsAdmin() throws SQLException {

		 ResultSet courses = adminService.viewCourseDetails();

		 if(courses!=null)
		 {
			 String json = ResultSetToJsonConverter.convertResultSetToJson(courses);
		      return new ResponseEntity<String>(json,HttpStatus.OK);
		     
		 }

		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course Details Not Available");
	
	  }
	 
	 /**
	  * Method to  approve students 
	  * @param param
	  * @return status of student approval 
	  * @throws SQLException
	  */
	 @RequestMapping(method=RequestMethod.PUT,value= "/approveStudentAdmin")
		public ResponseEntity<String> approveStudentAdmin(@RequestBody Map<String,String> param  ) throws SQLException {
		 	int userId = Integer.parseInt(param.get("userId"));
			int userChoice = Integer.parseInt(param.get("userChoice"));
			boolean isAdded = adminService.approveStudent(userChoice,userId);
			
			if(isAdded==true)
			{
				 return ResponseEntity.status(HttpStatus.OK).body("Student Approved");
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student Could Not be Approved");		
		}
	 
	 
	 /**
	  * Method to view unapproved Students
	  * @return unapproved students list 
	  * @throws SQLException
	  */
	 @RequestMapping(produces = MediaType.APPLICATION_JSON, method=RequestMethod.GET,value= "/viewUnapprovedStudentsAdmin")
	  public ResponseEntity<String> viewUnapprovedStudentsAdmin() throws SQLException {

		 ResultSet unapprovedStudents = adminService.displayUnapprovedStudent();

		 if(unapprovedStudents!=null)
		 {
			 String json = ResultSetToJsonConverter.convertResultSetToJson(unapprovedStudents);
	
		      return new ResponseEntity<String>(json,HttpStatus.OK);
		     
		 }

		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unapproved Student List not Available");
	
	  }
		
	
	
	
}
