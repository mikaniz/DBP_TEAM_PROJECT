package persistence.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import persistence.util.JDBCUtil;
import service.dto.ClubScheduleDTO;

public class ClubScheduleDAO {
	private JDBCUtil jdbcUtil = null;	
	
	private static String query = "SELECT CLUBSCHEDULE.SCHEDULEID AS SCHEDULEID, " +
				"CLUBSCHEDULE.CLUBID AS CLUBID, " +
				"CLUBSCHEDULE.CONTACTADDRESS AS CONTACTADDRESS, " + 
				"CLUBSCHEDULE.NOTICE AS NOTICE, " + 
				"CLUBSCHEDULE.CREATIONDATE AS CREATIONDATE";
	
	public ClubScheduleDAO() {
		jdbcUtil = new JDBCUtil(); 
	}

	public List<ClubScheduleDTO> getClubScheduleList() {
		// TODO Auto-generated method stub
		String allQuery = query + "FROM CLUBSCHEDULE ORDER BY CLUBID";
		
		jdbcUtil.setSqlAndParameters(allQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<ClubScheduleDTO> list = new ArrayList<ClubScheduleDTO>(); 
			
			while (rs.next()) {
				ClubScheduleDTO dto = new ClubScheduleDTO(); 
				dto.setClubId(rs.getInt("CLUBID"));
				dto.setScheduleId(rs.getInt("SCHEDULEID"));
				dto.setContactAddress(rs.getString("CONTACTADDRESS"));
				dto.setNotice(rs.getString("NOTICE"));
				dto.setCreationDate(rs.getString("CREATIONDATE"));
				
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

	public ClubScheduleDTO getClubScheduleById(int clubScheduleId, int clubId) {
		// TODO Auto-generated method stub
		String searchQuery = query + "FROM CLUBSCHEDULE WHERE CLUBSCHEDULEID = ? AND CLUBID = ?";
		
		Object[] param = new Object[] {clubScheduleId, clubId};		
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			ClubScheduleDTO schedule = null;
			
			if (rs.next()) {
				schedule = new ClubScheduleDTO(); // clubDTO ��ü�� �����Ͽ� ���� ���� ����
				schedule.setClubId(rs.getInt("CLUBID"));
				schedule.setScheduleId(rs.getInt("SCHEDULEID"));
				schedule.setContactAddress(rs.getString("CONTACTADDRESS"));
				schedule.setNotice(rs.getString("NOTICE"));
				schedule.setCreationDate(rs.getString("CREATIONDATE"));
			}
			return schedule; // ���� ������ ��� �ִ� ClubDTO ��ü ��ȯ
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection ��ȯ
		}
		
		return null;
	}

	public int insertClubSchedule(ClubScheduleDTO clubSchedule) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteClubSchedule(int clubId, int clubScheduleId) {
		// TODO Auto-generated method stub
		return 0;
	}


}
