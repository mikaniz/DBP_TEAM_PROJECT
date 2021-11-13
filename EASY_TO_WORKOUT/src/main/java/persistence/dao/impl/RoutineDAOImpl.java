package persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import service.dto.*;
import persistence.DAOFactory;
import persistence.dao.*;
import persistence.util.JDBCUtil;

public class RoutineDAOImpl implements RoutineDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	private static String query = "SELECT ROUTINE.ROUTINEID AS ROUTINE_ID, " +
	         "ROUTINE.RNAME AS ROUTINE_NAME, " +
	         "ROUTINE.RTIME AS ROUTINE_TIME, " +
	         "ROUTINE.DIFFICULTY AS ROUTINE_DIFFICULTY, " +
	         "ROUTINE.RTYPE AS ROUTINE_TYPE, " +
	         "ROUTINE.PART AS ROUTINE_PART ";
	
	public RoutineDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}

	@Override
	public List<RoutineDTO> getRoutineList() {
		String allQuery = query + ", " + "ROUTINE.ROUTINECREATER AS ROUTINE_CREATER " +
		    "FROM ROUTINE ORDER BY ROUTINE.ROUTINEID ASC ";		
		jdbcUtil.setSql(allQuery);		// JDBCUtil 에 query 설정

		try { 
				ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
				List<RoutineDTO> list = new ArrayList<RoutineDTO>();		// RoutineDTO 객체들을 담기위한 list 객체
				while (rs.next()) {	
					RoutineDTO dto = new RoutineDTO();		// 하나의 RoutineDTO 객체 생성 후 정보 설정
					dto.setRoutineId(rs.getInt("ROUTINE_ID"));
					dto.setrName(rs.getString("ROUTINE_NAME"));
					dto.setrTime(rs.getInt("ROUTINE_TIME"));
					dto.setDifficulty(rs.getInt("ROUTINE_DIFFICULTY"));
					dto.setrType(rs.getString("ROUTINE_TYPE"));
					dto.setPart(rs.getString("ROUTINE_PART"));
					dto.setRoutineCreater(rs.getString("ROUTINE_CREATER"));
					list.add(dto);		// list 객체에 정보를 설정한 RoutineDTO 객체 저장
				}
				return list;		// 루틴을 저장한 dto들의 목록을 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return null;
	}

	@Override
	public List<RoutineDTO> getRoutineListByPublic(String rType) {
		String searchQuery = query + ", " + "ROUTINE.ROUTINECREATER AS ROUTINE_CREATER " +
			        "FROM ROUTINE " +
			        "WHERE ROUTINE.RTYPE = 0 ";
			   	 
		jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
			List<RoutineDTO> list = new ArrayList<RoutineDTO>();	// RoutineDTO 객체들을 담기위한 list 객체
			if (rs.next()) {						// 하나의 RoutineDTO 객체 생성 후 정보 설정
				RoutineDTO dto = new RoutineDTO();
				dto.setRoutineId(rs.getInt("ROUTINE_ID"));
				dto.setrName(rs.getString("ROUTINE_NAME"));
				dto.setrTime(rs.getInt("ROUTINE_TIME"));
				dto.setDifficulty(rs.getInt("ROUTINE_DIFFICULTY"));
				dto.setrType(rs.getString("ROUTINE_TYPE"));
				dto.setPart(rs.getString("ROUTINE_PART"));
				dto.setRoutineCreater(rs.getString("ROUTINE_CREATER"));
				list.add(dto);	// list 객체에 정보를 설정한 RoutineDTO 객체 저장
			}
			return list;				// 퍼블릭 루틴의 정보를 담고 있는 RoutineDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}

	@Override
	public List<RoutineDTO> getRoutineListByPersonal(String rType) {
		String searchQuery = query + ", " + "ROUTINE.ROUTINECREATER AS ROUTINE_CREATER " +
		        "FROM ROUTINE " +
		        "WHERE ROUTINE.RTYPE = 1 ";
		   	 
		jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
			List<RoutineDTO> list = new ArrayList<RoutineDTO>();	// RoutineDTO 객체들을 담기위한 list 객체
			if (rs.next()) {						// 하나의 RoutineDTO 객체 생성 후 정보 설정
				RoutineDTO dto = new RoutineDTO();
				dto.setRoutineId(rs.getInt("ROUTINE_ID"));
				dto.setrName(rs.getString("ROUTINE_NAME"));
				dto.setrTime(rs.getInt("ROUTINE_TIME"));
				dto.setDifficulty(rs.getInt("ROUTINE_DIFFICULTY"));
				dto.setrType(rs.getString("ROUTINE_TYPE"));
				dto.setPart(rs.getString("ROUTINE_PART"));
				dto.setRoutineCreater(rs.getString("ROUTINE_CREATER"));
				list.add(dto);	// list 객체에 정보를 설정한 RoutineDTO 객체 저장
			}
			return list;				// 퍼스널 루틴의 정보를 담고 있는 RoutineDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}

	@Override
	public int insertRoutine(RoutineDTO routine) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRoutine(RoutineDTO routine) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRoutine(int routineId) {
		String deleteQuery = "DELETE FROM ROUTINE WHERE ROUTINEID = ?";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] {routineId};
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete 문 실행
			return result;						// delete 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return 0;
	}

	@Override
	public RoutineDTO getRoutineByName(String rName) {
		String searchQuery = query + ", " + "ROUTINE.ROUTINECREATER AS ROUTINE_CREATER " +
		        "FROM ROUTINE " +
		        "WHERE ROUTINE.RNAME = ? ";
		   	 
		jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { rName };
		jdbcUtil.setParameters(param);

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
			RoutineDTO dto = null;
			if (rs.next()) {						// 찾은 루틴의 정보를 RoutineDTO 객체에 설정
				dto = new RoutineDTO();
				dto.setRoutineId(rs.getInt("ROUTINE_ID"));
				dto.setrName(rs.getString("ROUTINE_NAME"));
				dto.setrTime(rs.getInt("ROUTINE_TIME"));
				dto.setDifficulty(rs.getInt("ROUTINE_DIFFICULTY"));
				dto.setrType(rs.getString("ROUTINE_TYPE"));
				dto.setPart(rs.getString("ROUTINE_PART"));
				dto.setRoutineCreater(rs.getString("ROUTINE_CREATER"));
			}
			return dto;				// 찾은 루틴의 정보를 담고 있는 RoutineDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}

}
