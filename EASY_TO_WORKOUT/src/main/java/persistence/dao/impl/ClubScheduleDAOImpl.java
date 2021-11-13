package persistence.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.ClubScheduleDAO;
import persistence.util.JDBCUtil;
import service.dto.ClubScheduleDTO;

public class ClubScheduleDAOImpl implements ClubScheduleDAO {
	private JDBCUtil jdbcUtil = null;	// JDBCUtil 객체 이용
	
	// 모임 스케줄 기본 정보를 포함하는 SELCT문
	private static String query = "SELECT CLUBSCHEDULE.SCHEDULEID AS SCHEDULEID, " +
				"CLUBSCHEDULE.CLUBID AS CLUBID, " +
				"CLUBSCHEDULE.CONTACTADDRESS AS CONTACTADDRESS, " + 
				"CLUBSCHEDULE.NOTICE AS NOTICE, " + 
				"CLUBSCHEDULE.CREATIONDATE AS CREATIONDATE";
	
	// 생성자
	public ClubScheduleDAOImpl() {
		jdbcUtil = new JDBCUtil(); // ClubScheduleDAOImpl 객체 생성 시 JDBC 객체 생성
	}

	@Override
	public List<ClubScheduleDTO> getClubScheduleList() {
		// TODO Auto-generated method stub
		String allQuery = query + "FROM CLUBSCHEDULE ORDER BY CLUBID";
		
		// JDBCUtil에 query 설정
		jdbcUtil.setSql(allQuery);
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			List<ClubScheduleDTO> list = new ArrayList<ClubScheduleDTO>(); // DTO 객체 담기 위한 리스트 생성
			
			while (rs.next()) {
				ClubScheduleDTO dto = new ClubScheduleDTO(); // ClubDTO 객체 생성 후 검색 결과 저장
				dto.setClubId(rs.getInt("CLUBID"));
				dto.setScheduleId(rs.getInt("SCHEDULEID"));
				dto.setContactAddress(rs.getString("CONTACTADDRESS"));
				dto.setNotice(rs.getString("NOTICE"));
				dto.setCreationDate(rs.getString("CREATIONDATE"));
				
				list.add(dto); // 리스트에 DTO 객체 저장
			}
			return list; // 모임 정보를 저장한 DTO 객체들의 리스트 반환
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		}
		
		return null;
	}

	@Override
	public ClubScheduleDTO getClubScheduleById(int clubScheduleId, int clubId) {
		// TODO Auto-generated method stub
		String searchQuery = query + "FROM CLUBSCHEDULE WHERE CLUBSCHEDULEID = ? AND CLUBID = ?";
		
		jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] {clubScheduleId, clubId};		// 모임을 찾기 위한 조건으로 id 설정
		jdbcUtil.setParameters(param);				// JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			ClubScheduleDTO schedule = null;
			
			if (rs.next()) {
				schedule = new ClubScheduleDTO(); // clubDTO 객체를 생성하여 모임 정보 저장
				schedule.setClubId(rs.getInt("CLUBID"));
				schedule.setScheduleId(rs.getInt("SCHEDULEID"));
				schedule.setContactAddress(rs.getString("CONTACTADDRESS"));
				schedule.setNotice(rs.getString("NOTICE"));
				schedule.setCreationDate(rs.getString("CREATIONDATE"));
			}
			return schedule; // 모임 정보를 담고 있는 ClubDTO 객체 반환
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		}
		
		return null;
	}

	@Override
	public int insertClubSchedule(ClubScheduleDTO clubSchedule) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClubSchedule(int clubId, int clubScheduleId) {
		// TODO Auto-generated method stub
		return 0;
	}


}
