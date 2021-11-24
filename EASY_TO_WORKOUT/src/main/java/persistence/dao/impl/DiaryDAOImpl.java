package persistence.dao.impl;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import persistence.dao.*;
import persistence.util.*;
import service.dto.*;

public class DiaryDAOImpl implements DiaryDAO {

	private JDBCUtil jdbcUtil = null;
	
	public DiaryDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}
	
	@Override
	public List<DiaryDTO> getAllDiaryList() {
		String allDiaryQuery = "SELECT * FROM diary WHERE private=0";
		jdbcUtil.setSqlAndParameters(allDiaryQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<DiaryDTO> list = new ArrayList<DiaryDTO>();
			
			while (rs.next()) {
				DiaryDTO dto = new DiaryDTO();
				dto.setDiaryId(rs.getInt("diaryId"));
				dto.setTitle(rs.getString("title"));
				dto.setDate(rs.getTimestamp("diaryDate"));
				dto.setWorkTime(rs.getInt("workTime"));
				dto.setContents(rs.getString("contents"));
				dto.setIsPrivate(rs.getInt("private"));
				dto.setAuthor(rs.getString("author"));
				
				list.add(dto);
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
	public List<DiaryDTO> getMyDiaryList(String memberId) {
		String myDiaryQuery = "SELECT * FROM diary WHERE author=?";
		Object[] param = new Object[] {memberId};
		jdbcUtil.setSqlAndParameters(myDiaryQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<DiaryDTO> list = new ArrayList<DiaryDTO>();
			while (rs.next()) {
				DiaryDTO dto = new DiaryDTO();
				dto.setDiaryId(rs.getInt("diaryId"));
				dto.setTitle(rs.getString("title"));
				dto.setDate(rs.getTimestamp("diaryDate"));
				dto.setWorkTime(rs.getInt("workTime"));
				dto.setContents(rs.getString("contents"));
				dto.setIsPrivate(rs.getInt("private"));
				dto.setAuthor(rs.getString("author"));
				
				list.add(dto);
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
	public List<DiaryDTO> getSortedAllDiary(String sortType) {
		String sortQuery = "SELECT * FROM diary WHERE private=0";
		if (sortType.equals("date")) {
			sortQuery += " ORDER BY diaryDate";
		}
		else if (sortType.equals("workTime")) {
			sortQuery += " ORDER BY workTime";
		}
		sortQuery += " DESC";
		jdbcUtil.setSqlAndParameters(sortQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<DiaryDTO> list= new ArrayList<DiaryDTO>();
			while (rs.next()) {
				DiaryDTO dto = new DiaryDTO();
				dto.setDiaryId(rs.getInt("diaryId"));
				dto.setTitle(rs.getString("title"));
				dto.setDate(rs.getTimestamp("diaryDate"));
				dto.setWorkTime(rs.getInt("workTime"));
				dto.setContents(rs.getString("contents"));
				dto.setIsPrivate(rs.getInt("private"));
				dto.setAuthor(rs.getString("author"));
				
				list.add(dto);
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
	public List<DiaryDTO> getSortedMyDiary(String memberId, String sortType) {
		String sortQuery = "SELECT * FROM diary WHERE author=?";
		Object[] param = new Object[] {memberId};
		if (sortType.equals("date")) {
			sortQuery += " ORDER BY diaryDate";
		}
		else if (sortType.equals("workTime")) {
			sortQuery += " ORDER BY workTime";
		}
		sortQuery += " DESC";
		jdbcUtil.setSqlAndParameters(sortQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<DiaryDTO> list= new ArrayList<DiaryDTO>();
			while (rs.next()) {
				DiaryDTO dto = new DiaryDTO();
				dto.setDiaryId(rs.getInt("diaryId"));
				dto.setTitle(rs.getString("title"));
				dto.setDate(rs.getTimestamp("diaryDate"));
				dto.setWorkTime(rs.getInt("workTime"));
				dto.setContents(rs.getString("contents"));
				dto.setIsPrivate(rs.getInt("private"));
				dto.setAuthor(rs.getString("author"));
				
				list.add(dto);
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
	public DiaryDTO getDiaryById(String id) {
		String searchQuery = "SELECT * FROM diary WHERE diaryId=?";
		Object[] param = new Object[] {id};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		DiaryDTO dto = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				dto = new DiaryDTO();
				dto.setDiaryId(rs.getInt("diaryId"));
				dto.setTitle(rs.getString("title"));
				dto.setDate(rs.getTimestamp("diaryDate"));
				dto.setWorkTime(rs.getInt("workTime"));
				dto.setContents(rs.getString("contents"));
				dto.setIsPrivate(rs.getInt("private"));
				dto.setAuthor(rs.getString("author"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return dto;
	}

	@Override
	public List<DiaryDTO> getAllDiaryByTitle(String title) {
		String searchQuery = "SELECT * FROM diary WHERE private=0 and title LIKE ?";
		Object[] param = new Object[] {"%" + title + "%"};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<DiaryDTO> list= new ArrayList<DiaryDTO>();
			while (rs.next()) {
				DiaryDTO dto = new DiaryDTO();
				dto.setDiaryId(rs.getInt("diaryId"));
				dto.setTitle(rs.getString("title"));
				dto.setDate(rs.getTimestamp("diaryDate"));
				dto.setWorkTime(rs.getInt("workTime"));
				dto.setContents(rs.getString("contents"));
				dto.setIsPrivate(rs.getInt("private"));
				dto.setAuthor(rs.getString("author"));
				
				list.add(dto);
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
	public List<DiaryDTO> getMyDiaryByTitle(String memberId, String title) {
		String searchQuery = "SELECT * FROM diary WHERE author=? and title LIKE ?";
		Object[] param = new Object[] {memberId, "%" + title + "%"};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<DiaryDTO> list= new ArrayList<DiaryDTO>();
			while (rs.next()) {
				DiaryDTO dto = new DiaryDTO();
				dto.setDiaryId(rs.getInt("diaryId"));
				dto.setTitle(rs.getString("title"));
				dto.setDate(rs.getTimestamp("diaryDate"));
				dto.setWorkTime(rs.getInt("workTime"));
				dto.setContents(rs.getString("contents"));
				dto.setIsPrivate(rs.getInt("private"));
				dto.setAuthor(rs.getString("author"));
				
				list.add(dto);
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
	public boolean existingAllDiary(String title) {
		String query = "SELECT count(*) FROM diary WHERE private=0 and title LIKE ?";
		Object[] param = new Object[] {"%" + title + "%"};
		jdbcUtil.setSqlAndParameters(query, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 0 ? false : true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}

	@Override
	public boolean existingMyDiary(String memberId, String title) {
		String query = "SELECT count(*) FROM diary WHERE author=? and title LIKE ?";
		Object[] param = new Object[] {memberId, "%" + title + "%"};
		jdbcUtil.setSqlAndParameters(query, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 0 ? false : true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}

	@Override
	public int insertDiary(DiaryDTO diary) {
		int result = 0;
		String insertQuery = "INSERT INTO "
				+ "diary (diaryId, title, diaryDate, workTime, contents, private, author) "
				+ "VALUES (DiaryIdSeq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] {diary.getTitle(), diary.getDate(), diary.getWorkTime(), 
						diary.getContents(), diary.getIsPrivate(), diary.getAuthor()};
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

	@Override
	public int updateDiary(DiaryDTO diary) {
		int result = 0;
		String updateQuery = "UPDATE diary "
				+ "SET title=?, diaryDate=?, workTime=?, contents=?, private=?, author=? "
				+ "WHERE diaryId=?";
		Object[] param = new Object[] {diary.getTitle(), diary.getDate(), 
						diary.getWorkTime(), diary.getContents(), 
						diary.getIsPrivate(), diary.getAuthor(), 
						diary.getDiaryId()};
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

	@Override
	public int deleteDiary(String id) {
		int result = 0;
		String deleteQuery = "DELETE FROM diary WHERE diaryId=?";
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
	
}
