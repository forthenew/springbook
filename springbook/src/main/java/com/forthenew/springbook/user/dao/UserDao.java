package com.forthenew.springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.forthenew.springbook.user.User;

public class UserDao {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void add(User user) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		
		try
		{
			c = dataSource.getConnection();
			ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
			
		} catch( SQLException e) {
			throw e;
		} finally {
			if(ps != null) { try {ps.close();} catch(SQLException e) {} } 
			if(c != null) { try {c.close();} catch(SQLException e) {} } 
		}
	}
	
	public User get(String id) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try
		{
			c = dataSource.getConnection();
			ps = c.prepareStatement("select * from users where id = ?");
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
			
		} catch( SQLException e) {
			throw e;
		} finally {
			if(rs != null) { try {rs.close();} catch(SQLException e) {} } 
			if(ps != null) { try {ps.close();} catch(SQLException e) {} } 
			if(c != null) { try {c.close();} catch(SQLException e) {} } 
		}
		
		if(user == null) throw new EmptyResultDataAccessException(1);
		
		return user;
	}
	
	public void deleteAll() throws SQLException {
		try ( Connection c = dataSource.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM users");) {
			
			ps.executeUpdate();
		}
	}
	
	public int getCount() throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count;
		
		try
		{
			c = dataSource.getConnection();
			ps = c.prepareStatement("SELECT COUNT(*) FROM users");
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			
		} catch( SQLException e) {
			throw e;
		} finally {
			if(rs != null) { try {rs.close();} catch(SQLException e) {} } 
			if(ps != null) { try {ps.close();} catch(SQLException e) {} } 
			if(c != null) { try {c.close();} catch(SQLException e) {} } 
		}
		
		return count;
	}
}
