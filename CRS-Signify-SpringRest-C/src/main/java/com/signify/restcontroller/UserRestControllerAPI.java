/**
 * 
 */
package com.signify.restcontroller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.signify.bean.User;
import com.signify.dao.UserDAOImplementation;
import com.signify.service.UserServiceOperations;
/**
 * @author Acer
 *
 */


@RestController
public class UserRestControllerAPI {
	
	@Autowired
	private UserServiceOperations userService;
	
	/**
	 * Method to login 
	 * @param user
	 * @return User object if login successfull 
	 */
	 @RequestMapping(method=RequestMethod.POST, value = "/login")
	  public ResponseEntity<User> login(@RequestBody User user) {

		 int userId = user.getUserId();
		 String password = user.getPassword();

		 User userObject = userService.login(userId, password);
	    if (userObject!=null) {
	      return ResponseEntity.ok(userObject);
	    } 
	    
	    else 
	    {
	    	return null;
	    }
	
	 }
	 
	 /**
	  * Method to update password s
	  * @param param
	  * @return
	  * @throws SQLException
	  */
	 @RequestMapping(method=RequestMethod.POST,value= "/updatePassword")
		public ResponseEntity<String> updatePassword(@RequestBody Map<String,String> param) throws SQLException {
			
		 	int userId = Integer.parseInt(param.get("userId"));
		 	String password = param.get("password");
		 	String newPassword = param.get("newPassword");
		 
			boolean isAdded = userService.updatePassword(userId, password, newPassword);
			
			if(isAdded==true)
			{
				 return ResponseEntity.status(HttpStatus.OK).body("Password Updated");
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password Could Not be Updated");		
		}
}
