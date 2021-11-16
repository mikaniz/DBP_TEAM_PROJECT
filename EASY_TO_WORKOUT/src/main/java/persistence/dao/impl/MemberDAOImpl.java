package persistence.dao.impl;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import persistence.dao.*;
import persistence.util.*;
import service.dto.*;

public class MemberDAOImpl implements MemberDAO {

	private JDBCUtil jdbcUtil = null;
	
	public MemberDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}
	
	@Override
	public List<MemberDTO> getMemberList() {
		String allQuery = "SELECT * FROM member";
		jdbcUtil.setSql(allQuery);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<MemberDTO> list = new ArrayList<MemberDTO>();
			
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
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

	@Override
	public MemberDTO getMemberById(String id) {
		String searchQuery = "SELECT * FROM member WHERE memberId=?";
		jdbcUtil.setSql(searchQuery);
		Object[] param = new Object[] {id};
		jdbcUtil.setParameters(param);
		MemberDTO dto = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				dto = new MemberDTO();
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

	@Override
	public int insertMember(MemberDTO member) {
		int result = 0;
		String insertQuery = "INSERT INTO "
				+ "member (memberId, pw, name, phone, email, grade) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		jdbcUtil.setSql(insertQuery);
		Object[] param = new Object[] {member.getId(), member.getPw(), member.getName(), 
						member.getPhone(), member.getEmail(), member.getGrade()};
		jdbcUtil.setParameters(param);
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

	@Override
	public int updateMember(MemberDTO member) {
		int result = 0;
		String updateQuery = "UPDATE member "
				+ "SET pw=?, name=?, phone=?, email=?, grade=? "
				+ "WHERE memberId=?";
		jdbcUtil.setSql(updateQuery);
		Object[] param = new Object[] {member.getPw(), member.getName(), member.getPhone(), 
						member.getEmail(), member.getGrade(), member.getId()};
		jdbcUtil.setParameters(param);
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

	@Override
	public int deleteMember(String id) {
		int result = 0;
		String deleteQuery = "DELETE FROM member WHERE memberId=?";
		jdbcUtil.setSql(deleteQuery);
		Object[] param = new Object[] {id};
		jdbcUtil.setParameters(param);
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

	@Override
	public boolean existingMember(String id) {
		String query = "SELECT count(*) FROM member WHERE memberId=?";
		jdbcUtil.setSql(query);
		Object[] param = new Object[] {id};
		jdbcUtil.setParameters(param);
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

	@Override
	public boolean isMaster(String id) {
		String query = "SELECT grade FROM member WHERE memberId=?";
		jdbcUtil.setSql(query);
		Object[] param = new Object[] {id};
		jdbcUtil.setParameters(param);
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
