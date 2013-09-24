package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Task;

@Component
public class TaskDao {

DBConnectionPool conPool;
	
	public TaskDao() {}
	
	public TaskDao(DBConnectionPool conPool) {
		this.conPool = conPool;
	}
	
	public TaskDao setDBConnectionPool(DBConnectionPool conPool) {
		this.conPool = conPool;
		return this;
	}
	
	public Task getTask(int projectNo, int taskNo) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = conPool.getConnection();
			
			String sql =
					" select TNO, PNO, TITLE, UIPROTOURL, CONTENT , START_DATE, END_DATE, STATUS " 
					+" from SPMS_TASKS"
					+" where PNO = ? "
					+"     and TNO =  ? ";
			System.out.println("[getTask] SQL :: \n" + sql);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, projectNo);
			stmt.setInt(2, taskNo);
			rs = stmt.executeQuery();
			Task task = null;
			while(rs.next()) {
				task =  new Task()
									.setTaskNo(rs.getInt("TNO"))
									.setProjectNo(rs.getInt("PNO"))
									.setTitle(rs.getString("TITLE"))
									.setUiProtoUrl(rs.getString("UIPROTOURL"))
									.setContent(rs.getString("CONTENT"))
									.setStartDate(rs.getDate("START_DATE"))
									.setEndDate(rs.getDate("END_DATE"))
									.setStatus(rs.getInt("STATUS"));
			}
			
			return task;
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}

	
	public List<Task> getTaskList(int no) throws Exception {
		System.out.println(no);
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = conPool.getConnection();
			
			String sql =
						" select TNO, PNO, TITLE, UIPROTOURL, CONTENT , START_DATE, END_DATE, STATUS " 
						+" from SPMS_TASKS "
						+" where PNO = ? "
						+" order by TNO desc ";
			System.out.println("[getTaskList] SQL :: \n" + sql);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			List<Task> taskList = new ArrayList<Task>();
			while(rs.next()) {
				taskList.add( new Task()
						.setTaskNo(rs.getInt("TNO"))
						.setProjectNo(rs.getInt("PNO"))
						.setTitle(rs.getString("TITLE"))
						.setUiProtoUrl(rs.getString("UIPROTOURL"))
						.setContent(rs.getString("CONTENT"))
						.setStartDate(rs.getDate("START_DATE"))
						.setEndDate(rs.getDate("END_DATE"))
						.setStatus(rs.getInt("STATUS"))
						);
			}
			
			return taskList;
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}

	public int update(Task task) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			String sql =
					" update SPMS_TASKS set"
							+ " TITLE=?, UIPROTOURL=?, CONTENT=?, START_DATE=?, END_DATE=?, STATUS=?"
							+ " where PNO = ?"
							+ " 	and TNO = ?";
			System.out.println("[upate] SQL :: \n" + sql);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, task.getTitle());
			stmt.setString(2, task.getUiProtoUrl());
			stmt.setString(3, task.getContent());
			stmt.setDate(4, task.getStartDate());
			stmt.setDate(5, task.getEndDate());
			stmt.setInt(6, task.getStatus());
			stmt.setInt(7, task.getProjectNo());
			stmt.setInt(8, task.getTaskNo());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
		}
	}

	public int add(Task task) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			String sql =
					 		" insert into SPMS_TASKS( PNO, TITLE, UIPROTOURL, CONTENT, START_DATE, END_DATE, STATUS )"
							+ " values(?, ?, ?, ?, ?, ?, ?)";
			System.out.println("[add] SQL :: \n" + sql);
			stmt = con.prepareStatement(sql);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, task.getProjectNo());
			stmt.setString(2, task.getTitle());
			stmt.setString(3, task.getUiProtoUrl());
			stmt.setString(4, task.getContent());
			stmt.setDate(5, task.getStartDate());
			stmt.setDate(6, task.getEndDate());
			stmt.setInt(7, task.getStatus());
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
		}
	}

	public int delete(int projectNo, int taskNo) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			String sql =
					" delete from SPMS_TASKS "
							+ " where PNO = ?"
							+ " 	and TNO = ?";
			System.out.println("[delete] SQL :: \n" + sql);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, projectNo);
			stmt.setInt(2, taskNo);
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
		}
	}

	
	
}
