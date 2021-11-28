package persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.util.JDBCUtil;
import service.dto.Membership;

public class MembershipDAO {
	private JDBCUtil jdbcUtil = null;	
	
	private static String query = "SELECT Membership.clubId AS clubId, " + 
			"Membership.MemberId AS MemberId, " + 
			"Membership.subDate AS subDate ";
	
	public MembershipDAO() {
		jdbcUtil = new JDBCUtil(); 
	}
	
	public List<Membership> getClubListByMemberId(String memberId) {
		// TODO Auto-generated method stub
		String searchQuery = query + "FROM Membership "
				+ "WHERE MemberId = ? ";
		
		Object[] param = new Object[] {memberId};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Membership> list = new ArrayList<Membership>();
			
			while (rs.next()) {
				Membership membership = new Membership();
				membership.setClubId(rs.getInt("clubId"));
				membership.setMemberId(rs.getString("MemberId"));
				membership.setSubDate(rs.getTimestamp("subDate"));
				
				list.add(membership); 
			}
			return list; 
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}
	
	public int insertMembership(Membership membership) throws SQLException { 
		// TODO Auto-generated method stub
		String insertQuery = "INSERT INTO "
				+ "Membership (clubId, MemberId, subDate) "
				+ "VALUES (?, ?, ?)";
		
		Object[] param = new Object[] {membership.getClubId(), 
				membership.getMemberId(), membership.getSubDate()};
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
	
	public int deleteMembership(int clubId, String memberId) { 
		// TODO Auto-generated method stub
		String deleteQuery = "DELETE FROM membership WHERE clubid =? and memberid = ?";
		
		Object[] param = new Object[] {clubId, memberId};
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

	
	/* 사용자가 특정 모임에 속하는지 판별 */
	public boolean isInClub(int clubId, String memberId) {	
		String sql = "SELECT count(*) FROM membership WHERE clubid = ? and memberid = ?";  
		
		Object[] param = new Object[] {clubId, memberId}; 
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
	
	
}
