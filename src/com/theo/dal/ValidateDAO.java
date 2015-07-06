package com.theo.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.theo.vo.UserVO;

public class ValidateDAO {

	public UserVO getUser(String userid, String password) throws SQLException {
		Statement stmt = null;
		String query = "SELECT USERID, PASSWORD, FIRSTNAME, LASTNAME, AGE, CITY " +
				"FROM WEB_USERS WHERE USERID='" + userid + "' AND PASSWORD='" +password +"'";

		//create db connection
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "theo",
					"clover");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(connection != null) {
			try {
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				UserVO uvo = new UserVO();
				if(rs.next()) {
					uvo.setUserID(rs.getString("USERID"));
					uvo.setPassword(rs.getString("PASSWORD"));
					uvo.setFName(rs.getString("FIRSTNAME"));
					uvo.setLName(rs.getString("LASTNAME"));
					uvo.setAge(rs.getInt("AGE"));
					uvo.setCity(rs.getString("CITY"));
					return uvo;
//					System.out.println(uvo.getUserID() + " Found");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(stmt==null) {
					stmt.close();
				}
			}
		}
		else {
			System.out.println("Failed to connect to DB");
		}
		return null;
	}

	
//	public static void main(String[] a) {
//		ValidateDAO vdao = new ValidateDAO();
//		try {
//			vdao.getUser("theo", "123");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
