/**
 * 
 */
package com.signify.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

import javax.security.auth.login.FailedLoginException;

import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.constants.SQLConstants;
import com.signify.exception.CourseAlreadyRegisteredException;
import com.signify.exception.CourseDoesNotExistException;
import com.signify.exception.CourseLimitFullException;
import com.signify.exception.FailToDropCourseException;
import com.signify.exception.GradeCardNotAvailableException;
import com.signify.exception.MaxCourseRegisterLimitException;
import com.signify.exception.NoRegisteredCourseException;
import com.signify.exception.ProfessorDoesNotTeachCourseException;
import com.signify.exception.StudentRegistrationFailedException;
import com.signify.utils.DBUtils;

/**
 * @author Acer
 *
 */
public class StudentDAOImplementation implements StudentDAOInterface {
	
//	JDBC driver name and database URL
	private Connection conn;
	
	
	public StudentDAOImplementation(Connection conn) {
        this.conn = conn;
    }
	
	
	  
	@Override
	/**
	 * Method to Register
	 * @param student
	 * @return
	 * @throws SQLException
	 * @throws StudentRegistrationFailedException
	 */
	public boolean registerDAO(Student student) throws SQLException, StudentRegistrationFailedException
	{


//        
//        String sql2 = "INSERT IGNORE INTO student (userId, name, studentId, branch, batch, isRegistered) VALUES (?, ?, ?,?,?,?)";
        PreparedStatement stmt2 = conn.prepareStatement(SQLConstants.REGISTER_STUDENT_USER);
        stmt2.setLong(1, student.getUserId());
        stmt2.setString(2, student.getName());
        stmt2.setLong(3, student.getStudentId());
        stmt2.setString(4, student.getBranch());
        stmt2.setLong(5, student.getBatch());
        stmt2.setBoolean(6, student.isRegistered());
//        stmt2.executeUpdate();
        
	

        int rows2 = stmt2.executeUpdate();
        
        if(rows2==0)
        {
        	throw new StudentRegistrationFailedException();
        }
        

        PreparedStatement stmt = conn.prepareStatement(SQLConstants.REGISTER_STUDENT);
        stmt.setLong(1, student.getUserId());
        stmt.setString(2, student.getPassword());
        stmt.setString(3, student.getName());
        stmt.setLong(4, student.getRole());
        int rows1 = stmt.executeUpdate();
        
        
        stmt.close();
        stmt2.close();
        conn.close();
        

        
        return rows1 > 0;
	}
	
	
	
	/**
	 * ADD courses for the semester
	 * @param user
	 * @param semester
	 * @param courseCode
	 * @return
	 * @throws SQLException
	 * @throws MaxCourseRegisterLimitException
	 * @throws CourseLimitFullException
	 * @throws CourseDoesNotExistException
	 * @throws CourseAlreadyRegisteredException
	 */
	public boolean addCourseDAO(User user, int semester, String courseCode) throws SQLException,MaxCourseRegisterLimitException, CourseLimitFullException,CourseDoesNotExistException,CourseAlreadyRegisteredException
	{
		
		 
		boolean isAdded = false;
		int rows2=-1;
		
		int value = -1;
		
		//COUNT number of students enrolled in a particular courseCode
//		String sql = "SELECT COUNT(studentId) AS num_students\r\n"
//				+ "FROM registeredcourse\r\n"
//				+ "WHERE courseCode = ?;";
		
		// CHECK COURSE AVAILABILITY
		String sql5 = "SELECT COUNT(courseCode) as `CourseCount` FROM course WHERE courseCode=?;";
		PreparedStatement stmt5 = conn.prepareStatement(sql5);
		stmt5.setString(1, courseCode);
		ResultSet rs5 = stmt5.executeQuery();
		
		int courseFound = 0;
		
		if(rs5.next())
		{
			courseFound = rs5.getInt("CourseCount");
		}

//				+ "FROM registeredcourse\r\n"
//				+ "WHERE courseCode = ?;";
		
		if(courseFound==0)
		{
			throw new CourseDoesNotExistException();
		}
		
		PreparedStatement stmt = conn.prepareStatement(SQLConstants.COUNT_STUDENTS_IN_COURSECODE);
		stmt.setString(1, courseCode);
		ResultSet rs = stmt.executeQuery();
		
		
		 if (rs.next()) {
             value = rs.getInt("num_students");
//             System.out.println("Value: " + value);
         	}
		 
		 
		int studentId = -1;
//	 	String sql2 = "SELECT studentId from student where userId=?";
		PreparedStatement stmt2 = conn.prepareStatement(SQLConstants.GET_STUDENT_ID);
		stmt2.setLong(1, user.getUserId());
		ResultSet rs2 = stmt2.executeQuery();
		
		
		 if (rs2.next()) {
			 studentId = rs2.getInt("studentId");
         }
		 
		 int numCourseRegistered = -1;
		 	String sql4 = "SELECT COUNT(*) AS num_courses\r\n"
		 			+ "FROM registeredcourse\r\n"
		 			+ "WHERE studentId = ?";
		 	
		 	
			PreparedStatement stmt4 = conn.prepareStatement(sql4);
			stmt4.setLong(1,studentId);
			ResultSet rs4 = stmt4.executeQuery();
			
			
			 if (rs4.next()) {
				 numCourseRegistered = rs4.getInt("num_courses");
	         }
		 
		 if(numCourseRegistered ==6)
		 {
//			 System.out.println("\nYou can't register for more than 4 courses.....");
			 throw new MaxCourseRegisterLimitException();
//			 return false;
		 }
		 
		 

		 if(value<=10)
		 {
//			 String sql3 = "INSERT IGNORE INTO registeredcourse (courseCode, studentId, semester, dateOfRegistration) VALUES (?, ?, ?,?)";
			 PreparedStatement stmt3 = conn.prepareStatement(SQLConstants.INSERT_INTO_REGISTERED_COURSE);
			 LocalDate today = LocalDate.now();

			// Create a SQL date object
			 Date sqlDate = Date.valueOf(today);
			 stmt3.setString(1, courseCode);
			 stmt3.setLong(2, studentId);
			 stmt3.setLong(3, semester);
//			 stmt3.setNull(4);
			 stmt3.setDate(4, sqlDate);
			 rows2 = stmt3.executeUpdate();
			 
			 if(rows2==0)
			 {
				 throw new CourseAlreadyRegisteredException();
			 }
			
			 
			 return rows2>0;
		 }
		  
		 else
		 {
			
			 throw new CourseLimitFullException();
		 }

		
	}
	
	
	/**
	 * View Student Grade Card
	 * @param user
	 * @param semester
	 * @throws SQLException
	 * @throws GradeCardNotAvailableException
	 */
	public void viewGradeCardDAO(User user, int semester)  throws SQLException,GradeCardNotAvailableException
	{ 
		
		 
		int studentId = -1;
//	 	String sql2 = "SELECT studentId from student where userId=?";
		PreparedStatement stmt2 = conn.prepareStatement(SQLConstants.GET_STUDENT_ID);
		stmt2.setLong(1, user.getUserId());
		ResultSet rs2 = stmt2.executeQuery();
		
		
		 if (rs2.next()) {
			 studentId = rs2.getInt("studentId");
         }
		 
		
//		String sql3 = "SELECT t1.courseCode, t4.courseName, t3.instructorName, t1.grade, t2.point from registeredcourse t1 "
//				+ "JOIN grade t2 ON t1.grade=t2.gradeChar JOIN courseprof t3 ON \r\n"
//				+ "t1.courseCode=t3.courseCode JOIN course t4 ON t1.courseCode=t4.CourseCode WHERE t1.studentId = ? and t1.semester=?;";
//		
		 PreparedStatement stmt1 = conn.prepareStatement(SQLConstants.VIEW_GRADE_CARD);
		stmt1.setLong(1, studentId);
		stmt1.setLong(2, semester);
		ResultSet rs1 = stmt1.executeQuery();
		
		boolean flag = false;
		 if(rs1.next())
		 {
			 flag=true;
		 }
		 
		 else if(flag==false)
		 {
			 throw new GradeCardNotAvailableException();
		 }
		 
		 
		 
		PreparedStatement stmt3 = conn.prepareStatement(SQLConstants.VIEW_GRADE_CARD);
		stmt3.setLong(1, studentId);
		stmt3.setLong(2, semester);
		
		 ResultSet rs3 = stmt3.executeQuery();
		 
		 
		 
		 System.out.println("\nGRADE CARD");
		 System.out.println("====================================");
		 DBTablePrinter.printResultSet(rs3);
		
		 
		 
		 
//		 String sql4 = "SELECT AVG(t2.point) as CPI from registeredcourse t1 "
//					+ "JOIN grade t2 ON t1.grade=t2.gradeChar JOIN courseprof t3 ON \r\n"
//					+ "t1.courseCode=t3.courseCode JOIN course t4 ON t1.courseCode=t4.CourseCode WHERE t1.studentId = ? and t1.semester=?;";
			
		
		PreparedStatement stmt4 = conn.prepareStatement(SQLConstants.VIEW_CPI);
		stmt4.setLong(1, studentId);
		stmt4.setLong(2, semester);
		
		ResultSet rs4 = stmt4.executeQuery();

		
		 DBTablePrinter.printResultSet(rs4);
		 
		 
		 
		
	}
	
	
	
	/**
	 * Drop Courses 
	 * @param user
	 * @param courseCode
	 * @return
	 * @throws SQLException
	 * @throws FailToDropCourseException
	 */
	public boolean dropCourseDAO(User user, String courseCode) throws SQLException,FailToDropCourseException
	{
		// GETTING STUDENT ID 
		int studentId = -1;
//	 	String sql2 = "SELECT studentId from student where userId=?";
		PreparedStatement stmt2 = conn.prepareStatement(SQLConstants.GET_STUDENT_ID);
		stmt2.setLong(1, user.getUserId());
		ResultSet rs2 = stmt2.executeQuery();
	
		 if (rs2.next()) {
			 studentId = rs2.getInt("studentId");
         }
		 
		 
	
//		String sql = "DELETE from registeredcourse where studentId=? and courseCode= ?";
		PreparedStatement stmt1 = conn.prepareStatement(SQLConstants.DROP_COURSE);
		stmt1.setInt(1, studentId);
		stmt1.setString(2, courseCode);
		
		int rows1 = stmt1.executeUpdate();
		
		if(rows1==0)
		{
			throw new FailToDropCourseException();
		}
		
		
		return rows1>0;
	}
	
	
	
	/**
	 * Show Registered Courses of student
	 * @param user
	 * @throws SQLException
	 * @throws NoRegisteredCourseException
	 */
	public void showRegisteredCoursesDAO(User user) throws SQLException,NoRegisteredCourseException
	{
		// GETTING STUDENT ID 
		int studentId = -1;
//	 	String sql2 = "SELECT studentId from student where userId=?";
		PreparedStatement stmt2 = conn.prepareStatement(SQLConstants.GET_STUDENT_ID);
		stmt2.setLong(1, user.getUserId());
		ResultSet rs2 = stmt2.executeQuery();
		
		
		 if (rs2.next()) {
			 studentId = rs2.getInt("studentId");
         }
		 
		 
//		 
//		 String sql = "SELECT t1.courseCode, t2.courseName,t1.semester, t1.dateOfRegistration "
//			 		+  "FROM registeredcourse t1\r\n"
//				 		+ " JOIN course t2 ON t1.courseCode = t2.courseCode WHERE t1.studentId = ?;" ;
		 
		 
		 PreparedStatement stmt1 = conn.prepareStatement(SQLConstants.SHOW_REGISTERED_COURSES);
		 stmt1.setLong(1, studentId);
//	        stmt.setLong(1,professorId);

	       ResultSet rs1 = stmt1.executeQuery();
	       
//	       boolean flag = false;
//	       
//	       if(!rs1.next()) {
//	    	   flag=true;
//	       }
//	       
//	       if(flag=true)
//	       {
//	    	   throw new NoRegisteredCourseException();
//	       }
	       
//	       DBTablePrinter.printResultSet(rs);
		 
		 
		 PreparedStatement stmt = conn.prepareStatement(SQLConstants.SHOW_REGISTERED_COURSES);
		 stmt.setLong(1, studentId);
//	        stmt.setLong(1,professorId);

	       ResultSet rs = stmt.executeQuery();
	       
	       DBTablePrinter.printResultSet(rs);
	}
	
	
	
	/**
	 * View Available courses student can add
	 * @throws SQLException
	 */
	public void viewAvailableCoursesDAO()  throws SQLException
	{
		
		
		
		
//		String sql = "SELECT t1.courseCode, t1.courseName, t1.courseFee,  COALESCE(t2.instructorName,'Professor Not Assigned') AS instructorName from course t1\r\n"
//				+ "LEFT JOIN courseprof t2 ON t1.courseCode = t2.courseCode;" ;
		
			PreparedStatement stmt = conn.prepareStatement(SQLConstants.VIEW_AVAILABLE_COURSES);
	//        stmt.setLong(1,professorId);
	
	       ResultSet rs = stmt.executeQuery();
	       
	       DBTablePrinter.printResultSet(rs);
		
	}
	
	
	
	/**
	 * View Fees to Pay 
	 * @param user
	 * @param semester
	 * @throws SQLException
	 */
	public void viewFeeToPayDAO(User user, int semester)  throws SQLException
	{
		int studentId = -1;
//	 	String sql2 = "SELECT studentId from student where userId=?";
		PreparedStatement stmt2 = conn.prepareStatement(SQLConstants.GET_STUDENT_ID);
		stmt2.setLong(1, user.getUserId());
		ResultSet rs2 = stmt2.executeQuery();
		
		
		 if (rs2.next()) {
			 studentId = rs2.getInt("studentId");
         }
		 
		 System.out.println("Fee Details for SEMESTER "+semester);
		 System.out.println("==============================================");
		 
					
			PreparedStatement stmt3 = conn.prepareStatement(SQLConstants.SHOW_FEE_DETAILS_SEMESTER);
	        stmt3.setLong(1,studentId);
	        stmt3.setLong(2,semester);

	       ResultSet rs3 = stmt3.executeQuery();
	       
	       DBTablePrinter.printResultSet(rs3);

				
		PreparedStatement stmt = conn.prepareStatement(SQLConstants.FEE_SUM_SEMESTER);
        stmt.setLong(1,studentId);
        stmt.setLong(2,semester);

       ResultSet rs = stmt.executeQuery();
       
       DBTablePrinter.printResultSet(rs);
       
       
       // DISPLAY FEE PAYMENT STATUS (PAID OR NOT PAID)
       
       boolean isPaid = false;
//       	String sql4 = "SELECT t1.referenceId, t1.amount, t2.mode from payment t1 JOIN paymentmode t2 ON t1.modeOfPayment = t2.modeOfPayment WHERE t1.studentId = ? and t1.semester=?; "
//       			;
       
       	PreparedStatement stmt4 = conn.prepareStatement(SQLConstants.FEE_PAYMENT_STATUS);
		stmt4.setLong(1, studentId);
		stmt4.setLong(2, semester);
		ResultSet rs4 = stmt4.executeQuery();
		
		int referenceId = -1;
		int amount = -1;
		String mode = null;
		Date dateofTransaction = null;
		Time timeofTransaction = null; 
		if(rs4.next())
		{
			isPaid = true;
			referenceId = rs4.getInt(1);
			amount = rs4.getInt(2);
			mode = rs4.getString(3);
			dateofTransaction = rs4.getDate(4);
			timeofTransaction = rs4.getTime(5);
			
		}
		
		if(isPaid==false)
		{
			System.out.println("-------------------------------");
			System.out.println("Fee Payment Status: NOT PAID");
			System.out.println("-------------------------------");
		}
		
		else
		{
			System.out.println("\nPAYMENT DETAILS");
			System.out.println("-------------------------------");
			System.out.println("Fee Payment Status: PAID");
			System.out.println("Amount Paid: INR "+ amount);
			System.out.println("Reference ID: "+ referenceId);
			System.out.println("Mode of Payment: "+ mode);
			System.out.println("Date of Transaction "+ dateofTransaction);
			System.out.println("Time of Transaction "+ timeofTransaction);
			System.out.println("-------------------------------");
		}
		
		
       
       
	}
	
	
	/**
	 * Pay Fee By Card
	 * @param user
	 * @param referenceId
	 * @param cardNumber
	 * @param cardType
	 * @param cardHolderName
	 * @param cvv
	 * @param expiryDate
	 * @param semester
	 * @throws SQLException
	 */
	public void payFeeByCardDAO (User user, int referenceId,int cardNumber,int cardType,String cardHolderName,int cvv,String expiryDate, int semester)  throws SQLException
	{
		
		// INSERTING INTO CARD TABLE 
//		 String sql1 = "INSERT IGNORE INTO card (referenceId, cardNumber, cardType, cardName, cvv, expiryDate) VALUES (?, ?, ?,?,?,?)";
		 PreparedStatement stmt = conn.prepareStatement(SQLConstants.INSERT_INTO_CARD_TABLE);
		 stmt.setLong(1,referenceId);
		 stmt.setLong(2,cardNumber);
		 stmt.setLong(3,cardType);
		 stmt.setString(4,cardHolderName);
		 stmt.setLong(5,cvv);
		 stmt.setString(6,expiryDate);
		 int rs = stmt.executeUpdate();
		 
		 //////////////////////////////////////////////////////////////////
		 // FETCH STUDENT ID
		 
		  int studentId = -1;
//		 	String sql2 = "SELECT studentId from student where userId=?";
			PreparedStatement stmt2 = conn.prepareStatement(SQLConstants.GET_STUDENT_ID);
			stmt2.setLong(1, user.getUserId());
			ResultSet rs2 = stmt2.executeQuery();
			
			
			 if (rs2.next()) {
				 studentId = rs2.getInt("studentId");
	         }
			 
	    //////////////////////////////////////////////////////////////////
		// FETCH FEE TO PAY
//		 String sql3 = "SELECT SUM(courseFee) AS `FEE TO PAY` from course t1 JOIN registeredcourse t2 on t1.courseCode = t2.courseCode "
//					+ "where t2.studentId = ? and t2.semester=?;";
					
					
		PreparedStatement stmt3 = conn.prepareStatement(SQLConstants.FETCH_FEE_TO_PAY);
        stmt3.setLong(1,studentId);
        stmt3.setLong(2,semester);

        ResultSet rs3 = stmt3.executeQuery();
        
        int amount =0;
        
        if (rs3.next()) {
            amount = rs3.getInt(1); // Get the integer value of the third column in the first row of the result set
        }
        
        
        System.out.println("\nProcessing Payment of: INR "+amount);
        System.out.println("================================================");
        

        //////////////////////////////////////////////////////////////////
        // Insert into payment table
//		String sql4 = "INSERT IGNORE INTO payment (studentId, referenceId, amount, modeOfPayment, semester) VALUES (?, ?, ?,?,?)";
		PreparedStatement stmt4 = conn.prepareStatement(SQLConstants.INSERT_INTO_PAYMENT_TABLE);
		stmt4.setLong(1,studentId);
		stmt4.setLong(2,referenceId);
		stmt4.setLong(3,amount);
		stmt4.setLong(4,cardType);
		stmt4.setLong(5,semester);

		 
		int rs4 = stmt4.executeUpdate();
		 
	}
	
	
	
	/**
	 * Pay Fee By Netbanking
	 * @param user
	 * @param referenceId
	 * @param semester
	 * @param modeOfTransfer
	 * @param accountNumber
	 * @param ifscCode
	 * @param bankName
	 * @throws SQLException
	 */
	public void  payFeeByNetbankingDAO(User user, int referenceId, int semester, int modeOfTransfer, int accountNumber, String ifscCode, String bankName)  throws SQLException
	{
		// INSERTING INTO NetBanking TABLE 
//			 String sql1 = "INSERT IGNORE INTO netbanking (referenceId,  modeOfTransfer, accountNumber, ifscCode, bankName) VALUES (?, ?, ?,?,?)";
			 PreparedStatement stmt = conn.prepareStatement(SQLConstants.INSERT_INTO_NETBANKING_TABLE);
			 stmt.setLong(1,referenceId);
			 stmt.setLong(2,modeOfTransfer);
			 stmt.setInt(3,accountNumber);
			 stmt.setString(4,ifscCode);
			 stmt.setString(5,bankName);
			 int rs = stmt.executeUpdate();
			 
		//////////////////////////////////////////////////////////////////
		// FETCH STUDENT ID
		
		int studentId = -1;
//		String sql2 = "SELECT studentId from student where userId=?";
		PreparedStatement stmt2 = conn.prepareStatement(SQLConstants.GET_STUDENT_ID);
		stmt2.setLong(1, user.getUserId());
		ResultSet rs2 = stmt2.executeQuery();
		
		
		if (rs2.next()) {
		studentId = rs2.getInt("studentId");
		}	 
		
		 //////////////////////////////////////////////////////////////////
		// FETCH FEE TO PAY
//		 String sql3 = "SELECT SUM(courseFee) AS `FEE TO PAY` from course t1 JOIN registeredcourse t2 on t1.courseCode = t2.courseCode "
//					+ "where t2.studentId = ? and t2.semester=?;";
//					
					
		PreparedStatement stmt3 = conn.prepareStatement(SQLConstants.FETCH_FEE_TO_PAY);
        stmt3.setLong(1,studentId);
        stmt3.setLong(2,semester);

        ResultSet rs3 = stmt3.executeQuery();
        
        int amount =0;
        
        if (rs3.next()) {
            amount = rs3.getInt(1); // Get the integer value of the third column in the first row of the result set
        }
        
        
        System.out.println("\nProcessing Payment of: INR "+amount);
        System.out.println("================================================");
        
        
        int setPaymentMode = -1;
        
        if(modeOfTransfer == 1)
        {
        	setPaymentMode=3;
        }
        
        else if(modeOfTransfer == 2)
        {
        	setPaymentMode=4;
        }
        
        else if(modeOfTransfer == 3)
        {
        	setPaymentMode=5;
        }
        
        
        
        
        
        //////////////////////////////////////////////////////////////////
        // Insert into payment table
//		String sql4 = "INSERT IGNORE INTO payment (studentId, referenceId, amount, modeOfPayment, semester) VALUES (?, ?, ?,?,?)";
		PreparedStatement stmt4 = conn.prepareStatement(SQLConstants.INSERT_INTO_PAYMENT_TABLE);
		stmt4.setLong(1,studentId);
		stmt4.setLong(2,referenceId);
		stmt4.setLong(3,amount);
		stmt4.setLong(4,setPaymentMode);
		stmt4.setLong(5,semester);
		 
		int rs4 = stmt4.executeUpdate();
			 
	}
	

}


