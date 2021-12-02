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
				"CLUBSCHEDULE.CREATIONDATE AS CREATIONDATE ";
	
	public ClubScheduleDAO() {
		jdbcUtil = new JDBCUtil(); 
	}

	public List<ClubSchedule> getClubScheduleListById(int clubId) {
		// TODO Auto-generated method stub
		String allQuery = query + "FROM CLUBSCHEDULE WHERE CLUBID = ? ORDER BY CREATIONDATE";
		
		Object[] param = new Object[] {clubId};	
		jdbcUtil.setSqlAndParameters(allQuery, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<ClubSchedule> list = new ArrayList<ClubSchedule>(); 
			
			while (rs.next()) {
				ClubSchedule dto = new ClubSchedule(); 
				dto.setClubId(rs.getInt("CLUBID"));
				dto.setScheduleId(rs.getInt("SCHEDULEID"));
				dto.setContactAddress(rs.getString("CONTACTADDRESS"));
				dto.setNotice(rs.getString("NOTICE"));
				
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
				String creationDate = sdf.format(rs.getTimestamp("CREATIONDATE"));
				dto.setCreationDate(creationDate);
				
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
		String searchQuery = query + "FROM CLUBSCHEDULE WHERE scheduleid = ? AND clubid = ?";
		
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
				
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
				String creationDate = sdf.format(rs.getTimestamp("CREATIONDATE"));
				schedule.setCreationDate(creationDate);
			}
			return schedule; 
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		}
		
		return null;
	}

	public List<ClubSchedule> getScheduleByMemberId(String memberId) {
		String findQuery = "SELECT DISTINCT clubSchedule.clubId AS clubId, clubSchedule.scheduleid AS scheduleId, "
								+ "clubschedule.creationDate AS creationDate, clubSchedule.contactAddress AS contactAddress "
						+ "FROM membership JOIN clubschedule ON membership.clubId = clubSchedule.clubId "
								+ "JOIN club ON membership.clubId = club.clubId "
						+ "WHERE  membership.memberid=? "
								+ "AND TO_CHAR(clubschedule.creationDate, 'YY/MM/DD HH:mm') > TO_CHAR(SYSDATE, 'YY/MM/DD HH:mm') "
						+ "ORDER BY clubschedule.creationDate";
		
		Object[] param = new Object[] {memberId};		
		jdbcUtil.setSqlAndParameters(findQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); 
			List<ClubSchedule> list = new ArrayList<ClubSchedule>(); 
			
			while (rs.next()) {
				ClubSchedule schedule = new ClubSchedule(); 
				schedule.setClubId(rs.getInt("clubId"));
				schedule.setScheduleId(rs.getInt("scheduleId"));
				
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
				String creationDate = sdf.format(rs.getTimestamp("creationDate"));
				schedule.setCreationDate(creationDate);
				
				schedule.setContactAddress(rs.getString("contactAddress"));
				
				list.add(schedule); 
			}
			return list; 
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

	public int getCurrentScheduleId(ClubSchedule schedule) {
		String findQuery = "SELECT scheduleId FROM clubschedule "
				+ "WHERE clubId=? and contactAddress=? and notice=? and creationDate=? ";
		
		// 포맷터
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		// 문자열 -> Date
		LocalDateTime date = LocalDateTime.parse(schedule.getCreationDate(), formatter);
		// Date->Timestamp
		Timestamp creationDate = Timestamp.valueOf(date);
		
		Object[] param = new Object[] {
				schedule.getClubId(), schedule.getContactAddress(), schedule.getNotice(), creationDate};
		jdbcUtil.setSqlAndParameters(findQuery, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			int result = 0;
			
			if (rs.next()) {
				result = rs.getInt("scheduleId");
			}
			return result;
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); 
		}
		
		return 0;
	}


}
