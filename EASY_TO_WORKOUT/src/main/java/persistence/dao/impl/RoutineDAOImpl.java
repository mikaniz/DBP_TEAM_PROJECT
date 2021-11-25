package persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import service.dto.RoutineDTO;
import persistence.dao.RoutineDAO;
import persistence.util.JDBCUtil;

public class RoutineDAOImpl implements RoutineDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	private static String query = "SELECT ROUTINE.ROUTINEID AS ROUTINE_ID, " +
	         "ROUTINE.RNAME AS ROUTINE_NAME, " +
	         "ROUTINE.RTIME AS ROUTINE_TIME, " +
	         "ROUTINE.DIFFICULTY AS ROUTINE_DIFFICULTY, " +
	         "ROUTINE.RTYPE AS ROUTINE_TYPE, " +
	         "ROUTINE.PART AS ROUTINE_PART, " +
	         "ROUTINE.ROUTINECREATER AS ROUTINE_CREATER ";
	
	public RoutineDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}

	@Override
	public List<RoutineDTO> getRoutineList() {
		String allQuery = query + "FROM ROUTINE ORDER BY ROUTINE_ID ASC ";		
		jdbcUtil.setSqlAndParameters(allQuery, null);		// JDBCUtil 에 query 설정

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
	public List<RoutineDTO> getRoutineListByPublic() {
		String searchQuery = query +
			        "FROM ROUTINE " +
			        "WHERE RTYPE = '0' ";
			   	 
		jdbcUtil.setSqlAndParameters(searchQuery, null);				// JDBCUtil 에 query 문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
			List<RoutineDTO> list = new ArrayList<RoutineDTO>();	// RoutineDTO 객체들을 담기위한 list 객체
			
			while (rs.next()) {						// 하나의 RoutineDTO 객체 생성 후 정보 설정
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
	public List<RoutineDTO> getRoutineListByPersonal() {
		String searchQuery = query + 
		        "FROM ROUTINE " +
		        "WHERE RTYPE = '1' ";
		   	 
		jdbcUtil.setSqlAndParameters(searchQuery, null);			// JDBCUtil 에 query 문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
			List<RoutineDTO> list = new ArrayList<RoutineDTO>();	// RoutineDTO 객체들을 담기위한 list 객체
			
			while (rs.next()) {						// 하나의 RoutineDTO 객체 생성 후 정보 설정
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
	public List<RoutineDTO> getRoutineByName(String rName) { // 루틴 이름으로 모임 검색
		// TODO Auto-generated method stub
		String searchQuery = query +
				"FROM ROUTINE " +
		        "WHERE RNAME = ? ";
		
		Object[] param = new Object[] {rName}; // 루틴 이름으로 검색 후 정렬 조건 설정
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			List<RoutineDTO> list = new ArrayList<RoutineDTO>();
			
			while (rs.next()) {
				RoutineDTO dto = new RoutineDTO(); // RoutineDTO 객체 생성 후 검색 결과 저장
				dto.setRoutineId(rs.getInt("ROUTINE_ID"));
				dto.setrName(rs.getString("ROUTINE_NAME"));
				dto.setrTime(rs.getInt("ROUTINE_TIME"));
				dto.setDifficulty(rs.getInt("ROUTINE_DIFFICULTY"));
				dto.setrType(rs.getString("ROUTINE_TYPE"));
				dto.setPart(rs.getString("ROUTINE_PART"));
				dto.setRoutineCreater(rs.getString("ROUTINE_CREATER"));
				
				list.add(dto); // 리스트에 DTO 객체 저장
			}
			return list; // 루틴 정보를 저장한 DTO 객체들의 리스트 반환
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		}
		
		return null;
	}

	@Override
	public int insertRoutine(RoutineDTO routine) {
		int result = 0;
		String insertQuery = "INSERT INTO "
				+ "ROUTINE (ROUTINEID, RNAME, RTIME, DIFFICULTY, RTYPE, RPART, ROUTINECREATER) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] {routine.getRoutineId(), routine.getrName(), 
				routine.getrTime(), routine.getDifficulty(), 
				routine.getrType(), routine.getPart(), 
				routine.getRoutineCreater()};
		
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
	public int updateRoutine(RoutineDTO routine) {
		int result = 0;
		String updateQuery = "UPDATE ROUTINE "
				+ "SET RNAME=?, RTIME=?, DIFFICULTY=?, RTYPE=?, RPART=?, ROUTINECREATER=? "
				+ "WHERE ROUTINEID=?";
		
		Object[] param = new Object[] {routine.getrName(), 
				routine.getrTime(), routine.getDifficulty(), 
				routine.getrType(), routine.getPart(), 
				routine.getRoutineCreater(), routine.getRoutineId()};
		
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
	public int deleteRoutine(int routineId) {
		String deleteQuery = "DELETE FROM ROUTINE WHERE ROUTINEID = ?";
		
		Object[] param = new Object[] {routineId};
		
		jdbcUtil.setSqlAndParameters(deleteQuery, param);
		
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
	public RoutineDTO getRoutineById(int routineId) {
		String searchQuery = query +
		        "FROM ROUTINE " +
		        "WHERE ROUTINE.ROUTINEID = ? ";
	
		Object[] param = new Object[] { routineId };
		
		jdbcUtil.setSqlAndParameters(searchQuery, param);

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

	public boolean existingRoutine(String rName) {
		String sql = "SELECT count(*) FROM ROUTINE WHERE RNAME=?";   
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {rName});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
}
