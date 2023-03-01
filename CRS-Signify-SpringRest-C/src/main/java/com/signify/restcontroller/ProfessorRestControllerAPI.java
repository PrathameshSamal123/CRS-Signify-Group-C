/**
 * 
 */
package com.signify.restcontroller;

import java.sql.ResultSet;
import java.sql.SQLException;

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

import com.signify.bean.User;
import com.signify.exception.CourseDoesNotExistException;
import com.signify.exception.ProfessorDoesNotTeachCourseException;
import com.signify.exception.ValidCourseIdOrCourseCodeException;
import com.signify.service.ProfessorServiceOperations;
import com.signify.service.StudentServiceOperations;

/**
 * @author Acer
 *
 */

@RestController
public class ProfessorRestControllerAPI {
	
	@Autowired
	private ProfessorServiceOperations professorService;

	/**
	 * View courses assigned to the prof
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	   @RequestMapping(method=RequestMethod.POST,value= "/viewProfessorCourses")
	   @ResponseBody
	  public ResponseEntity<String> viewMyCourses(@RequestBody User user) throws SQLException {

		 int userId = user.getUserId();

		 ResultSet myCourses = professorService.viewMyCourses(user);

		 if(myCourses!=null)
		 {
			 String json = ResultSetToJsonConverter.convertResultSetToJson(myCourses);
			 if (professorService.viewMyCourses(user)!=null) {
		      return new ResponseEntity<String>(json,HttpStatus.OK);
		      
		    }
		 }

		     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No Course Available");
	
	  }
	   
	    
	   /**
	    * 
	    * @param user
	    * @param courseCode
	    * @return
	    * @throws SQLException
	    */
	   @RequestMapping(method=RequestMethod.POST, value ="/viewEnrolledStudents")
	   public ResponseEntity<String> viewEnrolledStudents(@RequestBody User user, @RequestParam String courseCode) throws SQLException
	   {
		  try {
			   	ResultSet enrolledStudents = professorService.viewEnrolledStudents(user, courseCode);
			    String json = ResultSetToJsonConverter.convertResultSetToJson(enrolledStudents);
			    return new ResponseEntity<String>(json,HttpStatus.OK);	   
		  }
			catch (CourseDoesNotExistException e) {
				return new ResponseEntity<String>("Course Not Found...Please Enter Valid Course Code",HttpStatus.UNAUTHORIZED);

	        }
		   
			catch (ProfessorDoesNotTeachCourseException e) {
				return new ResponseEntity<String>("Entered course is not assigned to you...",HttpStatus.UNAUTHORIZED);
	        }

	   }
	   
	   /**
	    * 
	    * @param user
	    * @param courseCode
	    * @param studentId
	    * @param grade
	    * @return
	    * @throws SQLException
	    */
	   @RequestMapping(method = RequestMethod.POST, value= "/addGrades")
	   @ResponseBody
	   public ResponseEntity<String> addGrades(@RequestBody User user, @RequestParam String courseCode, @RequestParam int studentId, @RequestParam  String grade) throws SQLException
	   {
		   try {
		   boolean gradesAdded = professorService.addGrades(user, courseCode, studentId, grade);
		   
		   if(gradesAdded==true)
		   {
			   return ResponseEntity.status(HttpStatus.OK).body("Grade added successfully!!");
		   }
		   }
		   catch (ValidCourseIdOrCourseCodeException e) {
			   return new ResponseEntity<String>("Enter valid studentId or courseCode",HttpStatus.UNAUTHORIZED);
	        }
		   
			catch (ProfessorDoesNotTeachCourseException e2) {
				
				return new ResponseEntity<String>("Entered course is not assigned to you...",HttpStatus.UNAUTHORIZED);
	        }
		   
		   
		   
		   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Grade can't be assigned");
	   }
	   
	   
	   
}
