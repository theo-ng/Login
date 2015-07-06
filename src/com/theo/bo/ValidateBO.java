package com.theo.bo;

import java.sql.SQLException;

import com.theo.dal.ValidateDAO;
import com.theo.vo.UserVO;

public class ValidateBO {

	public boolean validate(String userid, String password) {
		UserVO uvo = new UserVO();
		ValidateDAO vdao = new ValidateDAO();
		try {
			uvo = vdao.getUser(userid, password);
			if(uvo!=null)
				if(userid.equals(uvo.getUserID()) && password.equals(uvo.getPassword()))
					return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
