package persistence.dao;

import java.util.List;

import service.dto.ClubScheduleDTO;

public interface ClubScheduleDAO {
	
	public List<ClubScheduleDTO> getClubScheduleList();
	public ClubScheduleDTO getClubScheduleById(int clubId, int clubScheduleId);
	public int insertClubSchedule(ClubScheduleDTO clubSchedule); 
	public int deleteClubSchedule(int clubScheduleId, int clubId);
	
}
