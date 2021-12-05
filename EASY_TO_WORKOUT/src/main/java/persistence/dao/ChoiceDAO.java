package persistence.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import persistence.util.*;
import service.dto.Choice;

public class ChoiceDAO {
private JDBCUtil jdbcUtil = null;
	
	public ChoiceDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public List<Choice> getChoiceList() {
		String allQuery = "SELECT * FROM choice";
		jdbcUtil.setSqlAndParameters(allQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Choice> list = new ArrayList<Choice>();
			
			while (rs.next()) {
				Choice dto = new Choice();
				dto.setRoutineId(rs.getInt("routineId"));
				dto.setExerciseId(rs.getInt("exerciseId"));
				dto.setSequence(rs.getInt("sequence"));
				dto.setRepetition(rs.getInt("repetition"));
				
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

	public List<Choice> getChoiceListByRoutineId(int routineId) {
		String searchListQuery = "SELECT * FROM choice WHERE routineId=?";
		Object[] param = new Object[] {routineId};
		jdbcUtil.setSqlAndParameters(searchListQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Choice> list = new ArrayList<Choice>();
			
			while (rs.next()) {
				Choice dto = new Choice();
				dto.setRoutineId(rs.getInt("routineId"));
				dto.setExerciseId(rs.getInt("exerciseId"));
				dto.setSequence(rs.getInt("sequence"));
				dto.setRepetition(rs.getInt("repetition"));
				
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

	public Choice getChoiceByRoutineIdAndExerciseId(int routineId, int exerciseId) {
		String searchQuery = "SELECT * FROM choice WHERE routineId=? and exerciseId=?";
		Object[] param = new Object[] {routineId, exerciseId};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		Choice dto = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				dto = new Choice();
				dto.setRoutineId(rs.getInt("routineId"));
				dto.setExerciseId(rs.getInt("exerciseId"));
				dto.setSequence(rs.getInt("sequence"));
				dto.setRepetition(rs.getInt("repetition"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return dto;
	}

	public int insertChoice(Choice choice) {
		int result = 0;
		String insertQuery = "INSERT INTO "
				+ "CHOICE (ROUTINEID, EXERCISEID, SEQUENCE, REPETITION) "
				+ "VALUES (?, ?, ?, ?)";
		Object[] param = new Object[] {choice.getRoutineId(), choice.getExerciseId(), choice.getSequence(), choice.getRepetition()};
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

	public int updateChoice(Choice choice) {
		int result = 0;
		String updateQuery = "UPDATE choice "
				+ "SET routineId=?, exerciseId=?, sequence=?, repetition=? "
				+ "WHERE routineId=?";
		Object[] param = new Object[] {choice.getRoutineId(), choice.getExerciseId(), choice.getSequence(), choice.getRepetition()};
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

	public int deleteChoice(int routineId) {
		int result = 0;
		String deleteQuery = "DELETE FROM choice WHERE routineId=?";
		Object[] param = new Object[] {routineId};
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
	
	@SuppressWarnings("null")
	public int[] getSequenceList(int routineId) {
		String allQuery = "SELECT sequence FROM choice WHERE routineId = ? ORDER BY exerciseId ASC";
		Object[] param = new Object[] {routineId};
		jdbcUtil.setSqlAndParameters(allQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			int[] list = null;
			int i = 0;
			
			while (rs.next()) {
				list[i] = rs.getInt("sequence");
				i++;
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	@SuppressWarnings("null")
	public int[] getRepetitionList(int routineId) {
		String allQuery = "SELECT repetition FROM choice WHERE routineId = ? ORDER BY exerciseId ASC";
		Object[] param = new Object[] {routineId};
		jdbcUtil.setSqlAndParameters(allQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			int[] list = null;
			int i = 0;
			
			while (rs.next()) {
				list[i] = rs.getInt("repetition");
				i++;
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
