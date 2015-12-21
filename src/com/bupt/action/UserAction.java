package com.bupt.action;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bupt.dao.UserDao;
import com.bupt.model.User;

public class UserAction {
	
	public static void main(String[] args) throws SQLException{
		System.out.print(getTime("1425140010000"));

	}
	
	 public static  String getTime(String i){
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Timestamp nowdate = new Timestamp(Long.parseLong(i));
	        String datestr = sdf.format(nowdate);
	        return datestr;
    }
}
 