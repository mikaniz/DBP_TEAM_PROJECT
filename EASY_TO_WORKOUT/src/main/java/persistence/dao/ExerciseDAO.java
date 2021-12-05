package persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import service.dto.Exercise;
import persistence.util.JDBCUtil;

public class ExerciseDAO {

	private JDBCUtil jdbcUtil = null;
	
	private static String query = "SELECT EXERCISE.EXERCISEID AS EXERCISE_ID, " +
	         "EXERCISE.NAME AS EXERCISE_NAME, " +
	         "EXERCISE.PART AS EXERCISE_PART, " +
	         "EXERCISE.METHOD AS EXERCISE_METHOD ";
	
	public ExerciseDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	public List<Exercise> getExerciseList() {
		String allQuery = query +
			   "FROM EXERCISE ORDER BY EXERCISE.EXERCISEID ASC ";		
		jdbcUtil.setSqlAndParameters(allQuery, null);		// JDBCUtil 에 query 설정

		try { 
				ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
				List<Exercise> list = new ArrayList<Exercise>();		// ExerciseDTO 객체들을 담기위한 list 객체
				while (rs.next()) {	
					Exercise dto = new Exercise();		// 하나의 ExerciseDTO 객체 생성 후 정보 설정
					dto.setExerciseId(rs.getInt("EXERCISE_ID"));
					dto.setName(rs.getString("EXERCISE_NAME"));
					dto.setPart(rs.getString("EXERCISE_PART"));
					dto.setMethod(rs.getString("EXERCISE_METHOD"));
					list.add(dto);		// list 객체에 정보를 설정한 ExerciseDTO 객체 저장
				}
				return list;		// 운동을 저장한 dto들의 목록을 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return null;
	}
	
	public List<Exercise> getExerciseByName(String name) { // 운동명으로 운동 검색
		// TODO Auto-generated method stub
		String searchQuery = query +
				"FROM EXERCISE " +
		        "WHERE NAME LIKE ? ";
		
		Object[] param = new Object[] {"%" + name + "%"}; // 운동명으로 검색 후 정렬 조건 설정
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			List<Exercise> list = new ArrayList<Exercise>();
			
			while (rs.next()) {
				Exercise dto = new Exercise();		// 하나의 ExerciseDTO 객체 생성 후 정보 설정
				dto.setExerciseId(rs.getInt("EXERCISE_ID"));
				dto.setName(rs.getString("EXERCISE_NAME"));
				dto.setPart(rs.getString("EXERCISE_PART"));
				dto.setMethod(rs.getString("EXERCISE_METHOD"));
				
				list.add(dto); // 리스트에 DTO 객체 저장
			}
			return list; // 운동 정보를 저장한 DTO 객체들의 리스트 반환
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		}
		
		return null;
	}
	
	public boolean existingExercise(String name) {
		String sql = "SELECT count(*) FROM EXERCISE WHERE NAME LIKE ? ";   
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {"%" + name + "%"});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 0 ? false : true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}

	public Exercise getExerciseById(int exerciseId) {
		String searchQuery = query +
		        "FROM EXERCISE " +
		        "WHERE EXERCISEID = ? ";
		   	 
		Object[] param = new Object[] { exerciseId };
		
		jdbcUtil.setSqlAndParameters(searchQuery, param);

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
			Exercise dto = null;
			if (rs.next()) {						// 찾은 운동의 정보를 ExerciseDTO 객체에 설정
				dto = new Exercise();
				dto.setExerciseId(rs.getInt("EXERCISE_ID"));
				dto.setName(rs.getString("EXERCISE_NAME"));
				dto.setPart(rs.getString("EXERCISE_PART"));
				dto.setMethod(rs.getString("EXERCISE_METHOD"));
			}
			return dto;				// 찾은 운동의 정보를 담고 있는 ExerciseDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}

}
