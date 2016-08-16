package com.srk.pkg;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Enumeration;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.model.core.Element;



/**
 * Servlet implementation class SingUp
 */
@WebServlet("/SignUp")
public class SingUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Hashtable<String, String> customerHash = new Hashtable<String, String>();
		customerHash.put("USERNAME", (String)request.getParameter("username"));
		customerHash.put("PASSWORD", (String)request.getParameter("password"));
		customerHash.put("COMMAND", (String)request.getParameter("command"));
		customerHash.put("EMAIL", (String)request.getParameter("email"));
		customerHash.put("PASSWORD1",(String)request.getParameter("password1"));
		
		processCustomerInfo(customerHash);
		 //TODO: when it is done go back to the log In page 
		response.getWriter().append("Served at: ").append(request.getContextPath());
 	}

	
	private void processCustomerInfo(Hashtable<String, String> customerHash) {
		String userName = customerHash.get("USERNAME");
		String passWord = customerHash.get("PASSWORD");
		String passWord1 = customerHash.get("PASSWORD1");
		String command = customerHash.get("COMMAND");
		String eMail = customerHash.get("EMAIL");
		String msg = "success";
		
		Date today = (Date) Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String curday = sdf.format(today);
		
		customerHash.put("msg", msg);
	    	customerHash = editSignUp(customerHash);
			
		PreparedStatement ps = null;
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/Bingo","root", "8998");
	
			String sql = "INSERT INTO CLIENTINFO ";
					sql += "VALUES (?,?,?,?,?,?,?) ";
		
			ps = connection.prepareStatement(sql);
			ps.setString(1,userName);
			ps.setString(2, passWord);
			ps.setString(3, eMail);
			ps.setString(4, "100");
			ps.setString(5, command);
			ps.setString(6, curday);
			ps.setString(7, "3");
						
			ps.executeUpdate();
			
			System.out.println("great");			
			
		} catch (Exception e) {

			System.err.println("Got an exception!");
		     System.err.println(e.getMessage());
			
		} finally {
			
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
			} catch (Exception e) {}
		}
		
	}
		

	private Hashtable<String, String> editSignUp(Hashtable<String, String> customerHash) {
		String userName = customerHash.get("USERNAME");
		String passWord = customerHash.get("PASSWORD");
		String passWord1 = customerHash.get("PASSWORD1");
		String command = customerHash.get("COMMAND");
		String eMail = customerHash.get("EMAIL");
		String msg = customerHash.get("msg");
		Hashtable<String, String> errors = new Hashtable<String, String>();
		
		if(userName.equals("")){
			msg = "error";
			
		}
		//TODO: check each attribute to be empty and password match.. 
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
