package service;

import java.util.List;

import persistence.DAOFactory;
import persistence.dao.ClubScheduleDAO;
import service.dto.ClubSchedule;

public class ClubScheduleManager {

	private static ClubScheduleManager scheduleManager = new ClubScheduleManager();
	private ClubScheduleDAO scheduleDao;
	
	private ClubScheduleManager() {
		DAOFactory factory = new DAOFactory();
		scheduleDao = factory.getClubScheduleDAO();
	}
	
	public static ClubScheduleManager getInstance() {return scheduleManager;}
	
	public List<ClubSchedule> getClubScheduleList() {
		return scheduleDao.getClubScheduleList();
	}

	public ClubSchedule getClubScheduleById(int clubScheduleId, int clubId) {
		return scheduleDao.getClubScheduleById(clubScheduleId, clubId);
	}
	
	public int insertClubSchedule(ClubSchedule clubSchedule) {
		return scheduleDao.insertClubSchedule(clubSchedule);
	}
	
	public int getCurrentScheduleId(ClubSchedule clubSchedule) {
		return scheduleDao.getCurrentScheduleId(clubSchedule);
	}
	
}
