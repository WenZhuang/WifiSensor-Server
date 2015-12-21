package com.bupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bupt.db.DBUtil;
import com.bupt.model.User;

public class UserDao {

	public void addUser(User user) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				"insert into login" +
				"(username,password)" +
				"values(" +
				"?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,user.getUsername());
		ptmt.setString(2,user.getPassword());
	
		ptmt.execute();
	}
	
	public void updateUser(User user) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" update login " +
				" set username=?,password=? " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,user.getUsername());
		ptmt.setString(2,user.getPassword());
		ptmt.setInt(3, user.getId());
		ptmt.execute();
	}
	
	public void deleteUser(int id) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" delete from login " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	
	public List<User> query() throws SQLException{
		Connection conn=DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		
		List<User> result = new ArrayList<User>();
        ResultSet	rs = stmt.executeQuery("select * from login");
        
        while(rs.next()){
        	User user = new User();
        	user.setUsername(rs.getString("username") );
        	user.setPassword(rs.getString("password")); 
        	result.add(user);
        }
        return result;
	}
	
	public List<User> query(String username) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="select * from login where username = ?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setString(1,username);
        ResultSet	rs = ptmt.executeQuery();
        
        List<User> result = new ArrayList<User>();
        
        while(rs.next()){
        	User user = new User();
        	user.setUsername(rs.getString("username") );
        	user.setPassword(rs.getString("password")); 
        	result.add(user);
        }
        return result;
	}
	
}
