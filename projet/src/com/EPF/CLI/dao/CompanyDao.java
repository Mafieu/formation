package com.EPF.CLI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.EPF.CLI.model.Company;
import com.EPF.CLI.model.Computer;

public enum CompanyDao {
	INSTANCE;

	private ConnectionManager connectionManager = ConnectionManager.INSTANCE;
	
	private CompanyDao() {
		
	}
	
	public List<Computer> getAll() throws Exception {
		List<Computer> computers = new ArrayList<>();
		Statement statement = null;
		Connection connection = connectionManager.getConnection();
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT ct.id, ct.name, introduced, discontinued, cn.id, cn.name from computer as ct join company as cn on ct.company_id = cn.id");
			while(rs.next()) {
				computers.add(new Computer(rs.getLong(1),
						rs.getString(2),
						rs.getDate(3),
						rs.getDate(4),
						new Company(rs.getLong(5),
								rs.getString(6))));
			}
		} catch (SQLException e) {
			throw new Exception("Error while retrieving all computers");
		}
		finally {
			if(statement != null) {
				try {
					statement.close();
				} catch(SQLException e) {
					throw new Exception("Error closing statement");
				}
			}
			if(connection != null) {
				connectionManager.close(connection);
			}
		}
		return computers;
	}
	
	public Company get(Long id) throws Exception {
		Company company = null;
		PreparedStatement statement = null;
		Connection connection = connectionManager.getConnection();
		try {
			statement = connection.prepareStatement("select id, name from company where id = ?");
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				company = new Company(rs.getLong(1),
								rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error while retrieving company " + id);
		}
		finally {
			if(statement != null) {
				try {
					statement.close();
				} catch(SQLException e) {
					throw new Exception("Error closing statement");
				}
			}
			if(connection != null) {
				connectionManager.close(connection);
			}
		}
		return company;
	}
}
