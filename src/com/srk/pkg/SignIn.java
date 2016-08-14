package com.srk.pkg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Enumeration;
import java.util.Hashtable;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/signIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Hashtable<String, String> customerHash = new Hashtable<String, String>();
		
		customerHash.put("EMAIL", (String)request.getParameter("email"));
		customerHash.put("PASSWORD", (String)request.getParameter("password"));
		boolean valid = processSignIn(customerHash);
		
		Enumeration objectList = request.getSession().getAttributeNames();
		
		PrintWriter out = response.getWriter();
	
		if (!valid){
			out.println("invalid");
			
		}else {
			//response.encodeRedirectURL(home.html);
			//response.addHeader("h1", "lets see");
			response.sendRedirect("http://localhost:8080/BingoWebApp/home.jsp");
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private Boolean processSignIn(Hashtable<String, String> customerHash) {
		String passWord = customerHash.get("PASSWORD");
		String eMail = customerHash.get("EMAIL");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Connection connection = null;
		Hashtable<String, String> rowHash = new Hashtable<String,String>();
		boolean isValid = true;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/Bingo","root", "8998");
	
			String sql = "SELECT * FROM CLIENTINFO WHERE USEREMAIL = ? AND USERPASSWORD = ? ";
		
			ps = connection.prepareStatement(sql);
			ps.setString(1, eMail);
			ps.setString(2, passWord);
						
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			
			int nulCol = rsmd.getColumnCount();
			
			while (rs.next()){
				for (int i = 0; i < nulCol; i++){
					String key = rsmd.getColumnName(i+1);
					rowHash.put(key, rs.getString(i+1));
				}
			}
			
			String customerEmail = String.valueOf(rowHash.get("USEREMAIL"));
			String customerPassword = String.valueOf(rowHash.get("USERPASSWORD"));
			
			if (eMail.equalsIgnoreCase(customerEmail) && (passWord.equals(customerPassword))){
				return isValid;
			}
			
			System.out.println("great");			
			
		} catch (Exception e) {

			System.err.println("The User Name & Password are Invlid!");
		     System.err.println(e.getMessage());
		     isValid = false;
			
		} finally {
			
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
			} catch (Exception e) {}
		}
		return isValid;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
