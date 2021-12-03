package service;

import java.util.List;

import persistence.DAOFactory;
import persistence.dao.UsageDAO;
import service.dto.Usage;

public class UsageManager {

	private static UsageManager usageManager = new UsageManager();
	private UsageDAO usageDao;
	
	private UsageManager() {
		DAOFactory factory = new DAOFactory();
		usageDao = factory.getUsageDAO();
	}
	
	public static UsageManager getInstance() {return usageManager;}
	
	public List<Usage> getUsageList() {
		return usageDao.getUsageList();
	}
	
	public Usage getUsageListByClubId(int clubId) {
		return usageDao.getUsageListByClubId(clubId);
	}
	
	public List<Usage> getUsageByClubIdAndScheduleId(int clubId, int scheduleId) {
		return usageDao.getUsageByClubIdAndScheduleId(clubId, scheduleId);
	}
	
	public int insertUsage(Usage usage) {
		return usageDao.insertUsage(usage);
	}
}
