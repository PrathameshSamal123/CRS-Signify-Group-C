/**
 * 
 */
package com.signify.constants;

/**
 * @author Acer
 *
 */
public class SQLConstants {
	
	// STUDENTS Queries 
	
	public static final String REGISTER_STUDENT= "INSERT IGNORE INTO user (userId, password, name, roleId) VALUES (?, ?, ?,?)";
	
	public static final String REGISTER_STUDENT_USER =  "INSERT IGNORE INTO student (userId, name, studentId, branch, batch, isRegistered) VALUES (?, ?, ?,?,?,?)";
	
	public static final String COUNT_STUDENTS_IN_COURSECODE = "SELECT COUNT(studentId) AS num_students FROM registeredcourse WHERE courseCode = ?;";
	
	public static final String GET_STUDENT_ID = "SELECT studentId from student where userId=?";
	
	public static final String INSERT_INTO_REGISTERED_COURSE = "INSERT IGNORE INTO registeredcourse (courseCode, studentId, semester, dateOfRegistration) VALUES (?, ?, ?,?)";
	
	public static final String VIEW_GRADE_CARD = "SELECT t1.courseCode, t4.courseName, t3.instructorName, t1.grade, t2.point from registeredcourse t1 "
			+ " JOIN grade t2 ON t1.grade=t2.gradeChar JOIN courseprof t3 ON "
			+ " t1.courseCode=t3.courseCode JOIN course t4 ON t1.courseCode=t4.CourseCode WHERE t1.studentId = ? and t1.semester=?;";
	
	
	public static final String VIEW_CPI = "SELECT AVG(t2.point) as CPI from registeredcourse t1 "
			+ "JOIN grade t2 ON t1.grade=t2.gradeChar JOIN courseprof t3 ON \r\n"
			+ "t1.courseCode=t3.courseCode JOIN course t4 ON t1.courseCode=t4.CourseCode WHERE t1.studentId = ? and t1.semester=?;";
	
	
	public static final String DROP_COURSE = "DELETE from registeredcourse where studentId=? and courseCode= ?";
	
	public static final String SHOW_REGISTERED_COURSES =  "SELECT t1.courseCode, t2.courseName,t1.semester, t1.dateOfRegistration "
	 								+  "FROM registeredcourse t1\r\n"
	 								+ " JOIN course t2 ON t1.courseCode = t2.courseCode WHERE t1.studentId = ?;" ;
	
	
	public static final String COUNT_REGISTERED_COURSES = "SELECT count(t1.courseCode) AS `COUNT` \r\n"
			+ "FROM registeredcourse t1 JOIN course t2 ON t1.courseCode = t2.courseCode WHERE t1.studentId = ?;";
	
	
	public static final String VIEW_AVAILABLE_COURSES = "SELECT t1.courseCode, t1.courseName, t1.courseFee,  COALESCE(t2.instructorName,'Professor Not Assigned') AS instructorName from course t1\r\n"
			+ "LEFT JOIN courseprof t2 ON t1.courseCode = t2.courseCode;" ;
	
	public static final String SHOW_FEE_DETAILS_SEMESTER =  "SELECT t1.courseCode, t1.courseName, t1.courseFee from course t1 JOIN "
	 		+ "registeredcourse t2 on t1.courseCode = t2.courseCode where t2.studentId = ? and t2.semester=?"
	 		;
	
	public static final String FEE_SUM_SEMESTER = "SELECT IFNULL(SUM(courseFee),0) AS `FEE TO PAY` from course t1 JOIN registeredcourse t2 on t1.courseCode = t2.courseCode "
			+ "where t2.studentId = ? and t2.semester=?;";
	
	
	public static final String FEE_PAYMENT_STATUS = "SELECT t1.referenceId, t1.amount, t2.mode, t1.dateOfTransaction, t1.timeOfTransaction from payment t1 JOIN paymentmode t2 ON t1.modeOfPayment = t2.modeOfPayment WHERE t1.studentId = ? and t1.semester=?; "
   			;
	
	
	public static final String INSERT_INTO_CARD_TABLE = "INSERT IGNORE INTO card (referenceId, cardNumber, cardType, cardName, cvv, expiryDate) VALUES (?, ?, ?,?,?,?)";
	
	
	public static final String FETCH_FEE_TO_PAY = "SELECT SUM(courseFee) AS `FEE TO PAY` from course t1 JOIN registeredcourse t2 on t1.courseCode = t2.courseCode "
			+ "where t2.studentId = ? and t2.semester=?;";
	
	public static final String INSERT_INTO_PAYMENT_TABLE = "INSERT IGNORE INTO payment (studentId, referenceId, amount, modeOfPayment, semester, dateOfTransaction, timeOfTransaction) VALUES (?, ?, ?,?,?,CURRENT_DATE,CURRENT_TIME)";
	
	public static final String INSERT_INTO_NETBANKING_TABLE = "INSERT IGNORE INTO netbanking (referenceId,  modeOfTransfer, accountNumber, ifscCode, bankName) VALUES (?, ?, ?,?,?)";
	
	
	
	
	// PROFESSOR QUERIES 
	
	public static final String VIEW__MY_COURSES_PROFESSOR = "SELECT t1.courseCode, t2.courseName from courseprof t1 JOIN course t2 on t1.courseCode = t2.courseCode where instructorUserId=?";
	
	public static final String CHECK_COURSE_EXISTS = "SELECT courseCode, instructorUserId from courseProf where courseCode=?  ";
	
	
	public static final String CHECK_DOES_PROF_TEACH_COURSE = "SELECT courseCode, instructorUserId from courseProf where courseCode=? and instructorUserId=?  ";
	
	public static final String VIEW_ENROLLED_STUDENTS_PROF = "SELECT  t1.studentId, t2.name, t2.branch,t2.batch ,COALESCE(grade,\"Not Graded\") AS `GRADE`  from registeredcourse t1 JOIN student t2 on t1.studentId=t2.studentId\r\n"
			+ "JOIN courseprof t3 on t1.courseCode=t3.courseCode WHERE t1.courseCode = ? and t3.instructorUserId=?";
	
	
	public static final String IS_STUDENT_REGISTERED = "SELECT courseCode, studentId from registeredcourse WHERE studentId=? and courseCode=?";
	
	// ADMIN QUERIES 

}
