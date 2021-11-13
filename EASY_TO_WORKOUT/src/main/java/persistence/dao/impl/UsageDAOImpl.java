package persistence.dao.impl;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import persistence.dao.*;
import persistence.util.*;
import service.dto.*;

public class UsageDAOImpl implements UsageDAO {

	private JDBCUtil jdbcUtil = null;
	
	public UsageDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}

	@Override
	public List<UsageDTO> getUsageList() {
		String allQuery = "SELECT * FROM usage";
		jdbcUtil.setSql(allQuery);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<UsageDTO> list = new ArrayList<UsageDTO>();
			
			while (rs.next()) {
				UsageDTO dto = new UsageDTO();
				dto.setClubId(rs.getInt("clubId"));
				dto.setScheduleId(rs.getString("scheduleId"));
				dto.setRoutineId(rs.getInt("routineId"));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	@Override
	public List<UsageDTO> getUsageListByClubId(int clubId) {
		String searchListQuery = "SELECT * FROM usage WHERE clubId=?";
		jdbcUtil.setSql(searchListQuery);
		Object[] param = new Object[] {clubId};
		jdbcUtil.setParameters(param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<UsageDTO> list = new ArrayList<UsageDTO>();
			
			while (rs.next()) {
				UsageDTO dto = new UsageDTO();
				dto.setClubId(rs.getInt("clubId"));
				dto.setScheduleId(rs.getString("scheduleId"));
				dto.setRoutineId(rs.getInt("routineId"));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	@Override
	public UsageDTO getUsageByClubIdAndScheduleId(int clubId, String scheduleId) {
		String searchQuery = "SELECT * FROM usage WHERE clubId=? and scheduleId=?";
		jdbcUtil.setSql(searchQuery);
		Object[] param = new Object[] {clubId, scheduleId};
		jdbcUtil.setParameters(param);
		UsageDTO dto = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				dto = new UsageDTO();
				dto.setClubId(rs.getInt("clubId"));
				dto.setScheduleId(rs.getString("scheduleId"));
				dto.setRoutineId(rs.getInt("routineId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return dto;
	}

	@Override
	public int insertUsage(UsageDTO usage) {
		int result = 0;
		String insertQuery = "INSERT INTO "
				+ "usage (clubId, scheduleId, routineId) "
				+ "VALUES (?, ?, ?)";
		jdbcUtil.setSql(insertQuery);
		Object[] param = new Object[] {usage.getClubId(), usage.getScheduleId(), usage.getRoutineId()};
		jdbcUtil.setParameters(param);
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
	public int updateUsage(UsageDTO usage) {
		int result = 0;
		String updateQuery = "UPDATE usage "
				+ "SET routineId=? "
				+ "WHERE clubId=? and scheduleId=?";
		jdbcUtil.setSql(updateQuery);
		Object[] param = new Object[] {usage.getRoutineId(), usage.getClubId(), usage.getScheduleId()};
		jdbcUtil.setParameters(param);
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
	public int deleteUsage(int clubId, String scheduleId, int routineId) {
		int result = 0;
		String deleteQuery = "DELETE FROM usage WHERE clubId=? and scheduleId=? and routineId=?";
		jdbcUtil.setSql(deleteQuery);
		Object[] param = new Object[] {clubId, scheduleId, routineId};
		jdbcUtil.setParameters(param);
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
	
}
