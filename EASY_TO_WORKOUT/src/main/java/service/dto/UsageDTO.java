package service.dto;

public class UsageDTO {

	private int clubId = 0;
	private String scheduleId = null;
	private int routineId = 0;
	
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public int getRoutineId() {
		return routineId;
	}
	public void setRoutineId(int routineId) {
		this.routineId = routineId;
	}
	
}
