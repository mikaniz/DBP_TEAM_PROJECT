package persistence.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import persistence.util.*;
import service.dto.*;

public class MemberDAO {

	private JDBCUtil jdbcUtil = null;
	
	public MemberDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	public List<Member> getMemberList() {
		String allQuery = "SELECT * FROM member";
		jdbcUtil.setSqlAndParameters(allQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Member> list = new ArrayList<Member>();
			
			while (rs.next()) {
				Member dto = new Member();
				dto.setId(rs.getString("memberId"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setGrade(rs.getString("grade"));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public Member getMemberById(String id) {
		String searchQuery = "SELECT * FROM member WHERE memberId=?";
		Object[] param = new Object[] {id};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		Member dto = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				dto = new Member();
				dto.setId(rs.getString("memberId"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setGrade(rs.getString("grade"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return dto;
	}

	public int insertMember(Member member) {
		int result = 0;
		String insertQuery = "INSERT INTO "
				+ "member (memberId, pw, name, phone, email, grade) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] {member.getId(), member.getPw(), member.getName(), 
						member.getPhone(), member.getEmail(), member.getGrade()};
		jdbcUtil.setSqlAndParameters(insertQuery, param);
		try {
			result = jdbcUtil.executeUpdate();
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}

	public int updateMember(Member member) {
		int result = 0;
		String updateQuery = "UPDATE member "
				+ "SET pw=?, name=?, phone=?, email=?, grade=? "
				+ "WHERE memberId=?";
		Object[] param = new Object[] {member.getPw(), member.getName(), member.getPhone(), 
						member.getEmail(), member.getGrade(), member.getId()};
		jdbcUtil.setSqlAndParameters(updateQuery, param);
		try {
			result = jdbcUtil.executeUpdate();
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}

	public int deleteMember(String id) {
		int result = 0;
		String deleteQuery = "DELETE FROM member WHERE memberId=?";
		Object[] param = new Object[] {id};
		jdbcUtil.setSqlAndParameters(deleteQuery, param);
		try {
			result = jdbcUtil.executeUpdate();
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}

	public boolean existingMember(String id) {
		String query = "SELECT count(*) FROM member WHERE memberId=?";
		Object[] param = new Object[] {id};
		jdbcUtil.setSqlAndParameters(query, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}
	
	public boolean isMaster(String id) {
		String query = "SELECT grade FROM member WHERE memberId=?";
		Object[] param = new Object[] {id};
		jdbcUtil.setSqlAndParameters(query, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				String grade = rs.getString("grade");
				return (grade.equals("master") ? true : false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}

}
