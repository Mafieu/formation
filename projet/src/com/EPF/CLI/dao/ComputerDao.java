package com.EPF.CLI.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.EPF.CLI.model.Company;
import com.EPF.CLI.model.Computer;

public enum ComputerDao {
	INSTANCE;
	
	private ConnectionManager connectionManager = ConnectionManager.INSTANCE;
	
	private ComputerDao() {
		
	}
	
	public List<Computer> getAll() throws Exception {
		List<Computer> computers = new ArrayList<>();
		Statement statement = null;
		Connection connection = connectionManager.getConnection();
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT ct.id, ct.name, introduced, discontinued, company_id, cn.name"
					+ "from computer as ct"
					+ "left join company as cn on ct.company_id = cn.id");
			while(rs.next()) {
				Company company;
				if(rs.getLong(5) != 0L) {
					company = new Company(rs.getLong(5),
							rs.getString(6));
				} else {
					company = null;
				}
				computers.add(new Computer(rs.getLong(1),
						rs.getString(2),
						rs.getDate(3),
						rs.getDate(4),
						company));
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
	
	public void add(Computer computer) throws Exception {
		PreparedStatement statement = null;
		Connection connection = connectionManager.getConnection();
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)");
			statement.setString(1, computer.getName());
			statement.setDate(2, new Date(computer.getIntroduced().getTime()));
			statement.setDate(3, new Date(computer.getDiscontinued().getTime()));
			statement.setLong(4, computer.getCompany().getId());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			throw new Exception("Error while retrieving all computers");
		}
		finally {
			connection.setAutoCommit(true);
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
	}
}
