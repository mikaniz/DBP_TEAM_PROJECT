package persistence.dao;

import java.util.List;

import service.dto.UsageDTO;

public interface UsageDAO {

	public List<UsageDTO> getUsageList();
	public List<UsageDTO> getUsageListByClubId(int clubId);
	public UsageDTO getUsageByClubIdAndScheduleId(int clubId, String scheduleId);
	public int insertUsage(UsageDTO usage);
	public int updateUsage(UsageDTO usage);
	public int deleteUsage(int clubId, String scheduleId, int routineId);
	
}
