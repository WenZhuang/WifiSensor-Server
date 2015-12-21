package com.bupt.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bupt.db.DBUtil;
import com.bupt.model.Person;
import com.bupt.model.User;


public class PersonDao {
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ardb","root","root"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void insert(String mac,int intime,int outtime,int dwellTime){
		Connection conn = getConnection();
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("insert into person (mac,intime,outtime,dwellTime) values(?,?,?,?)");
			pst.setString(1,mac);
			pst.setInt(2, intime);
			pst.setInt(3, outtime);
			pst.setInt(4, dwellTime);
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Person> query(String mac) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="select * from person where mac = ?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setString(1,mac);
        ResultSet	rs = ptmt.executeQuery();
        
        List<Person> result = new ArrayList<Person>();
        
        while(rs.next()){
        	Person person = new Person();
        	person.setIntime(rs.getInt("intime"));
        	person.setOuttime(rs.getInt("outtime")); 
        	person.setDwellTime(rs.getInt("dwellTime"));
        	result.add(person);
        }
        return result;
	}
	
	public Person query(int id) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="select * from person where id = ?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1,id);
        ResultSet rs = ptmt.executeQuery();
        
        Person person = new Person();
        
        while(rs.next()){	
        	person.setIntime(rs.getInt("intime"));
        	person.setOuttime(rs.getInt("outtime")); 
        	person.setDwellTime(rs.getInt("dwellTime")); 
        }
        
        return person;
	}
	
	
}
