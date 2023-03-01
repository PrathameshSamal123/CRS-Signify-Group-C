/**
 * 
 */
package com.signify.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.dao.DBTablePrinter;
import com.signify.service.StudentServiceOperations;
import com.signify.service.UserServiceOperations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.signify.restcontroller.ResultSetToJsonConverter;
/**
 * @author Acer
 *
 */
@RestController
public class StudentRestControllerAPI {
	
	@Autowired
	private StudentServiceOperations studentService;
	
	/**
	 * Method to View Student Grade Card 
	 * @param user
	 * @param semester
	 * @return Grade Card 
	 * @throws SQLException
	 */
	 @RequestMapping(method=RequestMethod.POST, value="/viewGradeCard")
	  public ResponseEntity<String> viewGradeCard(@RequestBody User user, @RequestParam int semester) throws SQLException {

		 int userId = user.getUserId();

		 System.out.println("user id -> " + userId );
		 List<ResultSet> gradeCard = studentService.viewGradeCard(user, semester);
		 
		 if(gradeCard!=null)
		 {
			 ResultSet gradeDetails = gradeCard.get(0); 
			 String json = ResultSetToJsonConverter.convertResultSetToJson(gradeDetails);
			 
			 ResultSet cpi = gradeCard.get(1);
			 String json2 = ResultSetToJsonConverter.convertResultSetToJson(cpi);
			 
			 if (studentService.viewGradeCard(user, semester)!=null) {
		      return new ResponseEntity<String>(json+json2,HttpStatus.OK);
		      
		    }
		 }

		      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Grade Card is Not Available");
		    
		 
	
	  }
	 
	 /**
	  * Method to register as a student
	  * @param student
	  * @return status of registration
	  */
	 @RequestMapping(method=RequestMethod.GET,value = "/register")
	 public ResponseEntity<String> register(@RequestBody Student student)
	 {
		 boolean isRegistered = studentService.register(student);
		 
		 
		 if(isRegistered)
		 {
			 String str = "You are Registered...";
			 return new ResponseEntity<String>(str,HttpStatus.OK);
		 }
		 
		 String str = "Registration Failed !!";
		 return new ResponseEntity<String>(str,HttpStatus.BAD_REQUEST);
	 }
	 
	 
	 /**
	  * Add course
	  * @param user
	  * @param semester
	  * @param courseCode
	  * @return
	  */
	 @RequestMapping(method=RequestMethod.POST, value="/addCourse")
	 public ResponseEntity<String> addCourse(@RequestBody User user , @RequestParam int semester, @RequestParam String courseCode)
	 {
		 boolean isAdded = studentService.addCourse(user,semester,courseCode);
		 if(isAdded)
		 {
			 String str = "Course Added";
			 return new ResponseEntity<String>(str,HttpStatus.OK);
		 }
		 
 
		 String str = "Course Could Not Be Added !!";
		 return new ResponseEntity<String>(str,HttpStatus.BAD_REQUEST); 
	 }
	 
	 /**
	  * Drop Course 
	  * @param user
	  * @param courseCode
	  * @return
	  */
	 @RequestMapping(method=RequestMethod.POST, value = "/dropCourse")
	 public ResponseEntity<String> dropCourse(@RequestBody User user , @RequestParam String courseCode)
	 {
		 boolean isDropped = studentService.dropCourse(user,courseCode);
		 if(isDropped)
		 {
			 String str = "Course Dropped";
			 return new ResponseEntity<String>(str,HttpStatus.OK);
		 }
		 
		 String str = "Course Could Not Be Dropped !!";
		 return new ResponseEntity<String>(str,HttpStatus.BAD_REQUEST);
	 }
	 
	 

	 /**
	  * View Registered Courses of a student 
	  * @param user
	  * @return
	  * @throws SQLException
	  */
	 @RequestMapping(method=RequestMethod.POST, value = "/viewRegisteredCourses")
	  public ResponseEntity<String> viewRegisteredCourses( @RequestBody User user) throws SQLException {

		 int userId = user.getUserId();
		 ResultSet registeredCourses = studentService.viewRegisteredCourses(user);

		 if(registeredCourses!=null)
		 {
			 String json = ResultSetToJsonConverter.convertResultSetToJson(registeredCourses);
			 if (studentService.viewRegisteredCourses(user)!=null) {
		      return new ResponseEntity<String>(json,HttpStatus.OK);
		      
		    }
		 }
		 else {
		      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Registered Course List is Not Available");
		    }
		 
		 return null;
		 
	  }
	 
	 
	 /**
	  * Method to view available courses 
	  * @return
	  * @throws SQLException
	  */
	 @RequestMapping(method=RequestMethod.GET,value = "/viewAvailableCourses")
	  public ResponseEntity<String> viewAvailableCourses() throws SQLException {

		 ResultSet availableCourses = studentService.viewAvailableCourses();

		 if(availableCourses!=null)
		 {
			 String json = ResultSetToJsonConverter.convertResultSetToJson(availableCourses);
		      return new ResponseEntity<String>(json,HttpStatus.OK);
		      
		 }
		 else {
		      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(" Course List is Not Available");
		    }
		 
	  }
	 
	 
	 @RequestMapping(method=RequestMethod.POST , value = "/viewFeeToPay")
	  public ResponseEntity<String> viewFeeToPay(@RequestBody User user, @RequestParam int semester) throws SQLException {

		 int userId = user.getUserId();
		 List<ResultSet> feeDetails = studentService.viewFeeToPay(user, semester);
		 
		 if(feeDetails!=null)
		 {
			 ResultSet feeInformation= feeDetails.get(0); 
			 ResultSet totalFees= feeDetails.get(1); 
			 String json = ResultSetToJsonConverter.convertResultSetToJson(feeInformation);
			 String json2 = ResultSetToJsonConverter.convertResultSetToJson(totalFees);
			 if (studentService.viewGradeCard(user, semester)!=null) {
		      return new ResponseEntity<String>(json+json2,HttpStatus.OK);
		      
		    }
		 }
		      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Fee Details Not Available");
		    
		 
		 
	
	  }
}