package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Photo;
import net.bitacademy.java41.vo.ProjectMember;

@Component
public class MemberDao {
	DBConnectionPool conPool;

	public MemberDao() {	}
	
	public MemberDao(DBConnectionPool conPool) {
		this.conPool = conPool;
	}
	
	public MemberDao setDBConnectionPool(DBConnectionPool conPool) {
		this.conPool = conPool;
		return this;
	}
	
	
	public Member getMember(String email, String password) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
					" select EMAIL, MNAME, PWD, TEL, BLOG, REG_DATE, UPDATE_DATE, ANO, DET_ADDR, TAG, LEVEL"
					+ " from SPMS_MEMBS"
					+ " where EMAIL=?"
					+ "		and PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				List<Photo> list = this.listPhoto(email);
				String[] photos = null;
				if (list.size() > 0) {
					photos = new String[list.size()];
					int index = 0;
					for( Photo photo : list ) {
						photos[index] = photo.getFilename();
					}
				}
				Member member = new Member()
											.setEmail(rs.getString("EMAIL"))
											.setName(rs.getString("MNAME"))
											.setTel(rs.getString("PWD"))
											.setTel(rs.getString("TEL"))
											.setBlog(rs.getString("BLOG"))
											.setRegDate(rs.getDate("REG_DATE"))
											.setUpdateDate(rs.getDate("UPDATE_DATE"))
											.setAddressNo(rs.getInt("ANO"))
											.setDetailAddress(rs.getString("DET_ADDR"))
											.setTag(rs.getString("TAG"))
											.setLevel(rs.getInt("LEVEL"))
											.setPhotos(photos);
				return member;
				
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public Member getMember(String email) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
					" select EMAIL, MNAME, PWD, TEL, BLOG, REG_DATE, UPDATE_DATE, ANO, DET_ADDR, TAG, LEVEL"
					+ " from SPMS_MEMBS"
					+ " where EMAIL=?");
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if (rs.next()) {
				List<Photo> list = this.listPhoto(email);
				String[] photos = null;
				if (list.size() > 0) {
					photos = new String[list.size()];
					int index = 0;
					for( Photo photo : list ) {
						photos[index] = photo.getFilename();
					}
				}
				Member member = new Member()
												.setEmail(rs.getString("EMAIL"))
												.setName(rs.getString("MNAME"))
												.setTel(rs.getString("PWD"))
												.setTel(rs.getString("TEL"))
												.setBlog(rs.getString("BLOG"))
												.setRegDate(rs.getDate("REG_DATE"))
												.setUpdateDate(rs.getDate("UPDATE_DATE"))
												.setAddressNo(rs.getInt("ANO"))
												.setDetailAddress(rs.getString("DET_ADDR"))
												.setTag(rs.getString("TAG"))
												.setLevel(rs.getInt("LEVEL"))
												.setPhotos(photos);
				return member;
				
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}

	public int addMember(Member member) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			String sql = 
					"insert into SPMS_MEMBS( EMAIL, MNAME, PWD, TEL, BLOG, REG_DATE, UPDATE_DATE, ANO, DET_ADDR, TAG, LEVEL )"
							+ " values(?, ?, ?, ?, ?, now(), now(), NULL, NULL, NULL, 0)";
			System.out.println("[addMember] SQL ::\n" + sql);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getPassword());
			stmt.setString(4, member.getTel());
			stmt.setString(5, member.getBlog());
//			stmt.setInt(6, member.getAddressNo());
//			stmt.setString(7, member.getDetailAddress());
//			stmt.setString(8, member.getTag());
//			stmt.setInt(9, member.getLevel());
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}

	public List<ProjectMember> getProjectMemberList(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			String sql = 
					"select a.PNO, a.LEVEL as PRJ_LVL, a.EMAIL, b.MNAME, b.TEL, b.BLOG, b.ANO, b.DET_ADDR, b.TAG, b.LEVEL as MEMB_LVL"
					+" from SPMS_PRJMEMB a"
					+" , SPMS_MEMBS b"
					+" where a.EMAIL = b.EMAIL"
					+" and a.PNO = ?"
					+" order by a.LEVEL, b.MNAME";
			System.out.println("[getPrjtMerList] SQL: \n" + sql);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			List<ProjectMember> list = new ArrayList<ProjectMember>();
			while (rs.next()) {
				ProjectMember projectMember = new ProjectMember()
										.setProjectNo(rs.getInt("PNO"))
										.setProjectLevel(rs.getInt("PRJ_LVL"))
										.setEmail(rs.getString("EMAIL"))
										.setName(rs.getString("MNAME"))
										.setTel(rs.getString("TEL"))
										.setBlog(rs.getString("BLOG"))
										.setAddressNo(rs.getInt("ANO"))
										.setDetailAddress(rs.getString("DET_ADDR"))
										.setTag(rs.getString("TAG"))
										.setLevel(rs.getInt("MEMB_LVL"));
				list.add(projectMember);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}

	public List<Member> getMemberList() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			String sql = 
					"select EMAIL, MNAME, TEL, BLOG, ANO, DET_ADDR, TAG, LEVEL"
					+" from SPMS_MEMBS"
					+" order by MNAME";
			System.out.println("[getPrjtMerList] SQL: \n" + sql);
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<Member> list = new ArrayList<Member>();
			while (rs.next()) {
				list.add( new Member()
									.setEmail(rs.getString("EMAIL"))
									.setName(rs.getString("MNAME"))
									.setTel(rs.getString("TEL"))
									.setBlog(rs.getString("BLOG"))
									.setAddressNo(rs.getInt("ANO"))
									.setDetailAddress(rs.getString("DET_ADDR"))
									.setTag(rs.getString("TAG"))
									.setLevel(rs.getInt("LEVEL"))
								);
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public int add(Member member) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"insert into SPMS_MEMBS("
				+ " EMAIL,MNAME,PWD,TEL,"
				+ " BLOG,REG_DATE,UPDATE_DATE,DET_ADDR,TAG,LEVEL)"
				+ " values(?,?,?,?,?,now(),now(),?,?,?)");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getPassword());
			stmt.setString(4, member.getTel());
			stmt.setString(5, member.getBlog());
			stmt.setString(6, member.getDetailAddress());
			stmt.setString(7, member.getTag());
			stmt.setInt(8, member.getLevel());
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public int update(Member member) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"update SPMS_MEMBS set"
				+ " MNAME=?, TEL=?, BLOG=?, TAG=?, UPDATE_DATE=now()"
				+ " where EMAIL=?");
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getTel());
			stmt.setString(3, member.getBlog());
			stmt.setString(4, member.getTag());
			stmt.setString(5, member.getEmail());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}


	public int delete(String email) throws Exception {
		Connection con = null;
		PreparedStatement projectMemberStmt = null;
		PreparedStatement memberStmt = null;
		
		try {
			con = conPool.getConnection();
			
			// 1. SPMS_PRJMEMB 삭제
			String sql  = 
					"delete from SPMS_PRJMEMB"
							+ " where EMAIL=?";	
			System.out.println("[Member Delete_1] SQL :: \n " + sql);
			projectMemberStmt = con.prepareStatement(sql);
			projectMemberStmt.setString(1, email);
			projectMemberStmt.executeUpdate();
			projectMemberStmt.close();
			
			// 2. SPMS_MEMBS 삭제
			String sql2  = 
					"delete from SPMS_MEMBS"
							+ " where EMAIL=?";	
			System.out.println("[Member Delete_1] SQL :: \n " + sql2);
			memberStmt = con.prepareStatement(sql2);
			memberStmt.setString(1, email);
			return memberStmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {projectMemberStmt.close();} catch(Exception e) {}
			try {memberStmt.close();} catch(Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public int myInfoChange(Member member) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"update SPMS_MEMBS set"
				+ " MNAME=?,TEL=?,BLOG=?,DET_ADDR=?,TAG=?,LEVEL=?"
				+ " where EMAIL=? and PWD=?");
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getTel());
			stmt.setString(3, member.getBlog());
			stmt.setString(4, member.getDetailAddress());
			stmt.setString(5, member.getTag());
			stmt.setInt(6, member.getLevel());
			stmt.setString(7, member.getEmail());
			stmt.setString(8, member.getPassword());
			
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}
	
	public int changePassword(
			String email, String oldPassword, String newPassword) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"update SPMS_MEMBS set"
				+ " PWD=?,UPDATE_DATE=now()"
				+ " where EMAIL=? and PWD=?");
			stmt.setString(1, newPassword);
			stmt.setString(2, email);
			stmt.setString(3, oldPassword);
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}

	public int addPhoto(String email, String path) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			String sql = 
					" insert into SPMS_MEMIMG( EMAIL, IMGURL )"
					+ " values(?, ?)";
			System.out.println("[addMember] SQL ::\n" + sql);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, path);
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}

	public List<Photo> listPhoto(String email) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
					" select INO, IMGURL"
					+ " from SPMS_MEMIMG"
					+ " where EMAIL=?");
			stmt.setString(1, email);	
			rs = stmt.executeQuery();
			ArrayList<Photo> list = new ArrayList<Photo>();
			while (rs.next()) {
				list.add( new Photo()
								.setNo(rs.getInt("INO"))
								.setEmail(email)
								.setFilename(rs.getString("IMGURL"))
								); 
			}
			return list;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if ( con != null && con.getAutoCommit() ) {
				conPool.returnConnection(con);
			}
		}
	}	
	
}




