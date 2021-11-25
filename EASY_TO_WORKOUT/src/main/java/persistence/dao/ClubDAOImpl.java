package persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.ClubDAO;
import persistence.util.JDBCUtil;
import service.dto.ClubDTO;
import service.exception.ExistingClubException;

public class ClubDAOImpl implements ClubDAO {
	private JDBCUtil jdbcUtil = null;	// JDBCUtil 객체 이용
	
	// 모임 기본 정보를 포함하는 SELCT문
	private static String query = "SELECT CLUB.CLUBID AS CLUBID, " + 
			"CLUB.SIGNUP AS SIGNUP, " + 
			"CLUB.OPENCYCLE AS OPENCYCLE, " + 
			"CLUB.CLUBINTRO AS CLUBINTRO, " + 
			"CLUB.CLUBNAME AS CLUBNAME, " + 
			"CLUB.CLUBMASTER AS CLUBMASTER";
	
	// 생성자
	public ClubDAOImpl() {
		jdbcUtil = new JDBCUtil(); // ClubDAOImpl 객체 생성 시 JDBC 객체 생성
	}
	
	@Override
	public List<ClubDTO> getClubList() throws SQLException { // 모임 목록
		// TODO Auto-generated method stub
		String allQuery = query + ", COUNT(MEMBERSHIP.CLUBID) AS COUNTMEMBER " + 
				"FROM CLUB LEFT OUTER JOIN MEMBERSHIP ON CLUB.CLUBID = MEMBERSHIP.CLUBID " + 
				"GROUP BY CLUB.CLUBID, CLUB.SIGNUP, CLUB.OPENCYCLE, CLUB.CLUBINTRO, CLUB.CLUBNAME, CLUB.CLUBMASTER ";
		
		// JDBCUtil에 query 설정
		jdbcUtil.setSqlAndParameters(allQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			List<ClubDTO> list = new ArrayList<ClubDTO>(); // DTO 객체 담기 위한 리스트 생성
			
			while (rs.next()) {
				ClubDTO dto = new ClubDTO(); // ClubDTO 객체 생성 후 검색 결과 저장
				dto.setClubId(rs.getInt("CLUBID"));
				dto.setSignUp(rs.getString("SIGNUP"));
				dto.setOpenCycle(rs.getString("OPENCYCLE"));
				dto.setClubIntro(rs.getString("CLUBINTRO"));
				dto.setClubName(rs.getString("CLUBNAME"));
				dto.setClubMaster(rs.getString("CLUBMASTER"));
				dto.setCountClub(rs.getInt("COUNTMEMBER"));
				
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
		String searchQuery = query + " FROM CLUB WHERE CLUBID = ?";
		
		Object[] param = new Object[] {clubId};		// 모임을 찾기 위한 조건으로 id 설정
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			ClubDTO club = null;
			
			if (rs.next()) {
				club = new ClubDTO(); // clubDTO 객체를 생성하여 모임 정보 저장
				club.setClubId(rs.getInt("CLUBID"));
				club.setSignUp(rs.getString("SIGNUP"));
				club.setOpenCycle(rs.getString("OPENCYCLE"));
				club.setClubIntro(rs.getString("CLUBINTRO"));
				club.setClubName(rs.getString("CLUBNAME"));
				club.setClubMaster(rs.getString("CLUBMASTER"));
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
	public List<ClubDTO> getClubByName(String clubName) throws ExistingClubException, SQLException { // 모임 이름으로 모임 검색
		// TODO Auto-generated method stub
		String searchQuery = query + ", COUNT(MEMBERSHIP.CLUBID) AS COUNTMEMBER " + 
				"FROM CLUB LEFT OUTER JOIN MEMBERSHIP ON CLUB.CLUBID = MEMBERSHIP.CLUBID " + 
				"WHERE CLUBNAME = ? " +
				"GROUP BY CLUB.CLUBID, CLUB.SIGNUP, CLUB.OPENCYCLE, CLUB.CLUBINTRO, CLUB.CLUBNAME, CLUB.CLUBMASTER ";
		
		Object[] param = new Object[] {clubName}; // 모임 이름으로 검색 후 정렬 조건 설정
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			List<ClubDTO> list = new ArrayList<ClubDTO>();
			
			while (rs.next()) {
				ClubDTO dto = new ClubDTO(); // ClubDTO 객체 생성 후 검색 결과 저장
				dto.setClubId(rs.getInt("CLUBID"));
				dto.setSignUp(rs.getString("SIGNUP"));
				dto.setOpenCycle(rs.getString("OPENCYCLE"));
				dto.setClubIntro(rs.getString("CLUBINTRO"));
				dto.setClubName(rs.getString("CLUBNAME"));
				dto.setClubMaster(rs.getString("CLUBMASTER"));
				
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
	public List<ClubDTO> getSortedClub(String sortWith) { // 모임 정렬
		// TODO Auto-generated method stub
		if (sortWith.equals("clubName")) {
			String sortQuery = query + ", COUNT(MEMBERSHIP.CLUBID) AS COUNTMEMBER " + 
					"FROM CLUB LEFT OUTER JOIN MEMBERSHIP ON CLUB.CLUBID = MEMBERSHIP.CLUBID " + 
					"GROUP BY CLUB.CLUBID, CLUB.SIGNUP, CLUB.OPENCYCLE, CLUB.CLUBINTRO, CLUB.CLUBNAME, CLUB.CLUBMASTER " + 
					"ORDER BY CLUBNAME ";
			
			jdbcUtil.setSqlAndParameters(sortQuery, null);
		}
		else if (sortWith.equals("countMember")) { // 회원 순
			String sortQuery = query + ", COUNT(MEMBERSHIP.CLUBID) AS COUNTMEMBER " + 
					"FROM CLUB LEFT OUTER JOIN MEMBERSHIP ON CLUB.CLUBID = MEMBERSHIP.CLUBID " + 
					"GROUP BY CLUB.CLUBID, CLUB.SIGNUP, CLUB.OPENCYCLE, CLUB.CLUBINTRO, CLUB.CLUBNAME, CLUB.CLUBMASTER " + 
					"ORDER BY COUNTMEMBER DESC ";
			
			jdbcUtil.setSqlAndParameters(sortQuery, null);
		}
		else if (sortWith.equals("freeSignUp")) { // 자유 가입
			String sortQuery = query + ", COUNT(MEMBERSHIP.CLUBID) AS COUNTMEMBER " + 
					"FROM CLUB LEFT OUTER JOIN MEMBERSHIP ON CLUB.CLUBID = MEMBERSHIP.CLUBID " + 
					"WHERE CLUB.SIGNUP = 1 " +
					"GROUP BY CLUB.CLUBID, CLUB.SIGNUP, CLUB.OPENCYCLE, CLUB.CLUBINTRO, CLUB.CLUBNAME, CLUB.CLUBMASTER ";
			
			jdbcUtil.setSqlAndParameters(sortQuery, null);
		}
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query문 실행
			List<ClubDTO> list = new ArrayList<ClubDTO>(); // DTO 객체 담기 위한 리스트 생성
			
			while (rs.next()) {
				ClubDTO dto = new ClubDTO(); // ClubDTO 객체 생성 후 검색 결과 저장
				dto.setClubId(rs.getInt("CLUBID"));
				dto.setSignUp(rs.getString("SIGNUP"));
				dto.setOpenCycle(rs.getString("OPENCYCLE"));
				dto.setClubIntro(rs.getString("CLUBINTRO"));
				dto.setClubName(rs.getString("CLUBNAME"));
				dto.setClubMaster(rs.getString("CLUBMASTER"));
				dto.setCountClub(rs.getInt("COUNTMEMBER"));
					
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
	
	public boolean existingClub(String clubName) throws SQLException {
		String sql = "SELECT count(*) FROM CLUB WHERE clubName = ?";  
		
		Object[] param = new Object[] {clubName}; 
		jdbcUtil.setSqlAndParameters(sql, param);
		
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
	
	@Override
	public int insertClub(ClubDTO club) throws SQLException { // 모임 추가 (개설)
		// TODO Auto-generated method stub
		String insertQuery = "INSERT INTO "
				+ "CLUB (CLUBID, SIGNUP, OPENCYCLE, CLUBINTRO, CLUBNAME, CLUBMASTER) "
				+ "VALUES (ClubIdSeq.nextval, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] {club.getSignUp(), 
						club.getOpenCycle(), club.getClubIntro(), 
						club.getClubName(), club.getClubMaster()};
		jdbcUtil.setSqlAndParameters(insertQuery, param);
		
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return 0;
	}

	@Override
	public int deleteClub(int clubId) { // 모임 삭제
		// TODO Auto-generated method stub
		String deleteQuery = "DELETE FROM CLUB WHERE CLUBID = ?";
		
		Object[] param = new Object[] {clubId};
		jdbcUtil.setSqlAndParameters(deleteQuery, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

}
