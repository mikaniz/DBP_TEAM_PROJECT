package persistence.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.ClubDAO;
import persistence.util.JDBCUtil;
import service.dto.ClubDTO;

public class ClubDAOImpl implements ClubDAO {
	private JDBCUtil jdbcUtil = null;	// JDBCUtil 객체 이용
	
	// 모임 기본 정보를 포함하는 SELCT문
	private static String query = "SELECT CLUB.CLUBID AS CLUBID, " + 
			"CLUB.SIGNUP AS SIGNUP, " + 
			"CLUB.OPENCYCLE AS OPENCYCLE, " + 
			"CLUB.CLUBINTRO AS CLUBINTRO, " + 
			"CLUB.CLUBNAME AS CLUBNAME, " + 
			"CLUB.CLUBMASTER AS CLUBMASTER ";
	
	// 생성자
	public ClubDAOImpl() {
		jdbcUtil = new JDBCUtil(); // ClubDAOImpl 객체 생성 시 JDBC 객체 생성
	}
	
	@Override
	public List<ClubDTO> getClubList() { // 모임 목록
		// TODO Auto-generated method stub
		String allQuery = query + "FROM CLUB ORDER BY CLUBID";
		
		// JDBCUtil에 query 설정
		jdbcUtil.setSql(allQuery);
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			List<ClubDTO> list = new ArrayList<ClubDTO>(); // DTO 객체 담기 위한 리스트 생성
			
			while (rs.next()) {
				ClubDTO dto = new ClubDTO(); // ClubDTO 객체 생성 후 검색 결과 저장
				dto.setClubId(rs.getInt("CLUBID"));
				dto.setSignUp(rs.getString("SIGNUP"));
				dto.setOpenCycle(rs.getString("OPENCYCLE"));
				dto.setOpenCycle(rs.getString("CLUBINTRO"));
				dto.setOpenCycle(rs.getString("CLUBNAME"));
				dto.setOpenCycle(rs.getString("CLUBMASTER"));
				
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
	public ClubDTO getClubById(int clubId) { // 모임 id를 통한 모임 상세정보
		// TODO Auto-generated method stub
		String searchQuery = query + "FROM CLUB WHERE CLUBID = ?";
		
		jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] {clubId};		// 모임을 찾기 위한 조건으로 id 설정
		jdbcUtil.setParameters(param);				// JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			ClubDTO club = null;
			
			if (rs.next()) {
				club = new ClubDTO(); // clubDTO 객체를 생성하여 모임 정보 저장
				club.setClubId(rs.getInt("CLUBID"));
				club.setSignUp(rs.getString("SIGNUP"));
				club.setOpenCycle(rs.getString("OPENCYCLE"));
				club.setOpenCycle(rs.getString("CLUBINTRO"));
				club.setOpenCycle(rs.getString("CLUBNAME"));
				club.setOpenCycle(rs.getString("CLUBMASTER"));
			}
			return club; // 모임 정보를 담고 있는 ClubDTO 객체 반환
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		}
		
		return null;
	}

	@Override
	public List<ClubDTO> getClubByName(String clubName) { // 모임 이름으로 모임 검색
		// TODO Auto-generated method stub
		String searchQuery = query + "FROM CLUB WHERE CLUBNAME = ?";
		
		jdbcUtil.setSql(searchQuery);	// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] {clubName}; // 모임을 찾기 위한 조건으로 이름을 설정
		jdbcUtil.setParameters(param); // JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			List<ClubDTO> list = new ArrayList<ClubDTO>();
			
			while (rs.next()) {
				ClubDTO dto = new ClubDTO(); // ClubDTO 객체 생성 후 검색 결과 저장
				dto.setClubId(rs.getInt("CLUBID"));
				dto.setSignUp(rs.getString("SIGNUP"));
				dto.setOpenCycle(rs.getString("OPENCYCLE"));
				dto.setOpenCycle(rs.getString("CLUBINTRO"));
				dto.setOpenCycle(rs.getString("CLUBNAME"));
				dto.setOpenCycle(rs.getString("CLUBMASTER"));
				
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
	public int insertClub(ClubDTO club) { // 모임 추가 (개설)
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public int deleteClub(int clubId) { // 모임 삭제
		// TODO Auto-generated method stub
		return 0;
	}

}
