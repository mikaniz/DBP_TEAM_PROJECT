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
		jdbcUtil.setSqlAndParameters(allQuery, null);
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
		Object[] param = new Object[] {clubId};
		jdbcUtil.setSqlAndParameters(searchListQuery, param);
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
		Object[] param = new Object[] {clubId, scheduleId};
		jdbcUtil.setSqlAndParameters(searchQuery, param);
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
		Object[] param = new Object[] {usage.getClubId(), usage.getScheduleId(), usage.getRoutineId()};
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
	public int updateUsage(UsageDTO usage) {
		int result = 0;
		String updateQuery = "UPDATE usage "
				+ "SET routineId=? "
				+ "WHERE clubId=? and scheduleId=?";
		Object[] param = new Object[] {usage.getRoutineId(), usage.getClubId(), usage.getScheduleId()};
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
	public int deleteUsage(int clubId, String scheduleId, int routineId) {
		int result = 0;
		String deleteQuery = "DELETE FROM usage WHERE clubId=? and scheduleId=? and routineId=?";
		Object[] param = new Object[] {clubId, scheduleId, routineId};
		jdbcUtil.setSqlAndParameters(deleteQuery, param);
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
