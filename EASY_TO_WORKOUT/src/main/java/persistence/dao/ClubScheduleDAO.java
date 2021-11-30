package persistence.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import persistence.util.JDBCUtil;
import service.dto.ClubSchedule;

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

	public List<ClubSchedule> getClubScheduleList() {
		// TODO Auto-generated method stub
		String allQuery = query + "FROM CLUBSCHEDULE ORDER BY CLUBID";
		
		jdbcUtil.setSqlAndParameters(allQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<ClubSchedule> list = new ArrayList<ClubSchedule>(); 
			
			while (rs.next()) {
				ClubSchedule dto = new ClubSchedule(); 
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

	public ClubSchedule getClubScheduleById(int clubScheduleId, int clubId) {
		// TODO Auto-generated method stub
		String searchQuery = query + "FROM CLUBSCHEDULE WHERE CLUBSCHEDULEID = ? AND CLUBID = ?";
		
		Object[] param = new Object[] {clubScheduleId, clubId};		
		jdbcUtil.setSqlAndParameters(searchQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			ClubSchedule schedule = null;
			
			if (rs.next()) {
				schedule = new ClubSchedule(); 
				schedule.setClubId(rs.getInt("CLUBID"));
				schedule.setScheduleId(rs.getInt("SCHEDULEID"));
				schedule.setContactAddress(rs.getString("CONTACTADDRESS"));
				schedule.setNotice(rs.getString("NOTICE"));
				schedule.setCreationDate(rs.getString("CREATIONDATE"));
			}
			return schedule; 
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		}
		
		return null;
	}

	public int insertClubSchedule(ClubSchedule clubSchedule) {
		// TODO Auto-generated method stub
		String insertQuery = "INSERT INTO "
				+ "CLUBSCHEDULE (scheduleId, clubId, contactAddress, notice, creationDate) "
				+ "VALUES (ScheduleIdSeq.nextval, ?, ?, ?, ?)";
		
		// 포맷터
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		// 문자열 -> Date
		LocalDateTime date = LocalDateTime.parse(clubSchedule.getCreationDate(), formatter);
		// Date->Timestamp
		Timestamp creationDate = Timestamp.valueOf(date);
		
		Object[] param = new Object[] {
				clubSchedule.getClubId(), clubSchedule.getContactAddress(), 
				clubSchedule.getNotice(), creationDate};
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

	public int deleteClubSchedule(int clubId, int clubScheduleId) {
		// TODO Auto-generated method stub
		return 0;
	}


}
