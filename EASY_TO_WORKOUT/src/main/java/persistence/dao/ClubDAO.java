package persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.util.JDBCUtil;
import service.dto.Club;
import service.exception.ExistingClubException;

public class ClubDAO  {
	private JDBCUtil jdbcUtil = null;	
	
	private static String query = "SELECT CLUB.CLUBID AS CLUBID, " + 
			"CLUB.SIGNUP AS SIGNUP, " + 
			"CLUB.OPENCYCLE AS OPENCYCLE, " + 
			"CLUB.CLUBINTRO AS CLUBINTRO, " + 
			"CLUB.CLUBNAME AS CLUBNAME, " + 
			"CLUB.CLUBMASTER AS CLUBMASTER";
	
	public ClubDAO() {
		jdbcUtil = new JDBCUtil(); 
	}
	
	public List<Club> getClubList() throws SQLException {
		// TODO Auto-generated method stub
		String allQuery = query + ", COUNT(MEMBERSHIP.CLUBID) AS COUNTMEMBER " + 
				"FROM CLUB LEFT OUTER JOIN MEMBERSHIP ON CLUB.CLUBID = MEMBERSHIP.CLUBID " + 
				"GROUP BY CLUB.CLUBID, CLUB.SIGNUP, CLUB.OPENCYCLE, CLUB.CLUBINTRO, CLUB.CLUBNAME, CLUB.CLUBMASTER ";
		
		jdbcUtil.setSqlAndParameters(allQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<Club> list = new ArrayList<Club>(); 
			
			while (rs.next()) {
				Club club = new Club(); 
				club.setClubId(rs.getInt("CLUBID"));
				club.setSignUp(rs.getString("SIGNUP"));
				club.setOpenCycle(rs.getString("OPENCYCLE"));
				club.setClubIntro(rs.getString("CLUBINTRO"));
				club.setClubName(rs.getString("CLUBNAME"));
				club.setClubMaster(rs.getString("CLUBMASTER"));
				club.setCountClub(rs.getInt("COUNTMEMBER"));
				
				list.add(club); 
			}
			return list; 
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}	

	public Club getClubById(int clubId) { 
		// TODO Auto-generated method stub
		String searchQuery = query + " FROM CLUB WHERE CLUBID = ?";
		
		Object[] param = new Object[] {clubId};		
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Club club = null;
			
			if (rs.next()) {
				club = new Club(); 
				club.setClubId(rs.getInt("CLUBID"));
				club.setSignUp(rs.getString("SIGNUP"));
				club.setOpenCycle(rs.getString("OPENCYCLE"));
				club.setClubIntro(rs.getString("CLUBINTRO"));
				club.setClubName(rs.getString("CLUBNAME"));
				club.setClubMaster(rs.getString("CLUBMASTER"));
			}
			return club; 
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		}
		
		return null;
	}

	public List<Club> getClubByName(String clubName) throws ExistingClubException, SQLException {
		// TODO Auto-generated method stub
		String searchQuery = query + ", COUNT(MEMBERSHIP.CLUBID) AS COUNTMEMBER " + 
				"FROM CLUB LEFT OUTER JOIN MEMBERSHIP ON CLUB.CLUBID = MEMBERSHIP.CLUBID " + 
				"WHERE CLUBNAME = ? " +
				"GROUP BY CLUB.CLUBID, CLUB.SIGNUP, CLUB.OPENCYCLE, CLUB.CLUBINTRO, CLUB.CLUBNAME, CLUB.CLUBMASTER ";
		
		Object[] param = new Object[] {clubName};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Club> list = new ArrayList<Club>();
			
			while (rs.next()) {
				Club dto = new Club(); 
				dto.setClubId(rs.getInt("CLUBID"));
				dto.setSignUp(rs.getString("SIGNUP"));
				dto.setOpenCycle(rs.getString("OPENCYCLE"));
				dto.setClubIntro(rs.getString("CLUBINTRO"));
				dto.setClubName(rs.getString("CLUBNAME"));
				dto.setClubMaster(rs.getString("CLUBMASTER"));
				
				list.add(dto); 
			}
			return list; 
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	public List<Club> getSortedClub(String sortWith) { 
		// TODO Auto-generated method stub
		if (sortWith.equals("clubName")) {
			String sortQuery = query + ", COUNT(MEMBERSHIP.CLUBID) AS COUNTMEMBER " + 
					"FROM CLUB LEFT OUTER JOIN MEMBERSHIP ON CLUB.CLUBID = MEMBERSHIP.CLUBID " + 
					"GROUP BY CLUB.CLUBID, CLUB.SIGNUP, CLUB.OPENCYCLE, CLUB.CLUBINTRO, CLUB.CLUBNAME, CLUB.CLUBMASTER " + 
					"ORDER BY CLUBNAME ";
			
			jdbcUtil.setSqlAndParameters(sortQuery, null);
		}
		else if (sortWith.equals("countMember")) {
			String sortQuery = query + ", COUNT(MEMBERSHIP.CLUBID) AS COUNTMEMBER " + 
					"FROM CLUB LEFT OUTER JOIN MEMBERSHIP ON CLUB.CLUBID = MEMBERSHIP.CLUBID " + 
					"GROUP BY CLUB.CLUBID, CLUB.SIGNUP, CLUB.OPENCYCLE, CLUB.CLUBINTRO, CLUB.CLUBNAME, CLUB.CLUBMASTER " + 
					"ORDER BY COUNTMEMBER DESC ";
			
			jdbcUtil.setSqlAndParameters(sortQuery, null);
		}
		else if (sortWith.equals("freeSignUp")) { 
			String sortQuery = query + ", COUNT(MEMBERSHIP.CLUBID) AS COUNTMEMBER " + 
					"FROM CLUB LEFT OUTER JOIN MEMBERSHIP ON CLUB.CLUBID = MEMBERSHIP.CLUBID " + 
					"WHERE CLUB.SIGNUP = 1 " +
					"GROUP BY CLUB.CLUBID, CLUB.SIGNUP, CLUB.OPENCYCLE, CLUB.CLUBINTRO, CLUB.CLUBNAME, CLUB.CLUBMASTER ";
			
			jdbcUtil.setSqlAndParameters(sortQuery, null);
		}
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<Club> list = new ArrayList<Club>(); 
			
			while (rs.next()) {
				Club dto = new Club(); 
				dto.setClubId(rs.getInt("CLUBID"));
				dto.setSignUp(rs.getString("SIGNUP"));
				dto.setOpenCycle(rs.getString("OPENCYCLE"));
				dto.setClubIntro(rs.getString("CLUBINTRO"));
				dto.setClubName(rs.getString("CLUBNAME"));
				dto.setClubMaster(rs.getString("CLUBMASTER"));
				dto.setCountClub(rs.getInt("COUNTMEMBER"));
					
				list.add(dto); 
			}
			return list; 
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}
	
	public boolean existingClub(String clubName) throws SQLException {
		String sql = "SELECT count(*) FROM CLUB WHERE clubName = ?";  
		
		Object[] param = new Object[] {clubName}; 
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 0 ? false : true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return false;
	}
	
	public int insertClub(Club club) throws SQLException { 
		// TODO Auto-generated method stub
		String insertQuery = "INSERT INTO "
				+ "CLUB (CLUBID, SIGNUP, OPENCYCLE, CLUBINTRO, CLUBNAME, CLUBMASTER) "
				+ "VALUES (ClubIdSeq.nextval, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] {club.getSignUp(), 
						club.getOpenCycle(), club.getClubIntro(), 
						club.getClubName(), club.getClubMaster()};
		jdbcUtil.setSqlAndParameters(insertQuery, param);
		
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}	
		return 0;
	}

	public int deleteClub(int clubId) { 
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
