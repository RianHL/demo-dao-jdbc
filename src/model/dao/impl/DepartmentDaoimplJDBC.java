package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoimplJDBC implements DepartmentDao{
	
	private Connection conn;
	
	public DepartmentDaoimplJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department d) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO department " +
					"(Id, Name)" +
					"VALUES " + "(?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, d.getId());
			ps.setString(2, d.getName());
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					d.setId(id);
				}
				DB.closeResult(rs);
			}
			else {
				throw new DBException("Unexpected Error! No rows affected!");
			}
			}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Department d) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE department " +
					"SET Id = ?, Name = ?" +
					"WHERE Id = ?");
			ps.setInt(1, d.getId());
			ps.setString(2, d.getName());
			ps.setInt(3, d.getId());
			ps.executeUpdate();
			}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM department " + "WHERE Id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Department findById(Integer Id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select department.* "
					+ "FROM department "
					+ "Where department.Id = ?");
			ps.setInt(1, Id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResult(rs);
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select department.*"
					+ "FROM department "
					+ "ORDER BY Name");
			rs = ps.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while(rs.next()) {
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResult(rs);
		}
	}

}
