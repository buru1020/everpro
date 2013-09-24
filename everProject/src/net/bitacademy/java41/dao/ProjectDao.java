package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.ProjectEx;
import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.util.DBConnectionPool;

@Component
public class ProjectDao {
	DBConnectionPool conPool;
	
	public ProjectDao() {}
	
	public ProjectDao(DBConnectionPool conPool) {
		this.conPool = conPool;
	}
	
	public ProjectDao setDBConnectionPool(DBConnectionPool conPool) {
		this.conPool = conPool;
		return this;
	}
	
	public List<ProjectEx> getUserProjectList(String email) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<ProjectEx> list = new ArrayList<ProjectEx>();

		try {
			con = conPool.getConnection();
			String sql = 
					" select a.PNO, a.TITLE, a.CONTENT, a.START_DATE, a.END_DATE, a.TAG, b.LEVEL, c.EMAIL, c.MNAME, c.TEL" 
					+" from SPMS_PRJS a"
					+"     , ("
					+"        select PNO, EMAIL, LEVEL"
					+"         from SPMS_PRJMEMB" 
					+"         where EMAIL = ?"
					+"        ) b"
					+"     , SPMS_MEMBS c"
					+" where a.PNO = b.PNO  "
					+"     and b.EMAIL = c.EMAIL"
					+" order by a.PNO desc";
			System.out.println("[listPorject(email)] SQL :: \n" + sql);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			ProjectEx project = null;
			while(rs.next()) {
				project = new ProjectEx();
				project.setNo(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setContent(rs.getString("CONTENT"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setTag(rs.getString("TAG"));
				project.setLevel(rs.getInt("LEVEL"));
				project.setPlEmail(rs.getString("EMAIL"));
				project.setPlName(rs.getString("MNAME"));
				project.setPlTel(rs.getString("TEL"));
				list.add(project);
			}
			
			return list;
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
	
	public List<ProjectEx> getProjectList() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<ProjectEx> list = new ArrayList<ProjectEx>();

		try {
			con = conPool.getConnection();
			String sql = 
					" select a.PNO, a.TITLE, a.CONTENT, a.START_DATE, a.END_DATE, a.TAG, a.LEVEL, a.EMAIL, b.MNAME, b.TEL"
					+" from ("
					+"         select a.PNO, a.TITLE, a.CONTENT, a.START_DATE, a.END_DATE, a.TAG, b.LEVEL, b.EMAIL"
					+"         from SPMS_PRJS a left join SPMS_PRJMEMB b on (a.PNO = b.PNO)"
					+"         where 1=1"
					+"             and b.LEVEL = 0 or b.LEVEL is null"
					+"        ) a , SPMS_MEMBS b"
					+" where 1=1"
					+"     and a.EMAIL = b.EMAIL"
					+ " order by a.PNO desc";
			System.out.println("[listPorject] SQL :: \n" + sql);
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			ProjectEx project = null;
			while(rs.next()) {
				project = new ProjectEx();
				project.setNo(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setContent(rs.getString("CONTENT"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setTag(rs.getString("TAG"));
				project.setLevel(rs.getInt("LEVEL"));
				project.setPlEmail(rs.getString("EMAIL"));
				project.setPlName(rs.getString("MNAME"));
				project.setPlTel(rs.getString("TEL"));
				list.add(project);
			}
			
			return list;
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

	public ProjectEx getProjectInfol(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			String sql = 
					" select a.PNO, a.TITLE, a.CONTENT, a.START_DATE, a.END_DATE, a.TAG, b.LEVEL, b.EMAIL, b.MNAME, b.TEL"  
					+" from SPMS_PRJS a "
					+" left join ("
					+" select a.PNO, a.LEVEL, a.EMAIL, b.MNAME, b.TEL"
					+" from SPMS_PRJMEMB a"
					+" , SPMS_MEMBS b"
					+" where a.EMAIL = b.EMAIL"
					+" group by a.PNO"
					+" ) b "
					+" on (a.PNO = b.PNO)"
					+" where 1=1 "
					+" and a.PNO=?"
					+" order by PNO desc";
			System.out.println("[listPorject(email)] SQL :: \n" + sql);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			ProjectEx project = null;
			while(rs.next()) {
				project = new ProjectEx();
				project.setNo(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setContent(rs.getString("CONTENT"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setTag(rs.getString("TAG"));
				project.setLevel(rs.getInt("LEVEL"));
				project.setPlEmail(rs.getString("EMAIL"));
				project.setPlName(rs.getString("MNAME"));
				project.setPlTel(rs.getString("TEL"));
			}
			
			return project;
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
	
	public int add(Project project) throws Exception {
		Connection con = null;
		PreparedStatement projectStmt = null;
		PreparedStatement projectMemberStmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			
			// 1. 프로젝트 정보 insert
			projectStmt = con.prepareStatement(
				"insert into SPMS_PRJS("
				+ " TITLE,CONTENT,START_DATE,END_DATE,TAG)"
				+ " values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			projectStmt.setString(1, project.getTitle());
			projectStmt.setString(2, project.getContent());
			projectStmt.setDate(3, project.getStartDate());
			projectStmt.setDate(4, project.getEndDate());
			projectStmt.setString(5, project.getTag());
			projectStmt.executeUpdate();
			
			// 2. 프로젝트 기본키 받기
			rs = projectStmt.getGeneratedKeys();
			if (rs.next()) {
				project.setNo( rs.getInt(1) );
			}
			
			// 3. 프로젝트멤버 정보 insert
			projectMemberStmt = con.prepareStatement(
					"insert into SPMS_PRJMEMB("
					+ " EMAIL,PNO,LEVEL)"
					+ " values(?,?,0)");
			projectMemberStmt.setString(1, project.getLeader());
			projectMemberStmt.setInt(2, project.getNo());
			projectMemberStmt.executeUpdate();
			
			return project.getNo();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch(Exception e) {}
			try {projectStmt.close();} catch(Exception e) {}
			try {projectMemberStmt.close();} catch(Exception e) {}
		}
	}

	public int update(Project project) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"update SPMS_PRJS set"
				+ " TITLE=?, CONTENT=?, START_DATE=?, END_DATE=?, TAG=? "
				+ " where PNO=?");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, project.getStartDate());
			stmt.setDate(4, project.getEndDate());
			stmt.setString(5, project.getTag());
			stmt.setInt(6, project.getNo());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
		}
	}

	public int delete(int no) throws Exception {
		Connection con = null;
		PreparedStatement projectMemberStmt = null;
		PreparedStatement projectStmt = null;
		
		try {
			con = conPool.getConnection();
			
			// 1. SPMS_PRJMEMB 삭제
			String sql  = 
					"delete from SPMS_PRJMEMB"
							+ " where PNO=?";	
			System.out.println("[Member Delete_1] SQL :: \n " + sql);
			projectMemberStmt = con.prepareStatement(sql);
			projectMemberStmt.setInt(1, no);
			projectMemberStmt.executeUpdate();
			projectMemberStmt.close();
			
			// 2. SPMS_PRJS 삭제
			String sql2  = 
					"delete from SPMS_PRJS"
							+ " where PNO=?";	
			System.out.println("[Member Delete_1] SQL :: \n " + sql2);
			projectStmt = con.prepareStatement(sql2);
			projectStmt.setInt(1, no);
			return projectStmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {projectMemberStmt.close();} catch(Exception e) {}
			try {projectStmt.close();} catch(Exception e) {}
		}
	}
	
	
/*	
	public List<Project> list() throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Project> list = new ArrayList<Project>();

		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select PNO,TITLE,START_DAT,END_DAT"
					+ " from PROJECTS"
					+ " order by PNO desc");
			
			Project project = null;
			while(rs.next()) {
				project = new Project();
				project.setNo(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setStartDate(rs.getDate("START_DAT"));
				project.setEndDate(rs.getDate("END_DAT"));
				list.add(project);
			}
			
			return list;
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

	public int add(Project project) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"insert into PROJECTS("
				+ " MGR,TITLE,CONTENT,START_DAT,END_DAT,CREATED_DAT)"
				+ " values(?,?,?,?,?,now())");
			stmt.setString(1, project.getManagerEmail());
			stmt.setString(2, project.getTitle());
			stmt.setString(3, project.getContent());
			stmt.setDate(4, project.getStartDate());
			stmt.setDate(5, project.getEndDate());
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public Project get(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
					"select PNO,MGR,TITLE,CONTENT,START_DAT,END_DAT"
							+ " from PROJECTS"
							+ " where PNO=?");
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				Project project = new Project();
				project.setNo(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setContent(rs.getString("CONTENT"));
				project.setStartDate(rs.getDate("START_DAT"));
				project.setEndDate(rs.getDate("END_DAT"));
				project.setManagerEmail(rs.getString("MGR"));
				return project;
				
			} else {
				return null;
			}
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
	
	public int change(Project project) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"update PROJECTS set"
				+ " MGR=?,TITLE=?,CONTENT=?,"
				+ " START_DAT=?,END_DAT=?,CREATED_DAT=now()"
				+ " where PNO=?");
			stmt.setString(1, project.getManagerEmail());
			stmt.setString(2, project.getTitle());
			stmt.setString(3, project.getContent());
			stmt.setDate(4, project.getStartDate());
			stmt.setDate(5, project.getEndDate());
			stmt.setInt(6, project.getNo());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public int remove(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"delete from PROJECTS"
				+ " where PNO=?"	);
			stmt.setInt(1, no);
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
*/
	
}
