package persistence.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import persistence.util.*;
import service.dto.*;

public class DiaryDAO {

	private JDBCUtil jdbcUtil = null;
	
	public DiaryDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	public List<Diary> getAllDiaryList() {
		String allDiaryQuery = "SELECT * FROM diary WHERE private=0";
		jdbcUtil.setSqlAndParameters(allDiaryQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Diary> list = new ArrayList<Diary>();
			
			while (rs.next()) {
				Diary dto = new Diary();
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

	public List<Diary> getMyDiaryList(String memberId) {
		String myDiaryQuery = "SELECT * FROM diary WHERE author=?";
		Object[] param = new Object[] {memberId};
		jdbcUtil.setSqlAndParameters(myDiaryQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Diary> list = new ArrayList<Diary>();
			while (rs.next()) {
				Diary dto = new Diary();
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

	public List<Diary> getSortedAllDiary(String sortType) {
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
			List<Diary> list= new ArrayList<Diary>();
			while (rs.next()) {
				Diary dto = new Diary();
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

	public List<Diary> getSortedMyDiary(String memberId, String sortType) {
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
			List<Diary> list= new ArrayList<Diary>();
			while (rs.next()) {
				Diary dto = new Diary();
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

	public Diary getDiaryById(int id) {
		String searchQuery = "SELECT * FROM diary WHERE diaryId=?";
		Object[] param = new Object[] {id};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		Diary dto = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				dto = new Diary();
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

	public List<Diary> getAllDiaryByTitle(String title) {
		String searchQuery = "SELECT * FROM diary WHERE private=0 and title LIKE ?";
		Object[] param = new Object[] {"%" + title + "%"};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Diary> list= new ArrayList<Diary>();
			while (rs.next()) {
				Diary dto = new Diary();
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

	public List<Diary> getMyDiaryByTitle(String memberId, String title) {
		String searchQuery = "SELECT * FROM diary WHERE author=? and title LIKE ?";
		Object[] param = new Object[] {memberId, "%" + title + "%"};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Diary> list= new ArrayList<Diary>();
			while (rs.next()) {
				Diary dto = new Diary();
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

	public int insertDiary(Diary diary) {
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

	public int updateDiary(Diary diary) {
		int result = 0;
		String updateQuery = "UPDATE diary "
				+ "SET title=?, workTime=?, contents=?, private=? "
				+ "WHERE diaryId=?";
		Object[] param = new Object[] {diary.getTitle(), diary.getWorkTime(),
						diary.getContents(), diary.getIsPrivate(), diary.getDiaryId()};
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

	public int deleteDiary(int id) {
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
